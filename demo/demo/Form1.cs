using demo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Drawing.Drawing2D; 

namespace UI
{

    public partial class Form1 : Form
    {
        private List<Scheme> schemes;   //容器List,存放所有方案信息
        private static Scheme selectedScheme;  //选中的方案 缺省值为schemes[0] 
        public Form1()
        {
            schemes=new List<Scheme>();
            InitializeComponent();
            //采用双缓冲技术的控件必需的设置
            this.SetStyle(ControlStyles.OptimizedDoubleBuffer, true);
            this.SetStyle(ControlStyles.AllPaintingInWmPaint, true);
            this.SetStyle(ControlStyles.UserPaint, true);

            this.Show();
            Load();
            if (schemes.Count>0)
            {
             Console.WriteLine("1111");
             selectedScheme = schemes[0];
             Console.WriteLine(selectedScheme.SchemeID + "  " + selectedScheme.Federationrunnumber + "  " + selectedScheme.SchemeName + "  " + selectedScheme.MemberNumber);
             this.drawSchemeInformation(selectedScheme);
            }
        }

        //生成根节点
        private void BindTreeView(TreeView treeview)
        {
            treeview.Nodes.Clear();
            treeview.ImageList = this.imageList1;  //关联图片列表
            treeview.ItemHeight = 20;
            treeview.Indent = 35;
            foreach(Scheme scheme in schemes){
                    TreeNode rootNode = new TreeNode();
                    rootNode.Name = scheme.Federationrunnumber;
                    rootNode.Text = scheme.SchemeName+"_"+scheme.Federationrunnumber;
                    rootNode.ImageIndex = 0;
                    rootNode.NodeFont = new Font("宋体",11,FontStyle.Bold);
                    treeview.Nodes.Add(rootNode);
                    this.CreateChildNode(rootNode, scheme.Federationrunnumber);
            }
            treeview.ExpandAll();
        }
        //生成子节点
        private void CreateChildNode(TreeNode parentNode, String federationrunnumber)
        {
            foreach (Scheme scheme in schemes)
            {
                foreach(Member member in scheme.SchemeMember){
                    if(scheme.Federationrunnumber.Equals(federationrunnumber)){
                        TreeNode childNode = new TreeNode();
                        childNode.Name = member.Id;
                        childNode.Text = member.Name;
                        childNode.ImageIndex = 1;
                        parentNode.Nodes.Add(childNode);
                    }
                }
            }
        }  
        //计时器
        private void time1_Tick(object sender, EventArgs e)
        {
           
            this.toolStripStatusLabel2.Text = "系统当前时间：" + DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        }

        private void Load()
        {
            this.toolStripStatusLabel2.Text = "系统当前时间：" + DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            this.timer1.Interval = 1000;
            this.timer1.Start();
            this.ParsingJson();
        }
   
