using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using System.Windows;

namespace demo
{
        class HttpManage
        {
            /// <Get>  
            /// 创建GET方式的HTTP请求  
            /// </Get>  
            public static System.Net.HttpWebResponse CreateGetHttpResponse(string url)
            {
                HttpWebRequest request = null;
                if (url.StartsWith("https", StringComparison.OrdinalIgnoreCase))
                {
                    //对服务端证书进行有效性校验（非第三方权威机构颁发的证书，如自己生成的，不进行验证，这里返回true）
                    ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(CheckValidationResult);
                    request = WebRequest.Create(url) as HttpWebRequest;
                    request.ProtocolVersion = HttpVersion.Version10;    //http版本，默认是1.1,这里设置为1.0
                }
                else
                {
                    request = WebRequest.Create(url) as HttpWebRequest;
                    request.ContentType = "application/x-www-form-urlencoded";
                }
                request.Method = "GET";

                //设置代理UserAgent和超时
                //request.UserAgent = userAgent;
                //request.Timeout = timeout;

                HttpWebResponse res;
                try
                {
                    res = (HttpWebResponse)request.GetResponse();
                }
                catch (WebException ex)
                {
                    res = (HttpWebResponse)ex.Response;
                }
                return res;
            }

            /// <Post>  
            /// 创建POST方式的HTTP请求  
            /// </Post>  
            public static HttpWebResponse CreatePostHttpResponse(string url, string dataaa)
            {
                HttpWebRequest request = null;
                request = WebRequest.Create(url) as HttpWebRequest;
                request.Method = "POST";
                request.ContentType = "application/octet-stream";

                //发送POST数据  
                byte[] data = Encoding.GetEncoding("utf-8").GetBytes(dataaa);

                try
                {
                    using (Stream stream = request.GetRequestStream())
                    {
                        stream.Write(data, 0, data.Length);
                    }

                }
                catch (WebException ex)
                {

                }

                HttpWebResponse response;
                try
                {
                    response = (HttpWebResponse)request.GetResponse();
                }
                catch (WebException ex)
                {
                    response = (HttpWebResponse)ex.Response;
                }
                return response;
            }

            /// <summary>
            /// 获取请求的数据
            /// </summary>
            public static string GetResponseString(HttpWebResponse webresponse)
            {
                using (Stream s = webresponse.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(s, Encoding.UTF8);
                    return reader.ReadToEnd();

                }
            }

            /// <summary>
            /// 验证证书
            /// </summary>
            private static bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
            {
                if (errors == SslPolicyErrors.None)
                    return true;
                return false;
            }
        }
}
