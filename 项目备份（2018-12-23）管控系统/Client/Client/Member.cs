using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Client
{
    [JsonObject(MemberSerialization.OptIn)]//默认所有成员均不序列化 只有标有特性JsonProperty的成员才会被序列化,当类的成员很多,但客户端仅仅需要一部分数据时,很有用
    class Member
    {
        [JsonProperty("SCHEME_MODEL_ID")]
        private int member_id;//成员编号
        public int Member_id
        {
            get { return member_id; }
            set { member_id = value; }
        }

        [JsonProperty("COM_NAME")]
        private String dll_name;//dll名称
        public String Dll_name
        {
            get { return dll_name; }
            set { dll_name = value; }
        }

        [JsonProperty("MUID")]
        private int model_id;//模型编号
        public int Model_id
        {
            get { return model_id; }
            set { model_id = value; }
        }

        //打印
        public String toString()
        {
            return "{\"SCHEME_MODEL_ID\":\"" + this.member_id + "\",\"MUID\":\"" + this.model_id + "\",\"COM_NAME\":\"" + this.dll_name + "\"}";
        }
    }
}