        //解析json 并将数据存入List容器内  同时绘制树结构
        private void ParsingJson()
        {
            String json = "[{\"SchemeMember\":[{\"memName\":\"mem1\",\"memID\":\"1\"},{\"memName\":\"mem2\",\"memID\":\"2\"},{\"memName\":\"mem3\",\"memID\":\"3\"}],\"SchemeID\":\"1-1\",\"MemberNumber\":3,\"Federationrunnumber\":\"1\",\"SchemeName\":\"方案1\"},{\"SchemeMember\":[{\"memName\":\"mem1\",\"memID\":\"1\"},{\"memName\":\"mem2\",\"memID\":\"2\"},{\"memName\":\"mem3\",\"memID\":\"3\"},{\"memName\":\"mem4\",\"memID\":\"4\"},{\"memName\":\"mem5\",\"memID\":\"5\"}],\"SchemeID\":\"1-1\",\"MemberNumber\":5,\"Federationrunnumber\":\"2\",\"SchemeName\":\"方案2\"},{\"SchemeMember\":[{\"memName\":\"mem1\",\"memID\":\"1\"},{\"memName\":\"mem2\",\"memID\":\"2\"},{\"memName\":\"mem3\",\"memID\":\"3\"},{\"memName\":\"mem4\",\"memID\":\"4\"},{\"memName\":\"mem5\",\"memID\":\"5\"},{\"memName\":\"mem6\",\"memID\":\"6\"}],\"SchemeID\":\"1-1\",\"MemberNumber\":6,\"Federationrunnumber\":\"3\",\"SchemeName\":\"方案3\"}]";
            /* JObject jo = (JObject)JsonConvert.DeserializeObject(json);
            Scheme scheme = new Scheme();
            scheme.SchemeID = jo["SchemeID"].ToString();
            scheme.MemberNumber = Convert.ToInt32(jo["MemberNumber"].ToString());
            scheme.SchemeName = jo["SchemeName"].ToString();
            scheme.Federationrunnumber = jo["Federationrunnumber"].ToString();
            JArray SchemeMember = JArray.Parse(jo["SchemeMember"].ToString()); 
            List<Member> Members = new List<Member>();*/
            //String json = this.requestHttp();  //请求得到json数据
            if (json != "当前无方案！")   //进入解析json
            {
                Console.WriteLine("获取json成功");
                Console.WriteLine(json);
                JArray ja = (JArray)JsonConvert.DeserializeObject(json);
                Console.WriteLine(ja);
                foreach (JObject ss in ja)
                {
                    //JObject ss = JObject.Parse(ja[k].ToString);
                    Scheme scheme = new Scheme();
                    scheme.SchemeID = ss["SchemeID"].ToString();
                    scheme.SchemeName = ss["SchemeName"].ToString();
                    scheme.Federationrunnumber = ss["Federationrunnumber"].ToString();
                    scheme.MemberNumber = Convert.ToInt32(ss["MemberNumber"].ToString());
                    JArray SchemeMember = JArray.Parse(ss["SchemeMember"].ToString());
                    List<Member> Members = new List<Member>();
                    for (var i = 0; i < SchemeMember.Count; i++)
                    {
                        JObject j = JObject.Parse(SchemeMember[i].ToString());
                        Member member = new Member();
                        member.Id = j["memID"].ToString();
                        member.Name = j["memName"].ToString();
                        Members.Add(member);
                    }
                    scheme.SchemeMember = Members;
                    schemes.Add(scheme);
                }
                foreach (Scheme tempScheme in schemes)
                {
                    Console.WriteLine(tempScheme.SchemeID + "  " + tempScheme.SchemeName + "  " + tempScheme.Federationrunnumber + "  " + tempScheme.MemberNumber);
                    foreach (Member member in tempScheme.SchemeMember)
                    {
                        Console.WriteLine(member.Id + "  " + member.Name);
                    }
                }
                this.BindTreeView(treeView1);
            }
            else {
                Console.WriteLine("方案为空");
            }
      }   

        private void ObtainingServiceInformation(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("http://172.16.73.125:7777/");
        }

