package com.jakipradip.sample_jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.jmq.jmsserver.core.Session;

/**
 * Hello world!
 *
 */
public class JMSSubscriber2 
{
    public static void main( String[] args ) throws JMSException, NamingException
    {
    	Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		InitialContext ctx = new InitialContext(props);	
		
		TopicConnectionFactory tcFactory = (TopicConnectionFactory) ctx.lookup("jms/tcf");
		TopicConnection tc = null;
		TopicSession session = null;
		Topic topic = null;
		
		try {
			tc = tcFactory.createTopicConnection();
			tc.start();
			System.out.println("Created TOpic Connection");
			session = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			topic = (Topic)ctx.lookup("jms/MyTopic");
			
			TopicSubscriber sub = session.createSubscriber(topic);
			while(true){
				TextMessage msg = (TextMessage) sub.receive();
				System.out.println((msg!=null)?msg:"No Message");
				System.out.println("Message subscribed(2) from topic Sucessfully");
			}
			
//			tc.stop();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(tc!=null){
				tc.close();
			}
			if(session!=null){
				session.close();
			}
		}
		
    }
}
