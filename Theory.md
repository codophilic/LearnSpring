# About Spring
- The Spring framework is a set of tools and libraries for building Java applications. It provides a way to simplify the development of complex enterprise applications by providing a set of pre-built modules that handle common tasks, such as managing objects and handling database transactions, and providing a web application framework. By using Spring, developers can focus on writing business logic and let the framework handle the low-level details of infrastructure.
- Spring framework The Spring framework can be considered as a collection of sub-frameworks, example if we want to build a web module, we have a collection of Spring MVC, if we want to build a REST APIs we have Spring REST, if we want to secure the application we have Spring Security. So like these there are multiple sub-frameworks which together becomes a collections, thus giving a Spring framework.
- In Spring, POJOs are used extensively as the building blocks of the application. Spring leverages POJOs in several ways.

<details>
  
<summary>About Pojo</summary>

```
What is a POJO?
- POJO is a plain old java object like in the above example coneIceCream. Which means any java object is a POJO.

What is NOT a POJO?
→Shouldn’t extend any class.
→Shouldn’t implement any interface.
→No annotations.

Example: 
class IceCream{  
 //defining fields/ properties of icecream/states of icecream  
 double price;
 String flavourName;  
  
 public static void main(String args[]){  
  **//Creating an object or instance**  
  IceCream coneIceCream =new IceCream();
  
 }  
}
```
</details>

<details>
<summary>About Java Beans</summary>

```Text
What is a java bean?
A java bean is also an object of a class but here the class has to follow 3 rules → Class of the bean should have a no args constructor.

Why : JavaBeans can be easily instantiated using the Class.newInstance() method.

→ Should have public getters and setters and private fields.

→ Class should implement Serializable interface.

Why : Serialization is the process of converting an object into a stream of bytes, which can be written to a file or transmitted over a network meaning it will make the job of storing , retrieval and recreating easy. How : It creates byte stream of the object and it is easy to send that byte stream from one JVM to another where the other JVM can easily re-create the object using the byte stream.

→ If a class implements Serializable interface the objects of the class are serialized.

Example:
class IceCream implements Serializable{  
 //defining fields/ properties of icecream/states of icecream  
 private double price;
 private String flavourName;
//Getters and setters
public double getPrice(){
	return this.price;
} 
public void setPrice(double price){
	this.price = price;
} 
public String getFlavourName(){
	return this.flavourName;
}
public void setFlavourName(String name){
	this.flavourName = name;
} 
}
```
</details>

- Spring leverages the simplicity and flexibility of POJOs while providing powerful features like DI (Dependency Injection), IoC (Inversion of Control), AOP (Aspect-Oriented Programming), and data access abstractions to build robust and maintainable applications.

## Spring Core
- Spring Core, also known as the Spring Core Container, is the foundation of the Spring Framework. It's responsible for creating, configuring, and assembling the objects in a Spring application. The Core Container uses dependency injection (DI) to manage components, which makes the framework easy to integrate with other technologies and frameworks.
- Let's say you're building a toy car with Legos. Normally, you'd have to find all the pieces yourself (wheels, engine, chassis) and figure out how they connect.
- Spring Core is like a magic box for Legos. You tell it what kind of car you want (using a configuration file), and it:
  - Grabs all the necessary pieces (wheels, engine, chassis) - creates the objects
  - Snaps them together in the right way (configures them) - injects dependencies between objects
  - Makes sure you have everything you need (no missing pieces) - ensures all dependencies are met
- This way, you just tell Spring Core what you want to build, and it takes care of the messy details of putting it all together. You can focus on making your car cool, not hunting for spare axles!
- Spring Core manages objects in your code like Legos:
  - Object Creation: Imagine a Lego wheel. Spring can create that wheel object (like new Wheel() in Java) based on your instructions.
  - Destruction: When you're done playing, Spring can clean up those objects (similar to wheel.dispose()), freeing up resources.
  - Configuration: But a single wheel isn't very useful. Spring can configure the object, like setting its size or color.
  - Dependency Injection: Now, imagine a Lego car needing four wheels. Spring can "inject" those wheels into your car object, so the car knows how to use them without you needing to create and manage them yourself.
