package activemqProducer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.jms.JMSException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import org.apache.activemq.broker.Connection;

import com.alibaba.fastjson.JSONObject;

import net.sf.json.JSONArray;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Control extends JFrame{
	public Control() {
		JFrame a = new JFrame();
		a.setSize(400,400);
		//a.show();
		
		JPanel panel = new JPanel();
		//getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(193, 45, 93, 29);
		panel.add(textPane);
		
		JButton btnNewButton = new JButton("\u5F00\u542F\u65B0\u4EFF\u771F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject startJson = new JSONObject();
				startJson.put("SchemeId", "41");
				startJson.put("RunId", "71");
				startJson.put("Ordertype", "Run/Restart");
				JSONArray memberInfo = new JSONArray();
				JSONObject temp = new JSONObject();
				temp.put("SCHEME_MODEL_ID", 1);
				temp.put("MUID", 107);
				temp.put("COM_NAME", "B.dll");
				memberInfo.add(temp);
				JSONObject temp1 = new JSONObject();
				temp1.put("SCHEME_MODEL_ID", 2);
				temp1.put("MUID", 108);
				temp1.put("COM_NAME", "C.dll");
				memberInfo.add(temp1);
				JSONObject temp2 = new JSONObject();
				temp1.put("SCHEME_MODEL_ID", 3);
				temp1.put("MUID", 108);
				temp1.put("COM_NAME", "C.dll");
				memberInfo.add(temp1);
				
				startJson.put("SchemeInfo", memberInfo);
				
				System.out.println(startJson.toString());
				
				
				Producer2 producer = new Producer2(startJson.toString(),"SCHEME",0);
				
				try {
					 ActiveMQConnecter con = new ActiveMQConnecter();
					ActiveMQReciever recieve1 = new ActiveMQReciever(con, "Status1");
					
				} catch (JMSException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(46, 45, 105, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5F00\u59CB\u5F53\u524D\u4EFF\u771F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String runID = textPane.getText().toString();
			//	String runID = "1";
				JSONObject startJson = new JSONObject();
				startJson.put("Control_Order", "Start");
				Producer2 producer2 = new Producer2(startJson.toString(),"Control_Order"+runID,1);
					System.out.println("发送"+"Control_Order"+runID+"  控制信号为Start");
			}
		});
		btnNewButton_1.setBounds(46, 122, 117, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u6682\u505C\u5F53\u524D\u4EFF\u771F");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String runID = textPane.getText().toString();
//				Producer2 producer3 = new Producer2("Pause","Control_Order"+runID);
			//	String runID = "1";
				JSONObject pauseJson = new JSONObject();
				pauseJson.put("Control_Order", "Pause");
				Producer2 producer2 = new Producer2(pauseJson.toString(),"Control_Order"+runID,1);
				//Producer2 producer2 = new Producer2("Pause","Control_Order"+runID,1);
			}
		});
		btnNewButton_2.setBounds(193, 122, 116, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u7ED3\u675F\u5F53\u524D\u4EFF\u771F");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String runID = textPane.getText().toString();
//				Producer2 producer4 = new Producer2("Stop","Control_Order"+runID);
			//	String runID = "1";
				JSONObject stopJson = new JSONObject();
				stopJson.put("Control_Order", "Stop");
				Producer2 producer2 = new Producer2(stopJson.toString(),"Control_Order"+runID,1);
			//	Producer2 producer2 = new Producer2("Stop","Control_Order"+runID,1);
			}
		});
		btnNewButton_3.setBounds(192, 183, 117, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u7EE7\u7EED\u5F53\u524D\u4EFF\u771F");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String runID = textPane.getText().toString();
//				Producer2 producer2 = new Producer2("Continue","Control_Order"+runID);
			//	String runID = "1";
				JSONObject ContinueJson = new JSONObject();
				ContinueJson.put("Control_Order", "Continue");
				Producer2 producer2 = new Producer2(ContinueJson.toString(),"Control_Order"+runID,1);
			//	Producer2 producer2 = new Producer2("Continue","Control_Order"+runID,1);
			}
		});
		btnNewButton_4.setBounds(46, 183, 117, 23);
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u4EFF\u771F\u7684\u8FD0\u884C\u53F7\u4E3A");
		lblNewLabel.setBounds(188, 22, 110, 15);
		panel.add(lblNewLabel);
		
		
		
		a.getContentPane().add(panel);
		a.setVisible(true);
		a.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Control();
	}
}