        //http请求json
        private String requestHttp()
        {
           String url = "http://172.16.73.125:4444/deliverrun";
           HttpWebResponse response = HttpManage.CreateGetHttpResponse(url);
           StreamReader sr = new StreamReader(response.GetResponseStream(), System.Text.Encoding.UTF8);
           String ms = sr.ReadToEnd();
           Console.WriteLine(ms);
           return ms;
        }   
        private void Rectangle(int width, int height,String str,Graphics graphics)
        {
            Pen myPen = new Pen(Color.Black,2);
            Rectangle rect=new Rectangle(width/20,(9*height/20),width*9/10,(height/10));
            graphics.DrawRectangle(myPen,rect);
            SolidBrush sb = new SolidBrush(Color.WhiteSmoke);
            graphics.FillRectangle(sb,rect);
            Font myFont = new Font("华文行楷",width/80);
            SolidBrush myBrush=new SolidBrush(Color.DarkGray);
            StringFormat sf = new StringFormat();
            sf.Alignment = StringAlignment.Center;
            sf.LineAlignment = StringAlignment.Center;
            graphics.DrawString(str, myFont, myBrush,rect,sf);
        }
        private void Ellipse(int width, int height, int x, int y, int memberNumber, String str, Graphics graphics)
        {
            Pen myPen = new Pen(Color.Black, 2);
            Rectangle rect = new Rectangle(x,y,width*2/(memberNumber*3),height/(memberNumber*4));
            graphics.DrawEllipse(myPen,rect);
            SolidBrush sb = new SolidBrush(Color.DodgerBlue);
            graphics.FillEllipse(sb,rect);
            Font myFont = new Font("华文行楷",width/110);
            SolidBrush myBrush = new SolidBrush(Color.DarkGray);
            StringFormat sf = new StringFormat();
            sf.Alignment = StringAlignment.Center;
            sf.LineAlignment = StringAlignment.Center;
            graphics.DrawString(str,myFont,myBrush,rect,sf);
        }
        private void drawSchemeInformation(Scheme scheme) {
            int width = this.panel1.Width;
            int height = this.panel1.Height;
            Bitmap bmp = new Bitmap(width,height);
            Graphics graphics = Graphics.FromImage(bmp);
            graphics.Clear(this.panel1.BackColor);
            graphics.SmoothingMode = SmoothingMode.HighQuality; //高质量
            graphics.PixelOffsetMode = PixelOffsetMode.HighQuality;//高像素偏移质量
            Pen myPen = new Pen(Color.Black, 2);
            String str1 = scheme.SchemeName+"   " +"Federationrunnumber:" + scheme.Federationrunnumber + " SchemeID:" + scheme.SchemeID + " MemberNumber:"+scheme.MemberNumber;
            Rectangle(width, height,str1,graphics);
            Pen pen1 = new Pen(Color.Gray, 1);
            for (int i = 0; i < scheme.MemberNumber/2;i++ )
            {
                int temp1 =( width / ((scheme.MemberNumber/2)*2) - width / (scheme.MemberNumber * 3))+i*(width/(scheme.MemberNumber/2));
                String str2 = "成员" + (i + 1) + "\n id:" + scheme.SchemeMember[i].Id + "  Name:" + scheme.SchemeMember[i].Name;
                int temp2 = temp1 + width  / (scheme.MemberNumber * 3);
                if(i%2==0){
                   Ellipse(width, height, temp1, height / 4 - height / (scheme.MemberNumber * 4), scheme.MemberNumber, str2, graphics);
                   graphics.DrawLine(pen1, temp2,height/4,temp2,9*height/20);
                }else{
                    Ellipse(width, height, temp1, height / 3 - height / (scheme.MemberNumber * 4), scheme.MemberNumber, str2, graphics);
                    graphics.DrawLine(pen1, temp2, height / 3, temp2, 9 * height / 20);
                }
            }
            for (int i = scheme.MemberNumber / 2; i < scheme.MemberNumber; i++)
            {
                int temp1 = width / ((scheme.MemberNumber - scheme.MemberNumber / 2) * 2) - width / (scheme.MemberNumber * 3) + (i - scheme.MemberNumber / 2) * (width / (scheme.MemberNumber - scheme.MemberNumber / 2));
                String str2 = "成员" + (i + 1) + ":\n id:" + scheme.SchemeMember[i].Id + "  Name:" + scheme.SchemeMember[i].Name;
                int temp2 = temp1 + width / (scheme.MemberNumber * 3);
                if (i % 2 == 0)
                {
                    Ellipse(width, height, temp1, 2 * height / 3, scheme.MemberNumber, str2, graphics);
                    graphics.DrawLine(pen1, temp2, 11 * height / 20, temp2, 2 * height / 3);
                }
                else {
                    Ellipse(width, height, temp1, 3 * height / 4, scheme.MemberNumber, str2, graphics);
                    graphics.DrawLine(pen1, temp2, 11 * height / 20, temp2, 3 * height / 4);
                }
            }
            this.panel1.CreateGraphics().DrawImage(bmp,0,0);
        }

        private void treeView1_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            //清空面板
            this.panel1.Refresh();
            //重绘,需根据特定节点绘制相应图形
            /*
             * 所有重绘应当由树节点的点击事件激发
             * this.drawSchemeInformation(schemes[特定节点]);
             */
            try
            {
                foreach (Scheme scheme in schemes)
                {
                    if (e.Node.Name.Equals(scheme.Federationrunnumber) && e.Node.Level == 0 || e.Node.Level == 1 && e.Node.Parent.Name.Equals(scheme.Federationrunnumber))
                    {
                        selectedScheme = scheme;
                        this.drawSchemeInformation(selectedScheme);
                        e.Node.SelectedImageIndex = e.Node.Level;
                        e.Node.ImageIndex = e.Node.Level;
                    }
                }
            }catch(Exception ex){
                Console.WriteLine(ex);
                MessageBox.Show("点击树发生异常");
            }
        }

        private void panel1_Paint_1(object sender, PaintEventArgs e)
        {
            if (schemes.Count > 0)
            {
             this.drawSchemeInformation(selectedScheme);
            }
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            if (schemes.Count > 0)
            {
                this.panel1.Refresh();
                this.drawSchemeInformation(selectedScheme);
            }
        }
    }
}

