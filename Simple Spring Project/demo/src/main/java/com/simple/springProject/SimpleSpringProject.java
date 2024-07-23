package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		
//		/**
//		 * This is a not the ideal way , here we
//		 * are creating object and not by spring.
//		 * Now the object cycle is dependent on us and not on spring 
//		 * since we created it
//		 */
//		Alien obj = new Alien();
		
		/**
		 * Created a Object
		 */
		Alien Object=(Alien) factory.getBean("objectOfAlien");
		
		Object.show();
		Object.setAge(15); // Setting age value using Object 1
		
		System.out.println("Age Object "+Object.getAge());

		/**
		 * Created another Object
		 * Now both the references of the Object points to the single object 
		 * and thats why we get the same value as 15 for both object which 
		 * is called as singleton type
		 */
		Alien Object1=(Alien) factory.getBean("objectOfAlien");
		System.out.println("Age Object2 "+Object.getAge());
		
			
		/**
		 * Prototype
		 */
		Alien ObjectProto=(Alien) factory.getBean("objectOfAlienprototype");
		ObjectProto.setAge(10);
		System.out.println(ObjectProto.getAge());
		
		
		Alien ObjectProto1=(Alien) factory.getBean("objectOfAlienprototype");
		System.out.println(ObjectProto1.getAge());
		
		
		/**
		 * Setters Injection
		 */
		Alien ObjectSetters=(Alien) factory.getBean("objectOfAlienSetters");
		System.out.println(ObjectSetters.getAge());
		
		/**
		 * Reference Injection 
		 */
		Laptop refLapObj=(Laptop) factory.getBean("laptopId");
		refLapObj.compile();
		
		/**
		 * Constructor Injection
		 */
		System.out.println(refLapObj.getLines());
		
		/**
		 * Autowiring 
		 */
		Laptop autoWiringLapObj=(Laptop) factory.getBean("laptopId");
		autoWiringLapObj.compile();
		
		AdditionOfNumbers addNums=(AdditionOfNumbers) factory.getBean("addNumBean");
		addNums.addition();
		

		AdditionOfNumbers addNumsInt=(AdditionOfNumbers) factory.getBean("addNumBeanInt");
		addNumsInt.addition();
		
	}

}
