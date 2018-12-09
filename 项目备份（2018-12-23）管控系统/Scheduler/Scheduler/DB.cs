using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace Scheduler
{
    class DB
    {
        //private String connetString;
        private MySqlConnection conn;
        private MySqlCommand cmd;
        MySqlConnectionStringBuilder mySqlConnectionStringBuilder;
        public DB(String ip, String username, String password, String database)
        {
            try
            {
                mySqlConnectionStringBuilder = new MySqlConnectionStringBuilder();
                mySqlConnectionStringBuilder.Pooling = true;//开启连接池
                mySqlConnectionStringBuilder.MinimumPoolSize = 0;//设置最小连接数
                mySqlConnectionStringBuilder.MaximumPoolSize = 50;//设置最大连接数为50   
                mySqlConnectionStringBuilder.ConnectionTimeout = 10;//设置超时时间为10秒
                mySqlConnectionStringBuilder.Server = ip;//mysql远程服务器ip地址
                mySqlConnectionStringBuilder.Database = database;//mysql远程服务器数据库名称
                mySqlConnectionStringBuilder.UserID = username;//mysql远程服务器账号
                mySqlConnectionStringBuilder.Password = password;//mysql远程服务器密码
                mySqlConnectionStringBuilder.CharacterSet = "utf8";//mysql远程服务器编码格式
              /*  connetString = "server=" + ip + ";database=" + database
+ ";port=3306;userid=" + username + ";password=" + password + ";charset='utf8';pooling=true";
                // server= 代表ip地址，端口号port默认是3306可以不写
                conn = new MySqlConnection(connetString); */
                conn = new MySqlConnection(mySqlConnectionStringBuilder.ConnectionString);
                Console.WriteLine("连接数据库成功");
                conn.Open();
            }
            catch (Exception e)
            {
                throw new Exception("连接数据库失败 -->" + e.Message);
            }
        }

        //关闭数据库连接
        public void DBClose()
        {
            if (conn.State == ConnectionState.Open || conn.State == ConnectionState.Broken)
            {
                conn.Close();//关闭连接
                conn.Dispose();//释放资源
                Console.WriteLine("退出数据库成功");
            }
        }

        //插入数据
        public int Insert(String sql)
        {
            try
            {
                cmd = new MySqlCommand(sql, conn);
                int result = cmd.ExecuteNonQuery();
                Console.WriteLine("成功插入" + result + "行数据");
                return result;
            }
            catch (Exception e)
            {
                throw new Exception("更新数据出错，错误信息如下 -->" + e.Message);
            }
        }

        //删除数据
        public int Delete(String sql)
        {
            try
            {
                cmd = new MySqlCommand(sql, conn);
                int result = cmd.ExecuteNonQuery();
                Console.WriteLine("成功删除" + result + "行数据");
                return result;
            }
            catch (Exception e)
            {
                throw new Exception("删除数据出错，错误信息如下 -->" + e.Message);
            }
        }

        //更新数据
        public int Updata(String sql)
        {
            try
            {
                cmd = new MySqlCommand(sql, conn);
                int result = cmd.ExecuteNonQuery();
                Console.WriteLine("成功更新" + result + "数据");
                return result;
            }
            catch (Exception e)
            {
                throw new Exception("更新数据出错，错误信息如下 -->" + e.Message);
            }
        }

        //查询数据
        public DataSet Query(String sql)
        {
            try
            {
                cmd = new MySqlCommand(sql, conn);
                MySqlDataAdapter adap = new MySqlDataAdapter(cmd);
                DataSet ds = new DataSet();
                adap.Fill(ds);
                Console.WriteLine("成功查询出结果");
                return ds;
            }
            catch (Exception e)
            {
                throw new Exception("查询结果出错，错误信息如下 -->" + e.Message);
            }
        }
    }
}
