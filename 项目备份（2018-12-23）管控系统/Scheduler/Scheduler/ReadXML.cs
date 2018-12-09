using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Scheduler
{
    class ReadXML
    {
        private static XmlDocument document = init();
        //初始化一个 XmlDocument对象
        private static XmlDocument init()
        {
            XmlDocument document = new XmlDocument();
            document.Load(@"../../args.xml");
            return document;
        }

        //读取数据库ip地址
        public static String readArg(String arg)
        {
            try
            {
                //传入的参数必须和xml文件节点对应 否则无法读取
                XmlNode xmlnode = document.SelectSingleNode("Args");
                String temp;
                if (xmlnode != null)
                {
                    temp = xmlnode.SelectSingleNode(arg).InnerText;
                    return temp;
                }
                return null;
            }
            catch (Exception e)
            {
                throw new Exception("读取xml文件出错,错误信息如下 -->" + e.Message);
            }
        }
    }
}
