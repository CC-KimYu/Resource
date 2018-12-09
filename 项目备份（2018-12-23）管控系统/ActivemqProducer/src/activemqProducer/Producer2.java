package activemqProducer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer2{

	ConnectionFactory factory; // 杩炴帴宸ュ巶
	Connection connection;// jms杩炴帴
	Session session;// 鍙戦�併�佹帴鏀剁嚎绋�
	Destination destination;// 娑堟伅鐩殑鍦�
	MessageProducer producer;// 娑堟伅鍙戦�佽��

	public Producer2(String msg,String topicname,int a) {
		try {
			factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://172.16.73.125:61616"); // 鑾峰彇宸ュ巶瀹炰緥
			connection = factory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); // 鍒涘缓Session骞堕�夋嫨浜嬪姟鍜孉CK妯″紡
		//	destination = session.createQueue(topicname);
			destination = session.createTopic(topicname);
			producer = session.createProducer(destination); // 鍒涘缓鐢熶骇鑰�
			producer.setDeliveryMode(DeliveryMode.PERSISTENT); // 鏄惁涓烘寔涔呭寲
																	// 锛孨ON_PERSISTENT闈炴寔涔呭寲
//														// 锛孭ERSISTENT鎸佷箙鍖�
			if(a==0){
			connection.start(); // 鎵撳紑Connection
			TextMessage textMessage = session.createTextMessage(msg);
			producer.send(textMessage);
			}
//			
			else{
				Message message = createMessage(msg, session);
			//	System.out.println("-----------------");
				producer.send(destination, message);
				System.out.println("发送消息"+message.toString());
			}
			// 鍏抽棴瀵硅薄
			session.commit();
			producer.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	protected Message createMessage(String msg, Session session)
			throws JMSException {

		MapMessage message = session.createMapMessage();
	//	message.setString("id",msg);
		message.setString("content",msg);
		return message;
	}
}