- So, yes, Spring handles object creation, destruction, and configuration. It also injects dependencies between objects, making your code cleaner and easier to maintain. You just define what you need (the car) and Spring takes care of the parts (wheels) and how they fit together.
- Here in analogy of car, the toolkit which has all the pieces are called IoC (Inversion of Control). Traditionally, you'd control how an object gets its dependencies (like the car creating its own wheels). However, with IoC, Spring inverts that control. You simply tell Spring what the car needs (wheels), and Spring takes over the responsibility of providing those wheels (dependency injection). This makes your application more flexible and easier to test.

### Dependency Injection (DI) and Inversion of Control (Ioc)/Bean Container
- DI means that you don't create objects yourself. Instead, you let an IoC container create them for you. The IoC container will also inject the dependencies of those objects into them. This means that you don't have to worry about creating and configuring objects yourself. The IoC container will do it for you.
- Dependency injection (DI) is a technique that focuses on providing the dependencies (objects or services) that a class requires from an external source, rather than having the class create or obtain those dependencies itself. The idea is to "inject" the dependencies into a class from the outside.
- Spring IoC container acts as a centralized repository of objects (beans) in your application. It creates and manages instances of objects, resolves their dependencies, and injects them where needed. It takes care of object lifecycle management and ensures that dependencies are correctly wired together.
- Consider below snip, this indicates the object is created by us and not by Spring.

```
		/**
		 * This is a not the ideal way , here we
		 * are creating object and not by spring.
		 * Now the object cycle is dependent on us and not on spring 
		 * since we created it
		 */
		Alien obj = new Alien();
```

- Now any object we want in our code it will be provided by IoC, but how shall we ask for which object we want to IoC? thats where we assign every bean an ID. Now every bean has an ID or a bean name, this ID needs to be store somewhere right? thats where we have a configuration file in spring.
- Spring uses configuration files (like XML or YAML) to define how objects (called beans) are created, configured, and wired together. These configuration files don't directly assign IDs to objects. Instead, they use a bean definition mechanism:
  - You specify the class type of the bean (e.g., com.Cars.Engine).
  - You can optionally provide a unique identifier (id="EngineOfcar") for the bean, but it's not mandatory. Spring can use class names by default.
  - The configuration allows you to set properties on the bean (e.g., engine type) and define relationships between beans (dependencies).
- In eclipse we need to install Spring from market place for spring configuration file or we can use [Spring tool suit](https://docs.spring.io/sts/nan/v3915/NewAndNoteworthy.html).
- In Spring, in a configuration xml file we need to specify bean name or an ID and the associated class package to it, so whenever in the code we require the object we need to ask spring by the bean name and spring will provide it.

## XML-based Configuration

- Example of Config xml file name **springConfig.xml**, here for the Alien Class we created a bean name as **objectOfAlien**.

<details>
<summary>About XML namespace</summary>

```
- It defines the XML namespaces and their corresponding schema locations. Let's break it down in simple terms:

- xmlns="http://www.springframework.org/schema/beans": This line specifies the default namespace for the XML file, which is the Spring Beans namespace. It allows you to use Spring Beans-related elements, such as <bean>, <property>, etc., without explicitly specifying the namespace prefix.

- xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance": This line declares the XML Schema Instance namespace, which is used to define XML schema-related attributes. It allows you to use the xsi prefix to reference attributes like xsi:schemaLocation.

- xmlns:context="http://www.springframework.org/schema/context": This line declares an additional namespace called "context" for the Spring Context namespace. It enables you to use Spring Context-related elements, such as component scanning, annotation-based configuration, etc., using the context prefix.

- xmlns:p="http://www.springframework.org/schema/p": This line declares an additional namespace called "p" for the Spring P namespace.
It allows you to use the p prefix to set property values inline in a concise manner, e.g., <bean p:name="John" p:age="25" />.

- xsi:schemaLocation="...": This attribute specifies the locations of the XML schemas associated with the declared namespaces.
It allows the XML file to be validated against the corresponding schema definitions. The schema is used by the Spring Framework to validate the XML configuration file.

- xmlns:c="http://www.springframework.org/schema/c": This line declares an additional namespace called "c" for the Spring c namespace.
It allows you to use the c prefix to set constructor values inline in a concise manner, e.g., <bean c:name="John" c:age="25" />. In this type of schema, suppose if we define <bean c:Name="John>. The constructor of the code must be like this below 
```
</details>


```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >
                           
     
    <bean class="com.simple.springProject.Alien" name="objectOfAlien">
    </bean>
                           
</beans>
```
- Alien Class

```
package com.simple.springProject;

public class Alien {
	
	public Alien() {
		System.out.println("Object created via constructor");
	}

	public void show() {
		System.out.println("Inside Alien");
	}
}

```

- Using spring we call the object by bean name and ApplicationContext. The Application Context represents the central container or entry point for an application that provides configuration and services to the application's components. It is a key component of the Spring IoC (Inversion of Control) container.

<details>
<summary>Bean Factory</summary>

```
The BeanFactory interface defines the basic contract for managing beans and accessing their instances. It provides methods for retrieving bean instances, resolving dependencies, and managing the lifecycle of beans.

An application context is an interface that extends the BeanFactory interface. It provides additional features, such as:
Message resolution: The ability to resolve messages from a properties file.

Event publication: The ability to publish events to registered listeners.

Resource loading: The ability to load resources, such as files and images.

Internationalization: The ability to support multiple languages.
```
</details>

- ClassPathXmlApplicationContext: This is a specific implementation of ApplicationContext that loads bean definitions from XML configuration files located on the classpath. The classpath refers to the standard location where application classes and resources are accessible within your project.

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
		
//		/**
//		 * This is a not the ideal way , here we
//		 * are creating object and not by spring.
//		 * Now the object cycle is dependent on us and not on spring 
//		 * since we created it
//		 */
//		Alien obj = new Alien();
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		Alien Object=(Alien) factory.getBean("objectOfAlien");
		
		Object.show();
	}

}

