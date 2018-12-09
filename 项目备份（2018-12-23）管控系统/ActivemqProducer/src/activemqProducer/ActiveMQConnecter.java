package activemqProducer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 
 * @author yc
 *	activemq监听程序
 *	brokerURL :activemq连接信息
 */
public class ActiveMQConnecter {
	
	private static transient ConnectionFactory factory;
	private transient Connection connection;
	private transient Session session;

	public ActiveMQConnecter() throws JMSException, IOException {
		String brokerURL = "tcp://172.16.73.125:61616?jms.producerWindowSize=1048576";
		
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.setClientID("memberDataCollect");
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public void close() throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	public Session getSession() {
		return session;
	}
	public Connection getConnection() {
		return connection;
	}
	
//	public static void main(String[] args) throws JMSException, IOException {
//		ActiveMQConnecter connect = new ActiveMQConnecter();
//		connect.getSession().createTopic("aaaaaaaaaaaa");
//		
//	}
}
