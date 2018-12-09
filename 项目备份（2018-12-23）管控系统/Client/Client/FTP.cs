using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Client
{
    class FTP
    {
        /// <summary>
        /// 连接FTP
        /// </summary>
        /// <param name="ip">FTP连接地址</param>
        /// <param name="remotePath">指定FTP连接成功后的当前目录, 如果不指定即默认为根目录</param>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        private String ip;
        private String username;
        private String password;

        public FTP(String ip, String username, String password)
        {
            this.ip = ip;
            this.username = username;
            this.password = password;
        }

        //下载ftp服务器文件
        public void downloadFile(String remotePath, String fileName, String localPath)
        {
            FtpWebRequest request = null;  //创建一个与FTP服务器联系的FtpWebRequest对象
            FtpWebResponse response = null;//获取一个请求响应对象
            try
            {
                FileStream fileStream = new FileStream(localPath + "/" + fileName, FileMode.Create);
                request = (FtpWebRequest)FtpWebRequest.Create(new Uri(remotePath + "/" + fileName));
                //设置请求的方法是FTP文件下载 
                request.Method = WebRequestMethods.Ftp.DownloadFile;
                //连接登录FTP服务器
                request.Credentials = new NetworkCredential(username, password);
                request.UseBinary = true;
                request.UsePassive = false;
                request.KeepAlive = true;
                //获取一个请求响应对象 
                response = (FtpWebResponse)request.GetResponse();
                if(response==null){
                    Console.WriteLine("服务器无响应");
                    return;
                }
                //获取请求的响应流  
                Stream stream = response.GetResponseStream();
                long cl = response.ContentLength;
                //Console.WriteLine("Response ContentLength is "+cl);
                int bufferSize = 2048;
                int readCount;
                byte[] buffer = new byte[bufferSize];

                readCount = stream.Read(buffer, 0, bufferSize);
                while (readCount > 0)
                {
                    fileStream.Write(buffer, 0, readCount);
                    readCount = stream.Read(buffer, 0, bufferSize);
                }
                Console.WriteLine("正在下载" + fileName + "到" + localPath);
                stream.Close();
                fileStream.Close();
                response.Close();
            }
            catch (Exception e)
            {
                throw new Exception("FTP Download Error -->" + e.Message);
            }
        }
        //获取当前目录下明细（包含文件和文件夹）
        public String[] GetFilesDetailList(String remotePath)
        {
            Console.WriteLine("当前目录" + remotePath);
            FtpWebRequest request = null;  //创建一个与FTP服务器联系的FtpWebRequest对象
            FtpWebResponse response = null;//获取一个请求响应对象
            String[] lists = null; ;
            try
            {
                StringBuilder result = new StringBuilder();
                request = (FtpWebRequest)FtpWebRequest.Create(new Uri(remotePath));
                request.Credentials = new NetworkCredential(username, password);
                //设置请求的方法是返回服务器对应目录的所有文件和文件夹
                request.Method = WebRequestMethods.Ftp.ListDirectoryDetails;
                request.UseBinary = true;
                request.UsePassive = false;
                request.KeepAlive = true;
                response = (FtpWebResponse)request.GetResponse();
                Stream stream = response.GetResponseStream();
                StreamReader streamReader = new StreamReader(stream, Encoding.Default);
                String line = streamReader.ReadLine();
                while (line != null)
                {
                    result.Append(line);
                    result.Append("\n");
                    line = streamReader.ReadLine();
                }
                result.Remove(result.ToString().LastIndexOf("\n"), 1);
                lists = result.ToString().Split('\n');
                streamReader.Close();
                stream.Close();
                response.Close();
                return lists;
            }
            catch (Exception e)
            {
                throw new Exception("获取文件列表出错，错误信息如下 -->" + e.Message);
            }
        }

        //下载ftp服务器文件夹
        public void downloadDicrectory(String remotePath, String localPath)
        {
            if (!Directory.Exists(localPath))
            {
                Directory.CreateDirectory(localPath);
            }
            String[] a = remotePath.Split('/');
            localPath = localPath + "/" + a[a.Length - 1];
            Directory.CreateDirectory(localPath); //本地创建文件夹
            String[] lists = GetFilesDetailList(remotePath);
            foreach (String list in lists)
            {
                Console.WriteLine(list);
                int dirPos = list.IndexOf("drwx");
                int start = list.LastIndexOf(" ") + 1;
                String listNew = list.Substring(start, list.Length - start);
                if (dirPos >= 0) //windows文件夹判断风格
                {
                    if (listNew.IndexOf(".") < 0)
                    {  //不存在特殊文件夹 如.setting 
                        downloadDicrectory(remotePath + "/" + listNew, localPath);
                        Console.WriteLine("下载文件夹" + listNew + "到" + remotePath);
                    }
                    else
                    {
                        Directory.CreateDirectory(localPath + "/" + listNew);
                    }
                }
                else
                {
                    downloadFile(remotePath, listNew, localPath);
                }
            }
        }
    }
}
