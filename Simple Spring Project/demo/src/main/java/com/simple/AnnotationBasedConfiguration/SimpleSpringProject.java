package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SimpleSpringProject {


	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		/**
		 * Singleton and ProtoType
		 */
		
		Alien al1=factory.getBean(Alien.class);
		System.out.println("Hash Code for First Object - "+al1.hashCode());
		
		Alien al2=factory.getBean(Alien.class);
		System.out.println("Hash Code for Second Object - "+al2.hashCode());
		
		/**
		 * Component and Autowired
		 */
		Laptop lapObj=(Laptop) factory.getBean("lap");
		lapObj.start();
		
		/**
		 * Primary and Qualifier
		 */
		Driver driver=(Driver) factory.getBean("driver");
		driver.start();
		
		/**
		 * Values and Spring Expression Language (SpEL)
		 */
		Student st=factory.getBean(Student.class);
		System.out.println(st.toString());
		
		Calculator cal=factory.getBean("device",Calculator.class);
		System.out.println(cal.toString());
		
		SpelExpressionParser temp=new SpelExpressionParser();
		Expression exp=temp.parseExpression("22+40");
		System.out.println(exp.getValue());
		
	}

}
