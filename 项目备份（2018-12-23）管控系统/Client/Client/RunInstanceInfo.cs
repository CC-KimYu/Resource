using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace Client
{
    [JsonObject(MemberSerialization.OptIn)]//默认所有成员均不序列化 只有标有特性JsonProperty的成员才会被序列化,当类的成员很多,但客户端仅仅需要一部分数据时,很有用
    class RunInstanceInfo
    {
        private String statue;//当前方案的运行状态
        public String Statue
        {
            get { return statue; }
            set { statue = value; }
        }

        [JsonProperty("SchemeId")]
        private int schemeId;//方案ID
        public int SchemeId
        {
            get { return schemeId; }
            set { schemeId = value; }
        }

        [JsonProperty("RunId")]
        private int runId;//运行ID
        public int RunId
        {
            get { return runId; }
            set { runId = value; }
        }

        [JsonProperty("SchemeInfo")]
        private List<Member> members;
        internal List<Member> Members
        {
            get { return members; }
            set { members = value; }
        }


        public RunInstanceInfo()
        {
            members = new List<Member>();
        }
        //打印
        public String toString()
        {
            String str = "{\"Statue\":\"" + this.statue + "\",\"SchemeId\":\"" + this.schemeId + "\",\"RunId\":\"" + this.runId + "\",\"SchemeInfo\":[";
            for (int i = 0; i < members.Count; i++)
            {
                str += members[i].toString();
                if (i != members.Count - 1)
                {
                    str += ",";
                }
            }
            str += "]}";
            return str;
        }
    }
}
