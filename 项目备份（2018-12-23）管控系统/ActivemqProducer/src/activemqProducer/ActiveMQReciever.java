package activemqProducer;


import java.io.File;
import java.io.FileInputStream;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;


/**
 * 
 * @author yc
 *	activemq��������
 */
public class ActiveMQReciever {
	MapMessage map = null;
	
	public ActiveMQReciever (ActiveMQConnecter activeMQConnect,String topicName) throws JMSException
	{

		Destination destination = activeMQConnect.getSession().createTopic(topicName);
		System.out.println("����TOPIC:"+topicName);
		MessageConsumer messageConsumer = activeMQConnect.getSession().createConsumer(
				destination);

		messageConsumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				try {
					map = (MapMessage) message;
					System.out.println("�յ�����ָ��");
					String content = map.getString("content");
					System.out.println("content"+content);
					if(content.equals("UpdataPara"))
					{
						String para ="1-x-1";
					//	ControlMember.UpdataPara(para);
					}
					else{
					//	ControlMember.reaction(content);//�������������Ƴ�Ա���ÿ��Ƴ�Աȥ����Ӧ����
					System.out.println("�յ���Ա׼�������Ϣ");
					}
					
					
					} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}
}




