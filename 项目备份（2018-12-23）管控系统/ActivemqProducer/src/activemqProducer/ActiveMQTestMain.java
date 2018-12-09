package activemqProducer;

import java.io.IOException;

import javax.jms.JMSException;

public class ActiveMQTestMain {
	static ActiveMQConnecter connecter;
	static ActiveMQReciever reciever;
	public static void main(String[] args) throws JMSException, IOException {
		// TODO Auto-generated method stub
		connecter = new ActiveMQConnecter();
		
		reciever = new ActiveMQReciever(connecter, "Control_Order58");//创建ActiveMQ接收对象
	}

}
