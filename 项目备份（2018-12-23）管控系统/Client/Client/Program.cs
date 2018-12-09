using Apache.NMS;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.InteropServices;

namespace Client
{
    public delegate bool ControlCtrlDelegate(int CtrlType);
    class Program
    {
        private static String dataBaseIP = ReadXML.readArg("DataBaseIP");//数据库 ip地址
        private static String dataBaseUsername = ReadXML.readArg("DataBaseUsername");//数据库用户名
        private static String dataBasePassword = ReadXML.readArg("DataBasePassword");//数据库密码
        private static String activeMQServerIP = ReadXML.readArg("ActiveMQServerIP");//ActiveMQ服务器 ip地址
        private static String fTPServerIP = ReadXML.readArg("FTPServerIP");//FTP服务器 ip地址
        private static String fTPUsername = ReadXML.readArg("FTPUsername");//FTP用户名
        private static String fTPPassword = ReadXML.readArg("FTPPassword");//FTP密码
        private static String localPath = ReadXML.readArg("LocalPath"); //本地文件夹(下载的默认路径)
        private static String RTIServerIP = ReadXML.readArg("RTIServerIP");//RTI服务器地址

        private static List<RunInstanceInfo> normalRunInstanceInfos = new List<RunInstanceInfo>();//装普通成员
        private static List<RunInstanceInfo> controlRunInstanceInfos = new List<RunInstanceInfo>();//装控制成员

        [DllImport("kernel32.dll")]
        private static extern bool SetConsoleCtrlHandler(ControlCtrlDelegate HandlerRoutine, bool Add);
        private static ControlCtrlDelegate cancelHandler = new ControlCtrlDelegate(HandlerRoutine);
        private static int id;//该运行端在数据库中的id记录下来
        public static bool HandlerRoutine(int CtrlType)
        {
            if (CtrlType == 0 || CtrlType == 2)
            { //关闭 客户端下线
                DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
                db.Delete("delete from runlibrary where id=" + id);
                db.DBClose();//关闭
            }
            Console.ReadLine();
            return false;
        }
        static void Main(string[] args)
        {
            SetConsoleCtrlHandler(cancelHandler, true);//监控运行端（下线及时清除数据库当前记录）;

            #region 获取本机ip
            String hostname = Dns.GetHostName();
            IPHostEntry iphostentry = Dns.GetHostByName(hostname);
            IPAddress ipaddress = iphostentry.AddressList[0];
            String nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            Console.WriteLine("当前时间：" + nowTime + "\t当前ip：" + ipaddress);
            #endregion

            String deviceType = SystemInfo.ComputerVersion();//电脑型号
            String deviceName = SystemInfo.MachineName();//机器名
            String operatingSystem = SystemInfo.OSVersion();//操作系统
            String principal = SystemInfo.UserName();//责任人
            DB db = new DB(dataBaseIP, dataBaseUsername, dataBasePassword, "runtime_state");
            DataSet ds = db.Query("select id from runlibrary");
            String queueName = null;
            if (ds.Tables[0].Rows.Count == 0)
            {
                id = 1;
                queueName = "queue1";
                //runlibrary为空插入第一行数据
                String sql = "insert into runlibrary values(1,'queue1','" + ipaddress + "','" + nowTime + "','" + deviceType + "','" + deviceName + "','" + operatingSystem + "','" + principal + "')";
                db.Insert(sql);
            }
            else
            { //直接跳到runlibrary最后一行进行操作
                int maxId = (int)db.Query("select max(id) from runlibrary").Tables[0].Rows[0][0];//读出runlibrary当前id的最大值
                id = maxId + 1;//在maxId基础上递增
                queueName = "queue" + id;
                String sql = "insert into runlibrary values(" + id + ",'" + queueName + "','" + ipaddress + "','" + nowTime + "','" + deviceType + "','" + deviceName + "','" + operatingSystem + "','" + principal + "')";
                db.Insert(sql);
            }
            db.DBClose();//关闭数据库连接
           
            MQQueueConsumer mQQueueConsumer = new MQQueueConsumer();
            Console.WriteLine("运行端正在监听调度程序的分配信息 ……");
            Console.WriteLine("运行端正在监听的ActiveMQ为 ……" + queueName);
            mQQueueConsumer.ActiveMQInit(activeMQServerIP, queueName).Listener += new MessageListener(OnMessage);
            MQTopicConsumer activeMQConsumerSystem = new MQTopicConsumer();
            activeMQConsumerSystem.ActiveMQInit(activeMQServerIP, "SystemInfoRequest").Listener += new MessageListener(OnMessageSystemInfo);
            Console.ReadLine();
        }

