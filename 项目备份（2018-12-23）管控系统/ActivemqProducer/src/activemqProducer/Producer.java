package activemqProducer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer{

	ConnectionFactory factory; // 杩炴帴宸ュ巶
	Connection connection;// jms杩炴帴
	Session session;// 鍙戦�併�佹帴鏀剁嚎绋�
	Destination destination;// 娑堟伅鐩殑鍦�
	MessageProducer producer;// 娑堟伅鍙戦�佽��

	public Producer(String msg) {
		try {
			factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://172.16.73.99:61616"); // 鑾峰彇宸ュ巶瀹炰緥
			connection = factory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); // 鍒涘缓Session骞堕�夋嫨浜嬪姟鍜孉CK妯″紡
			//destination = session.createQueue(topicname);
			destination = session.createTopic("SCHEME");
			producer = session.createProducer(destination); // 鍒涘缓鐢熶骇鑰�
			producer.setDeliveryMode(DeliveryMode.PERSISTENT); // 鏄惁涓烘寔涔呭寲
																	// 锛孨ON_PERSISTENT闈炴寔涔呭寲
																	// 锛孭ERSISTENT鎸佷箙鍖�
			connection.start(); // 鎵撳紑Connection
			TextMessage textMessage = session.createTextMessage(msg);
			producer.send(textMessage);
			// 鍏抽棴瀵硅薄
			session.commit();
			producer.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