Output:
Object created via constructor
Inside Alien
```

- If you see, we successfully injected the bean and if you observe over here whenever bean is created when ApplicationContext is initialized , the constructor of that bean is called. So whenever we initialized ApplicationContext Spring will create all the beans mentioned in the configuration whether you required it or not.

## Singleton & Prototype

- Lets say we have an variable in class Alien

```
package com.simple.springProject;

public class Alien {
	
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Alien() {
		System.out.println("Object created via constructor");
	}

	public void show() {
		System.out.println("Inside Alien");
	}
}

```

- We create two objects in the main method like below, we set age value using 1 object (**Object**) and we print the age value using another object (**Object1**), this type of scope of a bean is called as singleton.

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
		
//		/**
//		 * This is a not the ideal way , here we
//		 * are creating object and not by spring.
//		 * Now the object cycle is dependent on us and not on spring 
//		 * since we created it
//		 */
//		Alien obj = new Alien();
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
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

	}

}

Output:
Object created via constructor
Inside Alien
Age Object 15
Age Object2 15
```

- A **singleton** class ensures there's only one instance of that class ever created in your program.
This single instance can have multiple references, meaning different parts of your code can access and interact with the same object.
- By default, Spring creates beans with singleton scope. This means Spring creates only one instance of the bean for a particular bean definition in the XML configuration. Similar to singletons, this single bean instance can have multiple references. Different parts of your application can request the bean from the Spring container, and they will all receive the same instance data values.
- In Spring, the bean scope defines the lifecycle and visibility of a Spring bean within the Spring application context. It determines how many instances of a particular bean are created and how they are managed by Spring when they are requested by other beans or components
- So in singleton if we ask spring to provide 10 reference it will still create a single object and share all the references to that single object only.
- Lets say if we want to have a difference object for each references we create in spring. So for that we change the scope of bean to **prototype**. Below is the config xml file.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >
                           
     

    <bean class="com.simple.springProject.Alien" name="objectOfAlienprototype" scope="prototype">
    </bean>
                           
</beans>
```
- Main method for calling prototype scope beans

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
		
//		/**
//		 * This is a not the ideal way , here we
//		 * are creating object and not by spring.
//		 * Now the object cycle is dependent on us and not on spring 
//		 * since we created it
//		 */
//		Alien obj = new Alien();
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
				
		Alien ObjectProto=(Alien) factory.getBean("objectOfAlienprototype");
		ObjectProto.setAge(10);
		System.out.println(ObjectProto.getAge());
		
		
		Alien ObjectProto1=(Alien) factory.getBean("objectOfAlienprototype");
		System.out.println(ObjectProto1.getAge());
		


	}

}

Output:
Object created via constructor
10
Object created via constructor
0
```

- Incase of prototype scope, a prototype bean is a new instance that is created every time the bean is requested. So the new instance is created by spring only when it is called by `getBean()`. **Now whenever we initialized ApplicationContext spring does not create those beans in case of prototype scope.**