        //监听分配后的队列
        private static void OnMessage(IMessage message)
        {
            try
            {
                ITextMessage msg = (ITextMessage)message;
                Console.WriteLine("Riceive:" + msg.Text);
                //先判断是否是控制成员的分配信息
                JObject jobject = (JObject)JsonConvert.DeserializeObject(msg.Text);
                String Ordertype = jobject["OrderType"].ToString();//指令类型
                #region 运行
                if (Ordertype == "Run") //根据发送的Run指令 进行首次的运行端下载相关功能
                {
                    String MemberType = jobject["MEMBER_TYPE"].ToString();//成员类型
                    JObject jobjectRunInstanceInfo = (JObject)jobject["RunInstanceInfo"];
                    Console.WriteLine("成员的类型状态为" + MemberType);

                    #region 控制成员
                    if (MemberType == "Contorl")//如果要下载的成员类型是控制成员，则至进行控制成员的特殊下载操作
                    {//该运行端分配到了控制成员
                        int scheme_id = (int)jobjectRunInstanceInfo["SchemeId"];
                        int run_id = (int)jobjectRunInstanceInfo["RunId"];
                        RunInstanceInfo runInstanceInfo = new RunInstanceInfo();
                        runInstanceInfo.RunId = run_id;//运行ID
                        runInstanceInfo.SchemeId = scheme_id;//方案ID
                        controlRunInstanceInfos.Add(runInstanceInfo);//添加到controlRunInstanceInfos集合  重启时用到该记录
                        Console.WriteLine("运行ID:" + run_id + "  方案ID:" + scheme_id);

                        String remotepath = "ftp://" + fTPServerIP;
                        FTP ftp = new FTP(fTPServerIP, fTPUsername, fTPPassword);
                        String controlMemberPath = localPath + "/" + run_id + "/" + "ControlMember";//控制成员的路径，也是方案的最外层路径，用来存放仿真中公共的文件
                        if (!Directory.Exists(controlMemberPath))
                        {
                            Directory.CreateDirectory(controlMemberPath);
                        }
                        ftp.downloadFile(remotepath, "ControlMemberV1516_fat.jar", controlMemberPath);//下载通用成员

                        String schemeFilePath = remotepath + "/" + "scanario" + "/" + scheme_id;//方案文件路径，也是Fed文件路径

                        ftp.downloadFile(schemeFilePath, "Config.xml", controlMemberPath);
                        ftp.downloadFile(schemeFilePath, "FED.xml", controlMemberPath);

                        Console.WriteLine("方案文件路径为" + schemeFilePath);

                        String runID = run_id + "";//运行id
                        String SchemePath = controlMemberPath + "/Config.xml";//方案文件路径 
                        String FedPath = controlMemberPath + "/FED.xml";//联邦配置文件路径
                        //启动数据收集成员
                        Bat ControlMemberbat = new Bat();
                        String content1 = "java -jar " + controlMemberPath + "/ControlMemberV1516_fat.jar" + "  " + runID + "  " + SchemePath + "  " + RTIServerIP + "  " + FedPath + "\n exit";
                        ControlMemberbat.writeBatFile(controlMemberPath + "/ControlMemberV1516_fat.jar.bat", content1);
                        ControlMemberbat.runBatFile(controlMemberPath + "/ControlMemberV1516_fat.jar.bat", "");

                    }
                    #endregion

                    #region 普通成员
                    if (MemberType == "Normal") //表示此类成员是仿真成员，进行正常的成员下载操作
                    {
                        //解析分配方案对应json
                        int scheme_id = (int)jobjectRunInstanceInfo["SchemeId"];
                        int run_id = (int)jobjectRunInstanceInfo["RunId"];
                        RunInstanceInfo runInstanceInfo = new RunInstanceInfo();
                        runInstanceInfo.RunId = run_id;//运行ID
                        runInstanceInfo.SchemeId = scheme_id;//方案ID
                        JArray schemeInfo = (JArray)jobjectRunInstanceInfo["SchemeInfo"];
                        List<Member> members = new List<Member>();
                        foreach (JObject jo in schemeInfo)
                        {
                            int member_id = (int)jo["SCHEME_MODEL_ID"];//成员ID
                            String dll_name = jo["COM_NAME"].ToString();//DLL名字
                            int model_id = (int)jo["MUID"];//模型ID
                            Member member = new Member();
                            member.Member_id = member_id;//成员ID
                            member.Dll_name = dll_name;//DLL名字
                            member.Model_id = model_id;//模型ID
                            members.Add(member);
                        }
                        runInstanceInfo.Members = members;//所有成员
                        normalRunInstanceInfos.Add(runInstanceInfo);//添加到normalRunInstanceInfos重启时使用

                        //根据对应topicName下载相应文件
                        String remotepath = "ftp://" + fTPServerIP;
                        FTP ftp = new FTP(fTPServerIP, fTPUsername, fTPPassword);

                        //创建以运行端id+成员id命名的文件夹 存放对应资源文件
                        String dllPath = remotepath + "/" + "Model";//模型文件路径
                        Console.WriteLine("模型文件路径" + dllPath);
                        Bat bat = new Bat();//实例化一个批处理对象
                        for (int i = 0; i < runInstanceInfo.Members.Count; i++)
                        {
                            Member member = runInstanceInfo.Members[i];//成员对象
                            String SimulationMemberPath = localPath + "/" + runInstanceInfo.RunId + "/" + member.Member_id;
                            //下载模型文件
                            if (!Directory.Exists(SimulationMemberPath))
                            {
                                Directory.CreateDirectory(SimulationMemberPath);
                            }

                            Console.WriteLine("11111111");
                            ftp.downloadFile(dllPath, member.Dll_name, SimulationMemberPath);
                            //DLL名

                            Console.WriteLine("22222222");

                            String schemeFilePath = remotepath + "/" + "scanario" + "/" + scheme_id;//方案文件路径，也是Fed文件路径

                            //下载通用成员文件
                            ftp.downloadFile(remotepath, "UniversalMemberV1516_fat.jar", SimulationMemberPath);
                            Console.WriteLine("=====================");
                            //下载方案文件
                            ftp.downloadFile(schemeFilePath, "Config.xml", SimulationMemberPath);
                            ftp.downloadFile(schemeFilePath, "FED.xml", SimulationMemberPath);

                            String arg0 = runInstanceInfo.RunId + "";//运行id
                            String arg1 = member.Member_id.ToString();//成员id
                            String arg2 = SimulationMemberPath + "/Config.xml";//方案文件路径  
                            String arg3 = SimulationMemberPath;//方案成员路径
                            String arg4 = member.Dll_name;//DLL名
                            String arg5 = RTIServerIP;//RTI服务器地址
                            String FedPath = SimulationMemberPath + "/FED.xml";

                            String content = "java -jar " + SimulationMemberPath + "/UniversalMemberV1516_fat.jar" + "  " + arg0 + "  " + arg1 + "  " + arg2 + "  " + arg3 + "  " + arg4 + "  " + arg5 + "  " + FedPath + "\n pause";
                            bat.writeBatFile(SimulationMemberPath + "/runUniversalMemberV1516_fat.bat", content);
                            bat.runBatFile(SimulationMemberPath + "/runUniversalMemberV1516_fat.bat", "");

                        }
                    }
                    #endregion
                }
                #endregion
                #region 重启
                if (Ordertype == "Restart")//根据调度程序的Restart指令  进行重启各个运行端(无需下载相关操作  只需调用bat文件即可)
                {
                    Console.WriteLine("正在重启运行端的方案……");
                    JObject jobjectRunInstanceInfo = (JObject)jobject["RunInstanceInfo"];
                    int run_id = (int)jobjectRunInstanceInfo["RunId"];//运行ID
                    Bat bat = new Bat();//实例化一个批处理对象
                    //重启普通成员
                    foreach (RunInstanceInfo runInstanceInfo in normalRunInstanceInfos)
                    {
                        Console.WriteLine(normalRunInstanceInfos.Count);
                        if (runInstanceInfo.RunId == run_id)
                        {
                            foreach (Member member in runInstanceInfo.Members)
                            {
                                String SimulationMemberPath = localPath + "/" + runInstanceInfo.RunId + "/" + member.Member_id;
                                bat.runBatFile(SimulationMemberPath + "/runUniversalMemberV1516_fat.bat", "");
                            }
                        }
                    }
                    Console.WriteLine(controlRunInstanceInfos.Count);
                    //重启控制成员
                    foreach (RunInstanceInfo runInstanceInfo in controlRunInstanceInfos)//遍历判断是否运行端被分配了控制成员
                    {
                        if (runInstanceInfo.RunId == run_id)//表示该运行端分配到了控制成员
                        {
                            String controlMemberPath = localPath + "/" + runInstanceInfo.RunId + "/" + "ControlMember";//控制成员的路径，也是方案的最外层路径，用来存放仿真中公共的文件
                            bat.runBatFile(controlMemberPath + "/ControlMemberV1516_fat.jar.bat", "");
                        }
                    }
                }
                #endregion
            }
            catch (Exception e)
            {
                Console.WriteLine("监听出错，错误信息如下 -->" + e.Message);
            }
        }

        #region 监听前端发送过来的获取运行端系统CPU 内存信息
        private static void OnMessageSystemInfo(IMessage message)
        {
            try
            {
                ITextMessage msg = (ITextMessage)message;
                Console.WriteLine("Riceive:" + msg.Text);
                JObject jobject = (JObject)JsonConvert.DeserializeObject(msg.Text);
                int clientID = (int)jobject["ClientID"];
                String destination = jobject["Destination"].ToString();
                if (id == clientID && destination == "Client")
                {
                    Dictionary<String, String> dictionary = new Dictionary<String, String>();
                    dictionary.Add("PhysicalMemory", SystemInfo.PhysicalMemory.ToString());//物理内存
                    dictionary.Add("MemoryAvailable", SystemInfo.MemoryAvailable.ToString());//可用内存
                    dictionary.Add("CpuLoad", SystemInfo.CpuLoad.ToString());//CPU占用率
                    String sendMessage = JsonConvert.SerializeObject(dictionary);
                    MQTopicProducer activeMQProducer = new MQTopicProducer(activeMQServerIP, sendMessage, "SystemInfoResponse");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("错误信息-->" + ex.Message);
            }
        }
        #endregion
    }
}