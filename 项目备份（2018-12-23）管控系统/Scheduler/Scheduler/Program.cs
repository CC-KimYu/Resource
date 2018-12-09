using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using Apache.NMS;
using System.Data;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

namespace Scheduler
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}
