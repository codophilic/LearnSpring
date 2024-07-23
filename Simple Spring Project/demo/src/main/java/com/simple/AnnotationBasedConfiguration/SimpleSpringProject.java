package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {


	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		Laptop lapObj=(Laptop) factory.getBean("lap");
		lapObj.start();
		
		Driver driver=(Driver) factory.getBean("driver");
		driver.start();
	}

}
