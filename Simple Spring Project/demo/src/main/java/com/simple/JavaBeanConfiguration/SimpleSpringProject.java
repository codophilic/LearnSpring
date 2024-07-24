package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;
import com.simple.JavaBeanConfiguration.ComponentBased.Processor;

public class SimpleSpringProject {


	public static void main(String[] args) {
		
		/**
		 * Just like in XML we mentioned the XML file path
		 * Similarly here we need to mentioned our config java class
		 */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Alien al=context.getBean(Alien.class);
        al.show();
        
        Alien a2=context.getBean("objectOfAlien",Alien.class);
        a2.show();        
        
        Alien a3=context.getBean("instanceOfAlien",Alien.class);
        a3.show();
        
        Processor p=context.getBean(Processor.class);
        p.process();

	}

}
