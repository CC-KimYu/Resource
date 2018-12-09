using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client
{
    class Bat
    {
        #region 写bat文件
        public void writeBatFile(String filePath, String content)
        {//content 写入的内容
            if (!File.Exists(filePath)) //不存在对应bat文件
            {
                FileStream fs = new FileStream(filePath, FileMode.Create, FileAccess.Write);
                StreamWriter sw = new StreamWriter(fs);
                sw.WriteLine(content);
                sw.Close();
                fs.Close();
            }
            else
            {
                FileStream fs = new FileStream(filePath, FileMode.Open, FileAccess.Write);
                StreamWriter sw = new StreamWriter(fs);
                sw.WriteLine(content);
                sw.Close();
                fs.Close();
            }
        }
        #endregion

        #region 调用bat文件
        public void runBatFile(String targetDir, String args)
        {
            try
            {
                Process process = new Process();
                FileInfo fileInfo = new FileInfo(targetDir); 
                process.StartInfo.WorkingDirectory = fileInfo.Directory.FullName; ;
                process.StartInfo.FileName = targetDir;
                process.StartInfo.Arguments = String.Format(args);
                //process.StartInfo.CreateNoWindow = true;
                //process.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
                process.Start();
                //process.WaitForExit();
                
            }
            catch (Exception e)
            {
                throw new Exception("调用bat文件出错，错误信息如下 -->" + e.Message);
            }
        }
        #endregion
    }
}
