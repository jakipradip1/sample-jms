package com.jakipradip.sample_jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.jmq.jmsserver.core.Session;

/**
 * Hello world!
 *
 */
public class JMSQueueReceiver2 
{
    public static void main( String[] args ) throws JMSException, NamingException
    {
    	Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		InitialContext ctx = new InitialContext(props);	
		
		QueueConnectionFactory qcFactory = (QueueConnectionFactory) ctx.lookup("jms/QCF");
		QueueConnection qc = null;
		QueueSession session = null;
		Queue queue = null;
		
		try {
			qc = qcFactory.createQueueConnection();
			qc.start();
			System.out.println("Created Queue Connection");
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue)ctx.lookup("jms/MyQueue");
			
			QueueReceiver receiver = session.createReceiver(queue);
			System.out.println("receiver 2 waiting");
			TextMessage message =  (TextMessage) receiver.receive();
			System.out.println(message);
			System.out.println("Message received Sucessfully from receiver 2");
			qc.stop();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(qc!=null){
				qc.close();
			}
			if(session!=null){
				session.close();
			}
		}
		
    }
}
