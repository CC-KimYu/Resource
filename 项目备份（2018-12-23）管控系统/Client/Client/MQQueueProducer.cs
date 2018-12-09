using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;

namespace Client
{
    class MQQueueProducer
    {
        private IConnectionFactory factory;//连接工厂
        private IConnection connection; //jms连接
        private ISession session;//创建session
        private IDestination destination;//消息目的地
        private IMessageProducer producer;//消息发送者
        private ITextMessage message;
        public MQQueueProducer(String ip, String sendMessage, String queueName)
        {
            try
            {
                factory = new ConnectionFactory("tcp://" + ip + ":61616/");//获取工厂实例  
                connection = factory.CreateConnection("admin", "admin");
                session = connection.CreateSession(AcknowledgementMode.AutoAcknowledge);//创建Session并选择事务和ACK模式
                destination = new ActiveMQQueue(queueName);//通过会话创建生产者，方法里面new出来的是MQ中的queue
                producer = session.CreateProducer(destination);
                producer.DeliveryMode = MsgDeliveryMode.NonPersistent;//是否为持久化 ，NON_PERSISTENT非持久化 ，PERSISTENT持久化 ActiveMQ服务器停止工作后，消息不再保留
                producer.Priority = MsgPriority.Normal;
                producer.TimeToLive = TimeSpan.MinValue;
                //创建一个发送的消息对象
                message = producer.CreateTextMessage();
                //给这个对象赋实际的消息
                message.Text = sendMessage;
                Console.WriteLine("Sending:" + sendMessage);
                producer.Send(message);

                producer.Close();
                session.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                throw new Exception("初始化ActiveMQProducer出错，错误信息如下 -->" + e.Message);
            }
        }
    }
}