## Setters Injection
- Lets say we wanted to assigned some value of class variables whenever spring creates our bean, this can be done using property attribute, but property attribute has one condition. Your class variables must be private. So how will they set the value if access modifier is private? using getters and setters of the variable.
- Below is the config xml file for it.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >
                           

    
    <bean class="com.simple.springProject.Alien" name="objectOfAlienprototype" >
    <property name="age" value="100"></property>
    </bean>
                           
</beans>
```

- The age variable has getters and setters

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		Alien ObjectSetters=(Alien) factory.getBean("objectOfAlienSetters");
		System.out.println(ObjectSetters.getAge());

	}

}

Output:
Object created via constructor
Object created via constructor
Setters method is set with value 10
10
```

- Setter injection involves providing dependencies through setter methods. The class provides setter methods for each dependency, and the IoC container uses these methods to set the dependencies after creating an instance of the class. Setter injection allows for more flexibility, as dependencies can be changed or re-injected at runtime. Using setters method the objects/beans are instantiated and these objects are injected.

## Reference Injection
- Lets say Laptop class uses another class name Code which has methods below

```
package com.simple.springProject;

public class Code {

	public void coding() {
		System.out.println("Coding done");
	}
}

```

- The Laptop class uses code in its show method.

```
package com.simple.springProject;

public class Laptop {

	private Code code;
	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}

	public void compile() {
		System.out.println("Compiling...");
		code.coding();
	}
}

```

- Suppose we don't define configuration for Code class, we would land up in null pointer exception because  When you don't define a bean for the dependent class, there's no information for Spring to create and manage an instance of that class. The class that relies on the other class tries to use it, but since there's no instance available, it ends up referencing a null value. When you attempt to call methods or access properties on this null reference, you hit the NullPointerException.

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.simple.springProject.code.coding()" because "this.code" is null
	at com.simple.springProject.Alien.show(Alien.java:31)
	at com.simple.springProject.SimpleSpringProject.main(SimpleSpringProject.java:25)
```
- Now Laptop class depends on Code class, so if we create a bean in config xml for Code, we need to given reference of that Code class to Laptop class.

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >
    
     <bean class="com.simple.springProject.Laptop" id="laptopId" >
     	<property name="code" ref="codeId"></property>
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
                           
</beans>
```
- Now when we run the main method , we can see laptop class uses code class coding method 

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");

		Laptop refLapObj=(Laptop) factory.getBean("laptopId");
		refLapObj.compile();

		
	}

}

Output:
Compiling...
Coding done
```
- The reference type of injection refers to a method of dependency injection where a bean is injected into another bean by referencing it using its unique identifier (ID) or name. This type of injection allows one bean to use or access another bean by referring to it directly. The reference type of injection is commonly used when one bean depends on another bean and needs to collaborate with it. The collaboration can involve method calls, property access, or any other interaction between the beans.
- **Reference type injection is a powerful way to decouple beans in a Spring application. By using reference type injection, you can avoid tightly coupling beans together. This makes your application more flexible and easier to maintain.**

## Constructor Injection
- Lets say we have a class variable name lines in class Laptop, it is an integer variable.

```
package com.simple.springProject;

public class Laptop {

	private int lines;
	private Code code;
	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}

	public void compile() {
		System.out.println("Compiling...");
		code.coding();
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}
	
	
	/**
	 * Parameterize Constructor
	 */
	public Laptop(int lines) {
		this.lines=lines;
	}
}
```
- Here we have define a parameterize constructor which helps to assign integer value to the lines.
- Below is the config xml for laptop class, it does not define any setting values to variable lines (setters injection)

```
     <bean class="com.simple.springProject.Laptop" id="laptopId" >
     	<property name="code" ref="codeId"></property>
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
```

- When we execute the main method, we get an error stating there was no default constructor found.

```
Jul 23, 2024 12:31:24 AM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'laptopId' defined in class path resource [com/simple/springProject/springConfig.xml]: Failed to instantiate [com.simple.springProject.Laptop]: No default constructor found
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'laptopId' defined in class path resource [com/simple/springProject/springConfig.xml]: Failed to instantiate [com.simple.springProject.Laptop]: No default constructor found
```
- This indicates that we have a parameterized constructor which requires argument , so we need to specify the value in the config xml file for it. Below is the config xml.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >
    
     <bean class="com.simple.springProject.Laptop" id="laptopId" >
     	<constructor-arg value="110"></constructor-arg>
     	<property name="code" ref="codeId"></property>
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
                           
</beans>
```

