package com.spring.orm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.entities.Student;
import com.spring.orm.service.StudentService;

public class MainMethod 
{
    public static void main( String[] args )
    {
    	
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/spring/orm/springConfig.xml");

		/**
		 * Singleton and ProtoType
		 */ 
		
		StudentService std=factory.getBean("beanOfStudentService",StudentService.class);
		Student st=new Student();
		st.setRollnumber(1);
		st.setStudentName("Harsh");
		st.setStudentCity("Mumbai");
		
		System.out.println("Rows Affected - "+std.insertOperation(st));
    }
}
