using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;
using System.Management;

namespace Client
{
    /// <summary>
    /// windows api 名称
    /// </summary>
    public enum WindowsAPIType
    {
        /// <summary>
        /// 内存
        /// </summary>
        Win32_PhysicalMemory,
        /// <summary>
        /// cpu
        /// </summary>
        Win32_Processor,
        /// <summary>
        /// 硬盘
        /// </summary>
        win32_DiskDrive,
        /// <summary>
        /// 电脑型号
        /// </summary>
        Win32_ComputerSystemProduct,
        /// <summary>
        /// 分辨率
        /// </summary>
        Win32_DesktopMonitor,
        /// <summary>
        /// 显卡
        /// </summary>
        Win32_VideoController,
        /// <summary>
        /// 操作系统
        /// </summary>
        Win32_OperatingSystem

    }
    public enum WindowsAPIKeys
    {
        /// <summary>
        /// 名称
        /// </summary>
        Name,
        /// <summary>
        /// 显卡芯片
        /// </summary>
        VideoProcessor,
        /// <summary>
        /// 显存大小
        /// </summary>
        AdapterRAM,
        /// <summary>
        /// 分辨率宽
        /// </summary>
        ScreenWidth,
        /// <summary>
        /// 分辨率高
        /// </summary>
        ScreenHeight,
        /// <summary>
        /// 电脑型号
        /// </summary>
        Version,
        /// <summary>
        /// 硬盘容量
        /// </summary>
        Size,
        /// <summary>
        /// 内存容量
        /// </summary>
        Capacity,
        /// <summary>
        /// cpu核心数
        /// </summary>
        NumberOfCores
    }
    ///  
    /// 系统信息类 - 获取CPU、内存、磁盘、进程信息 
    ///  
    public class SystemInfo
    {
        private static PerformanceCounter pcCpuLoad;   //CPU计数器 
        private static long m_PhysicalMemory = 0;   //物理内存 

        #region 构造函数
        ///  
        /// 构造函数，初始化计数器等 
        ///  
        static SystemInfo()
        {
            //初始化CPU计数器 
            pcCpuLoad = new PerformanceCounter("Processor", "% Processor Time", "_Total");
            pcCpuLoad.MachineName = ".";
            pcCpuLoad.NextValue();

            //获得物理内存 
            ManagementClass mc = new ManagementClass("Win32_ComputerSystem");
            ManagementObjectCollection moc = mc.GetInstances();
            foreach (ManagementObject mo in moc)
            {
                if (mo["TotalPhysicalMemory"] != null)
                {
                    m_PhysicalMemory = long.Parse(mo["TotalPhysicalMemory"].ToString());
                }
            }
        }
        #endregion

        #region CPU占用率
        ///  
        /// 获取CPU占用率 
        ///  
        public static float CpuLoad
        {
            get
            {
                return pcCpuLoad.NextValue();
            }
        }
        #endregion

        #region 可用内存
        ///  
        /// 获取可用内存(单位:MB) 
        ///  
        public static long MemoryAvailable
        {
            get
            {
                long availablebytes = 0;
                ManagementClass mos = new ManagementClass("Win32_OperatingSystem");
                foreach (ManagementObject mo in mos.GetInstances())
                {
                    if (mo["FreePhysicalMemory"] != null)
                    {
                        availablebytes = 1024 * long.Parse(mo["FreePhysicalMemory"].ToString());
                    }
                }
                return availablebytes / 1024 / 1024;
            }
        }
        #endregion

        #region 物理内存
        ///  
        /// 获取物理内存(单位:MB) 
        ///  
        public static long PhysicalMemory
        {
            get
            {
                return m_PhysicalMemory / 1024 / 1024;
            }
        }
        #endregion

        #region 机器名
        public static string MachineName()
        {
            return Environment.MachineName;
        }
        #endregion

        #region 操作系统
        public static string OSVersion()
        {
            return Environment.OSVersion.ToString();
        }
        #endregion

        #region 责任人
        public static string UserName()
        {
            return Environment.UserName;
        }
        #endregion

        #region 电脑型号
        public static string ComputerVersion()
        {
            string str=null;
            string hdId = string.Empty;
            ManagementClass hardDisk = new ManagementClass(WindowsAPIType.Win32_ComputerSystemProduct.ToString());
            ManagementObjectCollection hardDiskC = hardDisk.GetInstances();
            foreach (ManagementObject m in hardDiskC)
            {
                str = m[WindowsAPIKeys.Version.ToString()].ToString();
                break;
            }
            return str;
        }
        #endregion
    }
}