- So when we run the main method 110 value gets set via parameterized constructor into the lines variable.

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		/**
		 * Reference Injection 
		 */
		Laptop refLapObj=(Laptop) factory.getBean("laptopId");
		refLapObj.compile();
		
		/**
		 * Constructor Injection
		 */
		System.out.println(refLapObj.getLines());

		
	}

}

Output:
Compiling...
Coding done
110
```

- In constructor injection, dependencies are provided to a class through its constructor. The dependencies are declared as constructor parameters, and the IoC container resolves and injects the dependencies when creating an instance of the class. This type of injection promotes immutability and ensures that all required dependencies are available when creating an object. Using constructors, objects are created.
- **Use Setter Injection when variable values are optional and use Constructor Injection when varible values are mandatory.**


## Autowire
- Lets say we have a ProgrammingLanguage interface which has a method `print()`.

```
package com.simple.springProject;

public interface ProgrammingLanguage {

	public void print();
}
```

- We have 2 programming languages defined , one is PythonCode and another one is JavaCode. Each class implements `print()` in their own style.

1. PythonCode Class

```
package com.simple.springProject;

public class PythonCode implements ProgrammingLanguage {

	@Override
	public void print() {
		System.out.println("Printing Style in Python");
	}

}
```
2. JavaCode Class

```
package com.simple.springProject;

public class JavaCode implements ProgrammingLanguage {

	@Override
	public void print() {
		System.out.println("Printing Style in Java");
	}

}
```

- Both classes have different style to print `print()` method.
- Now the Laptop class uses ProgrammingLanguage interface as its class variable, and inside the compile method we use the interface method which is `print()`.

```
package com.simple.springProject;

public class Laptop {

	private int lines;
	private Code code;
	private ProgrammingLanguage programmingLanguage;

	
	public Code getCode() {
		return code;
	}
	
	public void setCode(Code code) {
		this.code = code;
	}

	public void compile() {
		System.out.println("Compiling...");
		code.coding();
		programmingLanguage.print();
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	
	/**
	 * Parameterize Constructor
	 */
	public Laptop(int lines) {
		setLines(lines);
	}

	public ProgrammingLanguage getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
}
```

- In the Config XML, we create two more beans for PythonCode and JavaCode, and we wanted to give reference of these bean into Laptop. (ProgrammingLanguage is an interface and not a class)

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >

     <bean class="com.simple.springProject.Laptop" id="laptopId" >
     	<constructor-arg value="110"></constructor-arg>
     	<property name="code" ref="codeId"></property>
     	<property name="programmingLanguage" ref="programmingLanguage"></property>
     	
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
      <bean class="com.simple.springProject.PythonCode" id="programmingLanguage"/>
                   
       <bean class="com.simple.springProject.JavaCode" id="javaCodeId"/>
        
</beans>
```

- So our reference name as well as class variable name defined in Laptop is name which is **programmingLanguage**. Now since both are same then we can skip the `<property name="programmingLanguage" ref="programmingLanguage"></property>` tag and tell spring to directly pick up beans which has same variable class names using additional attribute name **autowire**. So basically autowire beans by using their variable names.
- This how it looks Config XML looks like

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd" >

     <bean class="com.simple.springProject.Laptop" id="laptopId"  autowire="byName" >
     	<constructor-arg value="110"></constructor-arg>
     	<property name="code" ref="codeId"></property>     	
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
      <bean class="com.simple.springProject.PythonCode" id="programmingLanguage"/>
                   
