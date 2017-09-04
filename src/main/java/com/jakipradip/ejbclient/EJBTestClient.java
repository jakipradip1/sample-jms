package com.jakipradip.ejbclient;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jakipradip.ejb.HelloEJB;

public class EJBTestClient {
	public static void main(String[] args) throws NamingException{
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
		InitialContext ctx = new InitialContext(props);	
		
		HelloEJB ejbObj = (HelloEJB) ctx.lookup(HelloEJB.class.getName());
		String result = ejbObj.sayHello();
		System.out.println(result);
	}
}
