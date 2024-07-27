package com.spring.orm;

import java.util.List;

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
		
		Student st1=new Student();
		st1.setRollnumber(1);
		st1.setStudentCity("Delhi");
		st1.setStudentName("Meet");
		
		System.out.println("Rows Affected - "+std.updateData(st1));
		
		Student st2=std.fetchbyId(1);
		System.out.println(st2.getStudentName());
		
		List<Student> st3=std.fetchAll();
		
		for(Student s: st3) {
			System.out.println(s.getStudentName());
		}

		System.out.println("Deleted row - "+std.deletedata(1));
		
    }
}