       <bean class="com.simple.springProject.JavaCode" id="javaCodeId"/>
        
</beans>
```

- When we autowire the **programmingLanguage** bean and execute the code we can see the `print()` method of PythonCode is getting printed since we have given id as programmingLanguage in configuration bean for PythonCode. So when the bean is available spring automatically connects with it. If the bean id is not found which needs to be same as class variable name, spring will throw an error.

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		/**
		 * Autowiring 
		 */
		Laptop autoWiringLapObj=(Laptop) factory.getBean("laptopId");
		autoWiringLapObj.compile();

		
	}

}
```

- What happens, if instead of name if we specify spring to autowire beans based on the class type. Basically autowire Laptop class with ProgrammingLanguage type. So basically Laptop class is dependent on ProgrammingLanguage type. So in Autowire we can mentioned that as well. Below is the config xml for it.

```
     <bean class="com.simple.springProject.Laptop" id="laptopId"  autowire="byType" >
     	<constructor-arg value="110"></constructor-arg>
     	<property name="code" ref="codeId"></property>     	
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
      <bean class="com.simple.springProject.PythonCode" id="programmingLanguage"/>
                   
       <bean class="com.simple.springProject.JavaCode" id="javaCodeId"/>
```

- Now when we try to run the main method we will get an error

```
Jul 23, 2024 1:48:38 AM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'laptopId' defined in class path resource [com/simple/springProject/springConfig.xml]: Unsatisfied dependency expressed through bean property 'programmingLanguage': No qualifying bean of type 'com.simple.springProject.ProgrammingLanguage' available: expected single matching bean but found 2: programmingLanguage,javaCodeId
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'laptopId' defined in class path resource [com/simple/springProject/springConfig.xml]: Unsatisfied dependency expressed through bean property 'programmingLanguage': No qualifying bean of type 'com.simple.springProject.ProgrammingLanguage' available: expected single matching bean but found 2: programmingLanguage,javaCodeId
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType(AbstractAutowireCapableBeanFactory.java:1536)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1430)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:971)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:625)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:85)
	at com.simple.springProject.SimpleSpringProject.main(SimpleSpringProject.java:10)
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.simple.springProject.ProgrammingLanguage' available: expected single matching bean but found 2: programmingLanguage,javaCodeId
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveNotUnique(DependencyDescriptor.java:218)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1420)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType(AbstractAutowireCapableBeanFactory.java:1521)
	... 13 more
```
- If we observer config XML, there are two classes or types for ProgrammingLanguage one is with PythonCode and another one is JavaCode, this makes confusion for spring to pick which one and inject it into Laptop class. In such scenario we can mentioned which been needs to be set once of them as primary or give one of them as preference. Below is the config xml for it.

```
    <bean class="com.simple.springProject.Alien" name="objectOfAlienSetters" >
	    <property name="age" value="10"></property>
    </bean>
    
     <bean class="com.simple.springProject.Laptop" id="laptopId"  autowire="byType" >
     	<constructor-arg value="110"></constructor-arg>
     	<property name="code" ref="codeId"></property>     	
     </bean>
     
     <bean class="com.simple.springProject.Code" id="codeId"/>
    
      <bean class="com.simple.springProject.PythonCode" id="programmingLanguage"/>
                   
       <bean class="com.simple.springProject.JavaCode" id="javaCodeId" primary="true"/>
        
```

- Here we have set JavaCode class as primary , lets check the main execution output.

```
Output:
Compiling...
Coding done
Printing Style in Java
```

- Autowiring is a feature that automatically injects the dependencies (other beans) into a class without requiring explicit configuration. It helps to simplify bean wiring by automatically connecting beans based on their types. Autowiring can be used to inject dependencies into a Spring bean, whether it's through constructor injection, setter injection, or field injection. The autowiring process is based on the types of the beans and the types of the dependencies required by the bean being autowired.
- Limitations: Autowiring cannot be used to inject primitive types. This is because primitive types are not objects. They are simply the raw values of the type, such as int, boolean, String, etc. It works with reference/Objects only.

### Types of Autowiring
1. **no**: It’s the default autowiring mode. It means no autowiring.
2. **byType**: Autowiring by type is the default mode. When you use autowire="byType" in the XML configuration or use annotations like @Autowired, Spring searches for a bean of the same type as the dependency and injects it.
3. **byName**: Autowiring by name looks for a bean whose ID matches the name of the dependency in XML, and if found, it injects that bean.
4. **byConstructor**: Autowiring by constructor is similar to byType, but it works for constructor injection. It looks for beans of the same type as the constructor parameter and injects them. In Autowiring by constructor, Spring will call the parameterized constructor that matches the dependencies it needs to inject. If there is no parameterized constructor available, Spring will fall back to using the default constructor (i.e., a constructor with no arguments) if it exists.

- Above learnings are implement [here](https://github.com/codophilic/LearnSpring/tree/main/Simple%20Spring%20Project).

## Why Spring is usefull, field method injections





















