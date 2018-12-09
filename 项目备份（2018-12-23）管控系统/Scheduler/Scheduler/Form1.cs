using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Apache.NMS;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Threading;
using System.Windows.Forms.DataVisualization.Charting;
using HslCommunication.Controls;

namespace Scheduler
{
    public partial class Form1 : Form
    {
        private String dataBaseIP = ReadXML.readArg("DataBaseIP");//数据库 ip地址
        private String dataBaseUsername = ReadXML.readArg("DataBaseUsername");//数据库用户名
        private String dataBasePassword = ReadXML.readArg("DataBasePassword");//数据库密码
        private String activeMQServerIP = ReadXML.readArg("ActiveMQServerIP");//ActiveMQ服务器 ip地址
        private String fTPServerIP = ReadXML.readArg("FTPServerIP");//FTP服务器 ip地址
        private String fTPUsername = ReadXML.readArg("FTPUsername");//FTP用户名
        private String fTPPassword = ReadXML.readArg("FTPPassword");//FTP密码
        private String localPath = ReadXML.readArg("LocalPath"); //本地文件夹(下载的默认路径)
        private String RTIServerIP = ReadXML.readArg("RTIServerIP");//RTI服务器地址
        //<"71":{},"72":{}> 记录每一次分配情况  key->运行ID  value->某一次的分配情况
        private Dictionary<String, Dictionary<String, Dictionary<String, Object>>> dictionaryAssignmentNormals = new Dictionary<String, Dictionary<String, Dictionary<String, Object>>>();//记录每一次 分配到运行端的信息(普通成员)
        private List<RunInstanceInfo> runInstanceInfos = new List<RunInstanceInfo>();//将调度程序接收到的json(Ordertype:Run)方案信息存到里面

