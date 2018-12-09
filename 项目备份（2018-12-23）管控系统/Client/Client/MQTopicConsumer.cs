
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;


namespace Client
{
    class MQTopicConsumer 
    {
        private IConnectionFactory factory;//连接工厂
        private IConnection connection; //jms连接
        private ISession session;//创建session
        private IDestination destination;//消息目的地
        private IMessageConsumer consumer;//消息发送者
        private ITextMessage message;
        public IMessageConsumer ActiveMQInit(String ip, String topicName) 
        {
            try {
                factory = new ConnectionFactory("tcp://" + ip + ":61616/");//获取工厂实例  
                connection = factory.CreateConnection("admin", "admin");
                connection.Start();//启动连接
                session = connection.CreateSession(AcknowledgementMode.AutoAcknowledge);//创建Session并选择事务和ACK模式
                destination = new ActiveMQTopic(topicName);
                consumer = session.CreateConsumer(destination);
                return consumer;
               // consumer.Listener += new MessageListener(consumerListener);
            }catch(Exception e){
                 throw new Exception("初始化ActiveMQConsumer出错，错误信息如下 -->" + e.Message);
            }
        }
    }
}
