package com.simple.BeanLifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/BeanLifeCycle/springConfig.xml");
	    
		/**
		 * Method Got initialize
		 */
		XMLBasedLC obj1=factory.getBean("xmlBasedObj",XMLBasedLC.class);
		
		/**
		 * Did some process
		 */
	    obj1.operation();
	    
	    /**
	     * triggering the shutdown process for the Spring IoC (Inversion of Control) container.
	     * The close() method on ClassPathXmlApplicationContext (which implements ConfigurableApplicationContext) 
	     * stops the IoC container, releasing all resources and locks. 
	     * This is crucial for releasing resources like database connections, file handles, 
	     * or network sockets. 
	     */
	    ((ClassPathXmlApplicationContext) factory).close();
	    
		ApplicationContext factory1 = new ClassPathXmlApplicationContext("com/simple/BeanLifeCycle/springConfig.xml");

	    
	    AnnotationBasedLC alc=factory1.getBean(AnnotationBasedLC.class);
	    alc.operation();
	    
	    ((ClassPathXmlApplicationContext) factory1).close();
	    
		ApplicationContext factory2 = new ClassPathXmlApplicationContext("com/simple/BeanLifeCycle/springConfig.xml");

		InterfaceApproach Ia=factory2.getBean("interfaceBased",InterfaceApproach.class);
		
		Ia.operation();
		
	    ((ClassPathXmlApplicationContext) factory2).close();


	}
}