        //监听前端传过来的Topic SCHEMEID--->接收Scheme数据
        private void OnMessage(IMessage message)
        {
            try
            {
                ITextMessage msg = (ITextMessage)message;
                Console.WriteLine("Riceive:" + msg.Text);//接收MQ内的消息(topic--->SCHEME)
                //将前端json数据解析
                JObject jobject = (JObject)JsonConvert.DeserializeObject(msg.Text);
                String orderType = jobject["OrderType"].ToString();//运行指令
                int runId = (int)jobject["RunId"];//运行ID
                MQQueueProducer mQQueueProducer;

                #region 运行指令
                if (orderType == "Run")
                {

                    #region 将解析的数据存入集合  后期将展示当前有多少个运行实例
                    //<queue1:{"OrderType":"Run","RunInstanceInfo":……,"MEMBER_TYPE":"Normal"}, queue2:{"OrderType":"Run","RunInstanceInfo":……,"MEMBER_TYPE":"Normal"}>
                    Dictionary<String, Dictionary<String, Object>> dictionaryAssignmentNormal = new Dictionary<String, Dictionary<String, Object>>(); //存放某一次分配到各个运行端的信息(普通成员)
                    int schemeId = (int)jobject["SchemeId"];//方案ID
                    Console.WriteLine("获取的方案ID为" + schemeId);
                    Console.WriteLine("获取的运行ID为" + runId);
                    JArray jarray = (JArray)jobject["SchemeInfo"];
                    List<Member> members = new List<Member>();
                    foreach (JObject jobjectTemp in jarray)
                    {
                        int member_id = (int)jobjectTemp["SCHEME_MODEL_ID"];//成员ID
                        int model_id = (int)jobjectTemp["MUID"];//成员ID
                        String dll_name = jobjectTemp["COM_NAME"].ToString();//DLL名
                        Member member = new Member();
                        member.Member_id = member_id;//成员ID
                        member.Model_id = model_id;//模型ID
                        member.Dll_name = dll_name;//DLL名
                        members.Add(member);
                    }
                    RunInstanceInfo runInstanceInfo = new RunInstanceInfo();
                    runInstanceInfo.Statue = "Run";//方案正在运行状态
                    runInstanceInfo.RunId = runId;//运行ID
                    runInstanceInfo.SchemeId = schemeId;//方案ID
                    runInstanceInfo.Members = members;//所有成员
                    runInstanceInfos.Add(runInstanceInfo);//数据持久化
                    Console.WriteLine(runInstanceInfo.toString());//展示数据 
                    #endregion

                    //根据前端获取的运行号作为本方案运行ID
                    //查询运行记录库，现在的运行信息是从前端获取，而不是从后端自己通过数据库生成    
                    DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
                    DataSet ds = db.Query("select * from runlibrary");

                    DataTable table = new DataTable();
                    //确定datatable每一列的数据类型  同时确定datatable的结构 (一个queue对应一个member 按成员进行分配)
                    table.Columns.Add(new DataColumn("TOPICNAME", typeof(String)));
                    table.Columns.Add(new DataColumn("MEMBER", typeof(Member)));

                    //模型分配原则 先给每一个运行端各分配一个 若有多余全部分配给最后一个上线的运行端
                    if (ds.Tables[0].Rows.Count == 0)
                    {
                        MessageBox.Show("无运行端");
                    }
                    else
                    {//正常处理 有运行端，应该先处理控制成员的分配，然后再处理其他成员的分配

                        Console.WriteLine("准备写入数据库");
                        String nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                        String sql = "insert into loginformation values(" + runId + "," + schemeId + ",'" + nowTime + "')";
                        db.Insert(sql);
                        //将数据库里增加一条方案运行的记录
                        Console.WriteLine("写入数据库成功");

                        #region 先进行控制成员的分配，再进行仿真成员的分配  分配给id最大的topic
                        //自定义发送到运行端的数据结构 用Dictionary存放
                        Dictionary<String, Object> directoryControl = new Dictionary<String, Object>();
                        directoryControl.Add("OrderType", "Run");
                        RunInstanceInfo runInstanceInfoControl = new RunInstanceInfo();
                        runInstanceInfoControl.RunId = runId;//运行ID
                        runInstanceInfoControl.SchemeId = schemeId;//方案ID
                        runInstanceInfoControl.Statue = "Run";//运行状态
                        directoryControl.Add("RunInstanceInfo", runInstanceInfoControl);
                        directoryControl.Add("MEMBER_TYPE", "Contorl");//成员类型为Contorl表示是控制成员
                        Console.WriteLine("CCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                        //将控制成员也通过Json格式进行传输
                        String sendMessageControl = JsonConvert.SerializeObject(directoryControl);//发送控制成员的信息
                        Console.WriteLine("控制成员的分配信息为" + sendMessageControl);
                        int maxId = (int)db.Query("select max(id) from runlibrary").Tables[0].Rows[0][0];
                        String queueNameControl = db.Query("select queueName from runlibrary where id=" + maxId).Tables[0].Rows[0][0].ToString();//获取最新加入的运行端分配控制成员
                        Console.WriteLine("控制成员的分配运行端为" + queueNameControl);
                        mQQueueProducer = new MQQueueProducer(activeMQServerIP, sendMessageControl, queueNameControl);
                        #endregion

                        #region 然后进行正常仿真成员的处理
                        Console.WriteLine("1111111111111111111111111111111111111111111111111111");
                        List<Member> membersNormal = runInstanceInfo.Members;
                        for (int i = 0; i < membersNormal.Count; i++)//分配普通成员,遍历前端发送过来的成员信息
                        {
                            String queueNameNormal;
                            if (i < ds.Tables[0].Rows.Count)
                            {
                                queueNameNormal = ds.Tables[0].Rows[i][1].ToString();//正常仿真成员的分配
                            }
                            else
                            {
                                queueNameNormal = queueNameControl;//topicNameControlMember即是当前最大id对应的topic
                            }
                            Console.WriteLine("成员" + membersNormal[i].Member_id + "运行分配队列为" + queueNameNormal);
                            DataRow dr = table.NewRow();//创建新的一行
                            dr[0] = queueNameNormal;//queue名称
                            dr[1] = membersNormal[i];//成员对象
                            table.Rows.Add(dr);
                        }
                        #endregion
                        Console.WriteLine("22222222222222222222222222222222222222222222");

                        for (int i = 0; i < ds.Tables[0].Rows.Count; i++)//查询数据库获取当前所有的queue
                        {
                            RunInstanceInfo runInstanceInfoTemp = new RunInstanceInfo();
                            List<Member> membersTemp = new List<Member>();
                            for (int j = 0; j < table.Rows.Count; j++)
                            {
                                if (ds.Tables[0].Rows[i][1].ToString() == table.Rows[j][0].ToString())
                                {
                                    Console.WriteLine("ds.Tables[0].Rows[i][1].ToString():" + ds.Tables[0].Rows[i][1].ToString());
                                    Console.WriteLine("table1.Rows[j][0].ToString():" + table.Rows[j][0].ToString());
                                    membersTemp.Add((Member)table.Rows[j][1]);
                                }
                            }
                            runInstanceInfoTemp.Statue = "Run";//状态
                            runInstanceInfoTemp.RunId = runId;//运行ID
                            runInstanceInfoTemp.SchemeId = schemeId;//方案ID
                            runInstanceInfoTemp.Members = membersTemp;//所有成员
                            //初始化每一个运行端数据
                            Dictionary<String, Object> clientData = new Dictionary<String, Object>();
                            clientData.Add("OrderType", "Run");
                            clientData.Add("RunInstanceInfo", runInstanceInfoTemp);
                            clientData.Add("MEMBER_TYPE", "Normal");//成员类型为Normal表示该方案里面的为仿真成员
                            //每一个queue 对应一个运行端数据
                            dictionaryAssignmentNormal.Add(ds.Tables[0].Rows[i][1].ToString(), clientData);
                        }
                        dictionaryAssignmentNormals.Add(runId.ToString(), dictionaryAssignmentNormal);
                        Console.WriteLine("==========================");

                        foreach (var item in dictionaryAssignmentNormal)//遍历某一次分配的信息
                        {
                            String queueName = item.Key;//queue名字
                            Dictionary<String, Object> clientData = item.Value;
                            String sendMessageNormalMember = JsonConvert.SerializeObject(clientData);
                            mQQueueProducer = new MQQueueProducer(activeMQServerIP, sendMessageNormalMember, queueName);
                            Console.WriteLine("普通成员的分配队列为" + queueName);
                        }
                    }
                    db.DBClose();//释放资源
                    #region treeView1委托所在线程与当前调用的线程不是同一个线程  必须通过Invoke或者BeginInvoke调用
                    if (treeView1.InvokeRequired)
                    { //InvokeRequired方法返回值为true
                        //刷新控件
                        treeView1.BeginInvoke(new MyDelegate(showTreeInfo));
                    }
                    #endregion
                }
                #endregion

                #region 重启指令
                if (orderType == "Restart")//向各个topic发送重启运行端的指令 Ordertype:Restart
                {
                    Restart(runId, orderType);//重启运行实例
                }
                #endregion
            }
            catch (Exception e)
            {
                Console.WriteLine("监听出错，错误信息如下 -->" + e.Message);
            }
        }
        //runId运行号   orderType启动指令类型
        private void Restart(int runId, String orderType)
        {
            Dictionary<String, Object> dictionary = new Dictionary<String, Object>();
            dictionary.Add("OrderType", orderType);
            RunInstanceInfo runInstanceInfo = new RunInstanceInfo();
            runInstanceInfo.RunId = runId;
            dictionary.Add("RunInstanceInfo", runInstanceInfo);
            String sendMessage = JsonConvert.SerializeObject(dictionary);
            foreach (var itemfather in dictionaryAssignmentNormals)
            {
                Dictionary<String, Dictionary<String, Object>> dictionaryAssignmentNormal = itemfather.Value;
                if (itemfather.Key == runId.ToString())
                {
                    foreach (var itemson in dictionaryAssignmentNormal)
                    {
                        String queueName = itemson.Key;
                        MQTopicProducer activeMQProducer = new MQTopicProducer(activeMQServerIP, sendMessage, queueName);
                        Console.WriteLine("普通成员的分配队列为" + queueName);
                    }
                }
            }
        }

        //定义一个委托 
        public delegate void MyDelegate();
        //展示树节点信息
        private void showTreeInfo()
        {
            TreeNodeCollection nodes = treeView1.Nodes;
            TreeNode runInstanceInfosNode = nodes[0];//获取运行实例结点
            TreeNode clientsNode = nodes[1];//获取运行端结点
            runInstanceInfosNode.Nodes.Clear();//清除运行实例结点下所有子结点
            clientsNode.Nodes.Clear();//清除运行端结点下所有子结点

            #region 展示运行方案实例信息
            for (int i = 0; i < runInstanceInfos.Count; i++)
            {
                RunInstanceInfo runInstanceInfo = runInstanceInfos[i];//运行实例
                int runId = runInstanceInfo.RunId;//运行ID
                TreeNode runInstanceInfoNode = new TreeNode();//运行实例结点
                runInstanceInfoNode.Text = runId.ToString();//运行ID
                nodes[0].Nodes.Add(runInstanceInfoNode);
            }
            #endregion

            #region 展示运行端信息
            //查询数据库看当前有多少个topic(运行端)
            DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
            DataSet ds = db.Query("select * from runlibrary");
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                String topicName = ds.Tables[0].Rows[i][1].ToString();//topic名
                int id = (int)ds.Tables[0].Rows[i][0];//topic对应的id
                TreeNode clientNode = new TreeNode();
                clientNode.Text = "运行端" + id;
                nodes[1].Nodes.Add(clientNode);
            }
            db.DBClose();//关闭数据库连接
            #endregion
            treeView1.ExpandAll();//展开所有节点
        }
        private int physicalMemory;//物理内存
        private int memoryAvailable;//可用内存
        private double cpuLoad;//CPU占用率
        //展示仪表盘（CPU）
        private void showCPUInfo()
        {
            this.userGaugeChart1.Value = cpuLoad;//CPU占用率
            if(this.userGaugeChart1.Value>=80)
            {
                this.label8.Text = "CPU当前状态:报警";
            }
            else
            {
                this.label8.Text = "CPU当前状态:正常";
            }
        }
        //展示内存信息
        private void showMemoryInfo()
        {
            this.userVerticalProgress1.Value = (int)((physicalMemory - memoryAvailable)*100 / physicalMemory);//内存利用率
            this.userVerticalProgress2.Value = (int)((physicalMemory - memoryAvailable)*100/physicalMemory);//已经使用的内存
            this.label11.Text = physicalMemory.ToString();//物理内存            
        }
        //初始化数据到 physicalMemory、memoryAvailable、cpuLoad
        private void OnMessageSyStemInfo(IMessage message)
        {
            try
            {
                ITextMessage msg = (ITextMessage)message;
                Console.WriteLine("Riceive:" + msg.Text);
                JObject jobject = (JObject)JsonConvert.DeserializeObject(msg.Text);
                physicalMemory = (int)jobject["PhysicalMemory"];
                memoryAvailable = (int)jobject["MemoryAvailable"];
                cpuLoad = (double)jobject["CpuLoad"];
                if (this.groupBox2.InvokeRequired)
                { //InvokeRequired方法返回值为true
                    //刷新控件
                    this.groupBox2.BeginInvoke(new MyDelegate(showCPUInfo));
                }
                if (this.groupBox3.InvokeRequired)
                { //InvokeRequired方法返回值为true
                    //刷新控件
                    this.groupBox3.BeginInvoke(new MyDelegate(showMemoryInfo));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("错误信息-->" + ex.Message);
            }
        }
        public Form1()
        {
            //运行调度
            //接收前端SCHEME_ID
            Console.WriteLine("调度程序正在监听前端的方案ID……");
            MQTopicConsumer mQTopicConsumer = new MQTopicConsumer();
            mQTopicConsumer.ActiveMQInit(activeMQServerIP, "SCHEME").Listener += new MessageListener(OnMessage);//调度程序监听的Topic名为SCHEME
            MQTopicConsumer mQTopicConsumerSystem = new MQTopicConsumer();
            mQTopicConsumerSystem.ActiveMQInit(activeMQServerIP, "SystemInfoResponse").Listener += new MessageListener(OnMessageSyStemInfo);

            InitializeComponent();
        }

        //运行实例终止
        private void userButton1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "")
            {
                Dictionary<String, String> dictionary = new Dictionary<String, String>();
                dictionary.Add("Control_Order", "Stop");
                String sendMessage = JsonConvert.SerializeObject(dictionary);
                MQTopicProducer activeMQProducer = new MQTopicProducer(activeMQServerIP, sendMessage, "Control_Order" + this.textBox1.Text);
            }
        }
        //运行实例重启
        private void userButton2_Click(object sender, EventArgs e)
        {
            int runId = Convert.ToInt32(this.textBox1.Text);
            if (textBox1.Text != "")
            {
                Restart(runId, "Restart");//重启运行实例
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //对应Label与TextBox对齐
            label1.Height = textBox1.Height;
            label2.Height = textBox2.Height;
            label3.Height = textBox3.Height;
            label4.Height = textBox4.Height;
            label5.Height = textBox5.Height;
            label6.Height = textBox6.Height;
            label7.Height = richTextBox1.Height / 4;
            userGaugeChart1.Height = groupBox2.Height * 3 / 4;
            label13.Height = textBox7.Height;
            label14.Height = textBox8.Height;
            label15.Height = textBox9.Height;
            label16.Height = textBox10.Height;

            groupBox2_X = groupBox2.Width;//获取groupBox2的宽度
            groupBox2_Y = groupBox2.Height;//获取groupBox2的高度
            setTag(groupBox2);//调用方法
            groupBox3_X = groupBox3.Width;//获取groupBox3的宽度
            groupBox3_Y = groupBox3.Height;//获取groupBox3的高度
            setTag(groupBox3);//调用方法
            groupBox5_X = groupBox5.Width;//获取groupBox5的宽度
            groupBox5_Y = groupBox5.Height;//获取groupBox5的高度
            setTag(groupBox5);//调用方法
            groupBox6_X = groupBox6.Width;//获取groupBox6的宽度
            groupBox6_Y = groupBox6.Height;//获取groupBox6的高度
            setTag(groupBox6);//调用方法
        }

        private float groupBox2_X;//记录当前groupBox2的宽度
        private float groupBox2_Y;//记录当前groupBox2的高度
        private float groupBox3_X;//记录当前groupBox3的宽度
        private float groupBox3_Y;//记录当前groupBox3的高度
        private float groupBox5_X;//记录当前groupBox5的宽度
        private float groupBox5_Y;//记录当前groupBox5的高度
        private float groupBox6_X;//记录当前groupBox6的宽度
        private float groupBox6_Y;//记录当前groupBox6的高度

        /// <summary>
        /// 将父容器的宽，高，左边距，顶边距和字体大小暂存到tag属性中
        /// </summary>
        /// <param name="cons">递归控件中的控件</param>
        private void setTag(Control cons)
        {
            foreach (Control con in cons.Controls)
            {
                con.Tag = con.Width + ":" + con.Height + ":" + con.Left + ":" + con.Top + ":" + con.Font.Size;
                if (con.Controls.Count > 0)
                    setTag(con);
            }
        }
        //根据窗体大小调整控件大小
        private void setControls(float newx, float newy, Control cons)
        {
            //遍历窗体中的控件，重新设置控件的值
            foreach (Control con in cons.Controls)
            {
                string[] mytag = con.Tag.ToString().Split(new char[] { ':' });//获取控件的Tag属性值，并分割后存储字符串数组
                float a = System.Convert.ToSingle(mytag[0]) * newx;//根据窗体缩放比例确定控件的值，宽度
                con.Width = (int)a;//宽度
                a = System.Convert.ToSingle(mytag[1]) * newy;//高度
                con.Height = (int)(a);
                a = System.Convert.ToSingle(mytag[2]) * newx;//左边距离
                con.Left = (int)(a);
                a = System.Convert.ToSingle(mytag[3]) * newy;//上边缘距离
                con.Top = (int)(a);
                //Single currentSize = System.Convert.ToSingle(mytag[4]) * newy;//字体大小
                //con.Font = new Font(con.Font.Name, currentSize, con.Font.Style, con.Font.Unit);
                if (con.Controls.Count > 0)
                {
                    setControls(newx, newy, con);
                }
            }
        }

        private void groupBox2_Resize(object sender, EventArgs e)
        {
            float newx = (groupBox2.Width) / groupBox2_X; //groupBox2宽度缩放比例
            float newy = (groupBox2.Height) / groupBox2_Y;//groupBox2高度缩放比例
            setControls(newx, newy, groupBox2);//随groupBox2改变控件大小
        }

        private void groupBox3_Resize(object sender, EventArgs e)
        {
            float newx = (groupBox3.Width) / groupBox3_X; //groupBox3宽度缩放比例
            float newy = (groupBox3.Height) / groupBox3_Y;//groupBox3高度缩放比例
            setControls(newx, newy, groupBox3);//随groupBox3改变控件大小
        }

        private void groupBox5_Resize(object sender, EventArgs e)
        {
            float newx = (groupBox5.Width) / groupBox5_X; //groupBox5宽度缩放比例
            float newy = (groupBox5.Height) / groupBox5_Y;//groupBox5高度缩放比例
            setControls(newx, newy, groupBox5);//随groupBox5改变控件大小
        }

        private void groupBox6_Resize(object sender, EventArgs e)
        {
            float newx = (groupBox6.Width) / groupBox6_X; //groupBox6宽度缩放比例
            float newy = (groupBox6.Height) / groupBox6_Y;//groupBox6高度缩放比例
            setControls(newx, newy, groupBox6);//随groupBox6改变控件大小
        }

        private void treeView1_AfterSelect(object sender, TreeViewEventArgs e)
        {
            if (e.Action == TreeViewAction.ByMouse)//只针对鼠标操作有效
            {
                #region 运行方案实例
                if (e.Node.Text == "运行方案实例")
                {
                    this.tabControl1.SelectedTab = tabControl1.TabPages[0];
                }
                if (e.Node.Parent != null && e.Node.Parent.Text == "运行方案实例")
                {
                    this.tabControl1.SelectedTab = tabControl1.TabPages[0];
                    DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
                    int runId = Convert.ToInt32(e.Node.Text);
                    textBox1.Text = runId.ToString();//运行ID
                    foreach (RunInstanceInfo runInstanceInfo in runInstanceInfos)
                    {
                        if (runInstanceInfo.RunId == runId)
                        {
                            List<Member> members = runInstanceInfo.Members;
                            textBox2.Text = runInstanceInfo.SchemeId.ToString();//方案ID
                            textBox4.Text = runInstanceInfo.Statue;//状态
                            textBox5.Text = members.Count.ToString();//成员个数
                            this.richTextBox1.Clear();
                            this.richTextBox1.Text = "方案" + runInstanceInfo.SchemeId.ToString() + "的第" + runId + "次运行，该运行实例有" + members.Count + "个成员，" + "运行实例的当前状态为:" + runInstanceInfo.Statue;
                            dataGridView1.Rows.Clear();//先清除再展示
                            DataSet ds;
                            foreach (Member member in members)
                            {
                                int index = this.dataGridView1.Rows.Add();
                                this.dataGridView1.Rows[index].Cells[0].Value = member.Member_id;//成员ID
                                foreach (var itemfather in dictionaryAssignmentNormals)
                                {
                                    Dictionary<String, Dictionary<String, Object>> dictionaryAssignmentNormal = itemfather.Value;
                                    if (itemfather.Key == runId.ToString())
                                    {
                                        foreach (var itemson in dictionaryAssignmentNormal)
                                        {
                                            String queueName = itemson.Key;
                                            Dictionary<String, Object> clientData = itemson.Value;
                                            RunInstanceInfo runInstanceInfoTemp = (RunInstanceInfo)clientData["RunInstanceInfo"];//分配的RunInstanceInfo对象
                                            List<Member> membersTemp = runInstanceInfoTemp.Members;//分配的成员
                                            if (membersTemp.Contains(member)) 
                                            {
                                                ds = db.Query("select * from runlibrary where queueName='" + queueName + "'");
                                                int id = (int)ds.Tables[0].Rows[0][0];
                                                String ip = ds.Tables[0].Rows[0][2].ToString();//IP地址
                                                this.dataGridView1.Rows[index].Cells[2].Value = "运行端" + id;
                                                this.dataGridView1.Rows[index].Cells[3].Value = ip;//IP地址
                                                this.dataGridView1.Rows[index].Cells[4].Value = ds.Tables[0].Rows[0][3];//开始运行时刻
                                            }
                                            Console.WriteLine(queueName);                     
                                        }
                                    }
                                }
                            }
                        }
                    }
                    db.DBClose();//关闭数据库连接
                }
                #endregion

                #region 运行端
                if (e.Node.Text == "运行端")
                {
                    this.tabControl1.SelectedTab = tabControl1.TabPages[1];
                }
                if (e.Node.Parent != null && e.Node.Parent.Text == "运行端")
                {
                    this.tabControl1.SelectedTab = tabControl1.TabPages[1];
                    String result = System.Text.RegularExpressions.Regex.Replace(e.Node.Text, @"[^0-9]+", "");
                    int id = Convert.ToInt32(result);//运行端编号
                    int memberCount = 0;//运行端成员数量
                    DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
                    foreach (var itemfather in dictionaryAssignmentNormals)
                    {
                        Dictionary<String, Dictionary<String, Object>> dictionaryAssignmentNormal = itemfather.Value;
                        foreach (var itemson in dictionaryAssignmentNormal)
                        {
                            if (itemson.Key == ("queue" + id))
                            {
                                Dictionary<String, Object> clientData = itemson.Value;
                                RunInstanceInfo runInstanceInfo = (RunInstanceInfo)clientData["RunInstanceInfo"];
                                memberCount += runInstanceInfo.Members.Count;
                            }
                        }
                    }
                    this.textBox7.Text = id.ToString();//运行ID
                    this.textBox8.Text = memberCount.ToString();//运行端成员数量
                    DataSet ds = db.Query("select * from runlibrary where id=" + id);
                    String ip = ds.Tables[0].Rows[0][2].ToString();//IP地址
                    db.DBClose();//归还连接到数据库连接池
                    textBox9.Text = ip;//ip地址
                    Dictionary<String, String> dictionary = new Dictionary<String, String>();//发送出去的更新指令
                    dictionary.Add("Destination", "Client");//发送给运行端
                    dictionary.Add("ClientID", textBox7.Text);
                    String sendMessage = JsonConvert.SerializeObject(dictionary);
                    MQTopicProducer activeMQProducer = new MQTopicProducer(activeMQServerIP, sendMessage, "SystemInfoRequest");
                }
                #endregion
                #region 统计信息
                if (e.Node.Text == "统计信息")
                {
                    this.tabControl1.SelectedTab = tabControl1.TabPages[2];
                }
                #endregion
            }
        }

    }
}
