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

![image](https://github.com/user-attachments/assets/bbb041ee-6dd8-4c6a-86b6-edc43aa9a6b2)

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
- Lets say we wanted to assigned some value of instance variables whenever spring creates our bean, this can be done using property attribute, but property attribute has one condition. Your instance variables must be private. So how will they set the value if access modifier is private? using getters and setters of the variable.
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

	    
    <!-- 
    OR using p:schema the setter injection declaration can be changed to below
        <bean class="com.simple.XMLBasedConfiguration.Alien" name="objectOfAlienSetters" p:age="10" >
    </bean>
    
     -->
                           
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
- In the above example we have injected primitives values, similarly we can inject List, Set, Map and Properties types.
- Lets say we have a Student class which has primitives values as well as collections types.

```
package com.simple.XMLBasedConfiguration;

import java.util.*;

public class Student {
	
	
	//Primitive Data types
	private String ID;
	private String Name;
	private String Address;
	
	
	public Student() { // This is a default constructor , which is require for setters injection
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String iD, String name, String address) {
		super();
		ID = iD;
		Name = name;
		Address = address;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	
	// List , Sets , Maps and Properties
	
	List<Integer> list=new ArrayList<Integer>();
	
	Set<Integer> set = new HashSet<Integer>();
	
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	Properties MyProps = new Properties();


	public Properties getMyProps() {
		return MyProps;
	}
	public void setMyProps(Properties myProps) {
		MyProps = myProps;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", Name=" + Name + ", Address=" + Address + ", list=" + list + ", set=" + set
				+ ", map=" + map + ", MyProps=" + MyProps + "]";
	}
}
```

- Lets say we wanted to initialized values whenever the bean is created by spring into these collections, below is the config xml file.

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

      
      <bean name="student" class="com.simple.XMLBasedConfiguration.Student" p:ID="4" p:Name="Harsh" p:Address="Mumbai">

        <property name="list">
        <list>
        <value>100</value>
        <value>200</value>
        </list>
        </property>
        
        
        <property name="set">
        <set>
        <value>100</value>
        <value>200</value>
        <value>100</value>
        </set>
        </property>
        
        
        <property name="map">
        <map>
        <entry key="Java" value="1"/>
        <entry key="Python" value="2"/>
        </map>
        </property>
        
        <property name="MyProps">
        <props>
        <prop key="prop1">value1</prop>
        <prop key="prop2">value2</prop>
        </props>
        </property>
        
    </bean>
        
</beans>
```

- Below is the main method, post execution we can see our collections values got injected.

```
package com.simple.XMLBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/XMLBasedConfiguration/springConfig.xml");

		/**
		 * Setter Injections of Collections
		 */
		
		Student s3=(Student)factory.getBean("student");
	       
	       System.out.println(s3.toString());
		
		
	}

}

Output:
Student [ID=4, Name=Harsh, Address=Mumbai, list=[100, 200], set=[100, 200], map={Java=1, Python=2}, MyProps={prop2=value2, prop1=value1}]
```

- Lets say we have multiple classes which may uses the same collections values, so instead of writing it multiple times can we have a utility which defined them by a reference and whenever any bean requires those values we can provide that bean this reference? , Yes, in Spring, you can define and configure standalone collections such as lists, sets, and maps in XML-based configuration using the `<util>` namespace. This is useful when you want to configure collections of values or beans that are not directly associated with a specific class.
- Below is the xml config file for it.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >
         
    <!-- Standalone List -->
    <!-- To enabled downloading of external references go to windows -> preferences -> XML allow download -->
    <util:list  list-class="java.util.LinkedList" id="myList">
        <value>1</value>
        <value>2</value>
        <value>3</value>
    </util:list> 
    
    <util:map map-class="java.util.TreeMap" id="myMap">
    	<entry key="123" value="11" />
    	<entry key="124" value="22" />
    </util:map>
    
    <util:properties id="myProps">
    	<prop key="dbDriver">sqlDriverPath</prop>
    </util:properties>
    
     <bean name="employee" class="com.simple.XMLBasedConfiguration.Employee" >
     	<property name="list" ref="myList"/>
     	<property name="map" ref="myMap"/>
     	<property name="MyProps" ref="myProps"/>
     </bean>

</beans>
```

- Below is the main method , post execution we can see our standalone collections are injected successfully.

```
package com.simple.XMLBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/XMLBasedConfiguration/springConfig.xml");
		
	      /**
	       * Standalone Collections
	       */
		Employee emp=(Employee)factory.getBean("employee");
		System.out.println(emp.toString());
	       
	}

}

Output:
Employee [list=[1, 2, 3], map={123=11, 124=22}, MyProps={dbDriver=sqlDriverPath}]
```

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
    
	<!-- 
         
	Alternative way of writing the ref bean
		 
		 <property name="code" ref="codeId"/>
		 
		 Using P schema 
		 p:code-ref="codeId"
     
      -->
                           
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

### Ambuiguity in Constructor injection
- Suppose there are 3 constructors out of which 2 constructor is a constructor overloading. 
- First Constructor accepts input as double, second constructor accepts input as integer and the third accepts input as string. Now in config xml we pass constructor values, Spring will pick up which constructor? 
- It will take that constructor which is defined as per order in the code. If double constructor is written before integer constructor then the double constructor will be considered for injection.
- Below is the class written for AdditionOfNumbers

```
package com.simple.springProject;

public class AdditionOfNumbers {

	private int n1;
	private int n2;
	
	
	public AdditionOfNumbers(int n1, int n2) {
		System.out.println("Integer Constructor");
		System.out.println("N1: "+n1);
		System.out.println("N2: "+n2);
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public AdditionOfNumbers(double n1, double n2) { // Constructor Overloading
		System.out.println("Double Constructor");
		this.n1 = (int)n1;
		this.n2 = (int)n2;
		this.n1=0;
		this.n2=1;
	}	
	
	public void addition() {
		int sum=this.n1+this.n2;
		System.out.println("Addition is: "+sum);
	}
}
```

- Below is the config xml file

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
                           
      <bean class="com.simple.springProject.AdditionOfNumbers" id="addNumBean" c:n1="6" c:n2="7">
      </bean>
      
        
</beans>
```

- When we execute the main program, as per ordering integer parameterized constructor is called and values are injected into it

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		AdditionOfNumbers addNums=(AdditionOfNumbers) factory.getBean("addNumBean");
		addNums.addition();
		
	}

}

Output:
Integer Constructor
N1: 6
N2: 7
Addition is: 13
```

- Lets say we wanted to use double parameterized constructor, we need to specify what type of variable we are passing to the constructor. So if we pass (double,double) as arguments to constructor , Spring will take the double parameterized constructor.
- Below is the config xml file for it

``` 
<bean class="com.simple.springProject.AdditionOfNumbers" id="addNumBean">
    	<constructor-arg value="6" type="double" />
    	<constructor-arg value="7" type="double" />
      </bean>
```

- When we run the code we get below output for it, in double constructor we have type cast it into integer as 0 and 1 thus obtaining 1 as sum.

```
Double Constructor
Addition is: 1
```

- Lets say we also add a new constructor which takes arguments as a string and in the config xml if we don't define the type attribute, what will happen? 
- Below is the code for AdditionOfNumber class

```
package com.simple.springProject;

public class AdditionOfNumbers {

	private int n1;
	private int n2;
	
	
	public AdditionOfNumbers(int n1, int n2) {
		System.out.println("Integer Constructor");
		System.out.println("N1: "+n1);
		System.out.println("N2: "+n2);
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public AdditionOfNumbers(double n1, double n2) { // Constructor Overloading
		System.out.println("Double Constructor");
		this.n1 = (int)n1;
		this.n2 = (int)n2;
		this.n1=0;
		this.n2=1;
	}
	
	public AdditionOfNumbers(String n1, String n2) { // Constructor Overloading
		System.out.println("String Constructor");
		this.n1 = Integer.parseInt(n1);
		this.n2 = Integer.parseInt(n2);
	}
	
	
	public void addition() {
		int sum=this.n1+this.n2;
		System.out.println("Addition is: "+sum);
	}
}

```

- Below is the config XML file

```
      <bean class="com.simple.springProject.AdditionOfNumbers" id="addNumBean">
    	<constructor-arg value="6" />
    	<constructor-arg value="7" />
      </bean>
```

- Now as per our understanding, it must call the constructor which is written above as per ordering, but it will the string parameterized constructor.

```
Output:
String Constructor
Addition is: 13
```

- Why so? basically in the config xml file these values are considered as string. In the config xml whenever we mentioned `<constructor-arg value="6" />` this is default considered as string. This creates an ambuiguity (doubtfulness or uncertainty).
- To solve this ambuiguity and lets say between those these parameterized constructor we can use attribute **type**. 
- Apart from these ambuiguity, in spring constructor injection, we can specify which value to be taken as first argument and which value to be taken as second argument by using **index**. Below is the config xml for it.

```
       <bean class="com.simple.springProject.AdditionOfNumbers" id="addNumBeanInt">
    	<constructor-arg value="6"  type="int" index="1"/>
    	<constructor-arg value="7" type="int" index="0"/>
      </bean>
```

- Below we get the output post running main method

```
package com.simple.springProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/springProject/springConfig.xml");
		
		AdditionOfNumbers addNumsInt=(AdditionOfNumbers) factory.getBean("addNumBeanInt");
		addNumsInt.addition();
		
	}

}


Output:
Integer Constructor
N1: 7
N2: 6
Addition is: 13
Addition is: 13
```

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

- Above learnings are implement [here](https://github.com/codophilic/LearnSpring/tree/main/Simple%20Spring%20Project/demo/src/main/java/com/simple/XMLBasedConfiguration).

## Why Spring is useful
- Loose Coupling: Spring promotes loose coupling through dependency injection, making it easier to manage and test components. By configuring beans and their dependencies in a separate configuration file or class, the components in your application become loosely coupled. This improves modularity, testability, and flexibility in your codebase. **Example in the Autowire we had two class which implements the same interface, and during the configuration we specified to spring that which object we want, thus making it loosely coupled**.
- Dependency Management: It automates the process of managing object dependencies, which simplifies configuration and increases maintainability. Spring manages the dependencies between beans. You can specify the dependencies of a bean, and the IoC container automatically resolves and injects them. This simplifies the process of managing complex dependencies between components. Due to this you don't need to compile your code again , just configure XML and save it.
- Centralized Management: Beans and their configuration are managed in a centralized manner. This allows for easy modification, maintenance, and scalability of the application. You can change the behavior of beans or swap implementations by simply modifying the configuration, without modifying the application code.
- Simplifying application development: Spring provides a programming model that simplifies the development of complex enterprise applications. This helps developers be more productive and write better code.
- Encouraging good design practices: Spring promotes good design practices, such as separation of concerns and test-driven development. This helps ensure that applications are maintainable, scalable, and easy to evolve over time.
- Reducing development time: By providing a set of pre-built modules, Spring helps developers write code faster and reduces the amount of boilerplate code they need to write.

## Annotation-based Configuration
- In the above approach , we configured configurations using XML files. In annotation appraoch we declare annotations like `@Component`, `@Service`, `@Repository`, etc., to mark classes as Spring beans. The Spring IoC container scans the application's classpath and automatically detects and manages these annotated beans.
- In Spring, annotations are special markers used to simplify configuration and setup of the application.
- Let us take an example, so we have a class Laptop, so earlier in xml config we create config file for a class using `<bean>` attribute, but in annotation we have `@Component` as annotation way to create beans in Spring.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

/**
 * <bean class="com.simple.AnnotationBasedConfiguration" name="objectofLaptop"/>
 */
@Component
public class Laptop {

	public void start() {
		System.out.println("Laptop is starting ...");
	}
}

```

- Below is our main method where we execute the beans by asking Spring to gives us the object or beans.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		Laptop lapObj=(Laptop) factory.getBean("Laptop");
	}

}

```

- So we have defined one annotation for Laptop bean and we have declare our config xml file but when we try to run this method we will get an error.

```
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'Laptop' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:895)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1362)
```

- Whats the problem? the problem is we have declare the annotation and config xml, but when spring uses the config xml ,it will look for XML based configuration details but we are here using annotation based configurations which spring does not know. So in the config xml we need to specify Spring that we are using annotation based configurations.
- Below is the xml config file which specify spring to use annotation configuration for a given package.

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >  
                           
    <!--  Enabling Component Scanning Annotations  -->
	<context:component-scan base-package="com.simple.AnnotationBasedConfiguration"/> 
        
</beans>
```

- Now we have specify spring to refer annotation based configurations , now post execution we will get below output

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		Laptop lapObj=(Laptop) factory.getBean("laptop");
		lapObj.start();
	}

}

Output:
Laptop is starting ...
```

- If you notice in XML based configuration we also declare a bean name but in case of annotation the default bean name is small case of the class (**laptop**). If we want to customized the bean name we can do it like below way.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

/**
 * <bean class="com.simple.AnnotationBasedConfiguration" name="objectofLaptop"/>
 */
@Component("lap")
public class Laptop {
	
	private PythonCode pyCode;
	
	

	public void start() {
		System.out.println("Laptop is starting ...");
	}
}

```

- Below is the main method.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		Laptop lapObj=(Laptop) factory.getBean("lap");
		lapObj.start();
	}

}

Output:
Laptop is starting ...
```

- `@Component` annotation creates an Object for the bean and the object is name is based on camel case of class name. So whenever we create a class and wanted to use that class as an object , always annote it using `@Component`.
- Lets say we have another class name PythonCode and our Laptop class is dependent on PythonCode class. Below is the PythonCode class.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component
public class PythonCode {

	public void run() {
		System.out.println("Python Code is running..");
	}
}
```

- Using `@Component` we have told spring this is an bean and it might be asked in middle of the code. Below is the Laptop class.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

/**
 * <bean class="com.simple.AnnotationBasedConfiguration" name="objectofLaptop"/>
 */
@Component("lap")
public class Laptop {
	
	private PythonCode pyCode;

	public void start() {
		System.out.println("Laptop is starting ...");
		pyCode.run();
	}

	public PythonCode getPyCode() {
		return pyCode;
	}

	public void setPyCode(PythonCode pyCode) {
		this.pyCode = pyCode;
	}
}
```

- It seems we have created bean for PythonCode and we are using that class into Laptop. So when we run, it gives us **pyCode** is null.

```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.simple.AnnotationBasedConfiguration.PythonCode.run()" because "this.pyCode" is null
	at com.simple.AnnotationBasedConfiguration.Laptop.start(Laptop.java:15)
	at com.simple.AnnotationBasedConfiguration.SimpleSpringProject.main(SimpleSpringProject.java:13)
```

- To solve this we need to use annotation `@Autowired`.

``` 
package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <bean class="com.simple.AnnotationBasedConfiguration" name="objectofLaptop"/>
 */
@Component("lap")
public class Laptop {
	
	/**
	 * <bean class="com.simple.XMLBasedConfiguration.Laptop" id="laptopId" autowire=
	 * "byType" > <constructor-arg value="110"></constructor-arg>
	 * <property name="code" ref="codeId"></property> </bean>
	 *
	 */
	@Autowired
	private PythonCode pyCode;

	public void start() {
		System.out.println("Laptop is starting ...");
		pyCode.run();
	}

	public PythonCode getPyCode() {
		return pyCode;
	}

	public void setPyCode(PythonCode pyCode) {
		this.pyCode = pyCode;
	}
}
```

- Now when we run the main method , we get the below output.

```
Output:
Laptop is starting ...
Python Code is running..
```

- `@Autowired` annotations injects dependencies automatically. When you mark a field, constructor, or setter method with `@Autowired`, Spring looks for a matching bean in the application context and injects it into the marked location. This helps in managing dependencies between different parts of your application without needing to manually create or pass objects around.

```
Examples

Field Injection

@Autowired
private Car car;

Constructor Injection

@Autowired
public Driver(Car car) {
    this.car = car;
}

Setter Injection

private Car car;

@Autowired
public void setCar(Car car) {
    this.car = car;
}
```

- In XML based configuration, we have saw bean scope which is singleton or prototype where a **singleton** class ensures there's only one instance of that class ever created in your program. This single instance can have multiple references, meaning different parts of your code can access and interact with the same object.
- A prototype bean is a new instance that is created every time the bean is requested. So the new instance is created by spring only when it is called by `getBean()`. **Now whenever we initialized ApplicationContext spring does not create those beans in case of prototype scope.**
- In annotation configuration to define bean scope we have `@Scope` annotation.
- Consider we have a class Alien, for which we have declare a scope as prototype.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Alien {

	
}
```

- Below is main program, which outputs hashcode for both object. The hashcode are different which indicates the scope of the bean.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		
		
		
	}

}

Output:
Hash Code for First Object - 1349182676
Hash Code for Second Object - 2108763062
```

- Lets say we have an interface of Car, and there are two classes Ferrari and AstonMartin which implements Car interface.
- Car Interface
```
package com.simple.AnnotationBasedConfiguration;

public interface Car {

	public void engine();
}

```

- Ferrari Class

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component
public class Ferrari implements Car {

	@Override
	public void engine() {
		System.out.println("Ferrari Engine");

	}

}
```
- AstonMartin Class

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component
public class AstonMartin implements Car {

	@Override
	public void engine() {
		System.out.println("AstonMartin Engine");

	}

}
```

- Lets say we have a driver class which will run the car.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {

	@Autowired
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void start() {
		System.out.println("Starting Engine..");
		car.engine();
	}
	
}
```

- Now when we call bean for implemented interface of car, spring will return which bean? it will throw and error

```
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

Output:
Jul 23, 2024 11:11:56 PM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'driver': Unsatisfied dependency expressed through field 'car': No qualifying bean of type 'com.simple.AnnotationBasedConfiguration.Car' available: expected single matching bean but found 2: astonMartin,ferrari
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'driver': Unsatisfied dependency expressed through field 'car': No qualifying bean of type 'com.simple.AnnotationBasedConfiguration.Car' available: expected single matching bean but found 2: astonMartin,ferrari
```

- Here the same issue we faced in autowiring when we did xml base configuration for `autowiring="byType"`. To resolve this we had used a primary attribute on one of the class. So in case of annotation we can resolve it using `@Primary` and another annotation `@Qualifier(name=)`.
- Using `@Primary` annotation

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 
 * <bean class="com.simple.AnnotationBasedConfiguration" id="javaCodeId" primary="true"/>
 */

@Component
@Primary 
public class Ferrari implements Car {

	@Override
	public void engine() {
		System.out.println("Ferrari Engine");

	}

}
```

- When we run the code we get below output

```
Output:
Laptop is starting ...
Python Code is running..
Starting Engine..
Ferrari Engine
```

- Using `@Qualifier` annotation.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Driver {

	@Autowired
	@Qualifier("astonMartin")
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void start() {
		System.out.println("Starting Engine..");
		car.engine();
	}
	
}

Output:
Laptop is starting ...
Python Code is running..
Starting Engine..
AstonMartin Engine
```

- In Spring, the `@Qualifier` annotation is used along with `@Autowired` to disambiguate beans when multiple beans of the same type are available for autowiring. When there are multiple beans of the same type, Spring might not be able to determine which bean to inject based solely on type, leading to an ambiguity error. The `@Qualifier` annotation helps resolve this ambiguity by specifying the exact bean name or qualifier to be injected.
- With the `@Qualifier` annotation, you can precisely control which bean you want to inject when there are multiple beans of the same type. This is particularly useful when dealing with complex configurations and scenarios where multiple beans of the same type exist, and you need to ensure the correct bean is wired into your classes. If bean name is not found then it will throw exception.
- The name in the `@Qualifier` field should be exactly same as of the class name but the first character should be always in lowercase, as spring creates beans this way.
- In `@Component` we specify name, we can use that name as well in the `@Qualifier` annotation like below

```
package com.example;

import org.springframework.stereotype.Component;

@Component("customServiceName")
public class MyService {
    // Class implementation
}


package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyController {

    @Autowired
    @Qualifier("customServiceName") // Use the custom bean name
    private MyService myService;

    // Class implementation
}
```

- In XML based configuration, we could inject values into the variables similarly in annotation we have `@Value` annotation. Lets say we need to inject primitive values as well as standalone collections values using annotation based configurations.
- Consider below class of Students. 

```
package com.simple.AnnotationBasedConfiguration;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
	
	
	//Primitive Data types
	@Value("123")
	private String ID;
	
	@Value("Harsh")
	private String Name;
	
	@Value("Mumbai")
	private String Address;
	
	
	
	// List , Sets , Maps and Properties
	
	@Value("#{myList}")
	private List<Integer> list;
	
	@Value("#{myMap}")
	private Map<String,Integer> map;
	
	@Value("#{myProps}")
	private Properties MyProps;
	
	
	public Student() { // This is a default constructor , which is require for setters injection
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String iD, String name, String address) {
		super();
		ID = iD;
		Name = name;
		Address = address;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Properties getMyProps() {
		return MyProps;
	}
	public void setMyProps(Properties myProps) {
		MyProps = myProps;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", Name=" + Name + ", Address=" + Address + ", list=" + list
				+ ", map=" + map + ", MyProps=" + MyProps + "]";
	}
}
```

- For primitive values where String or integer are the datatypes, withing `@Value` annotation we can pass the value for those datatype, whereas for standalone collections like list, map ,properties etc.. we use Spring Expression Language (SpEL). Spring Expression Language (SpEL) is used for defining expressions to evaluate dynamically.
- Below is the config xml for util schema where we declare the values of list , map and properties.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >
                           
    <!--  Enabling Component Scanning Annotations  -->
	<context:component-scan base-package="com.simple.AnnotationBasedConfiguration"/> 
    
        <!-- Standalone List -->
    <!-- To enabled downloading of external references go to windows -> preferences -> XML allow download -->
    <util:list  list-class="java.util.LinkedList" id="myList">
        <value>1</value>
        <value>2</value>
        <value>3</value>
    </util:list> 
    
    <util:map map-class="java.util.TreeMap" id="myMap">
    	<entry key="123" value="11" />
    	<entry key="124" value="22" />
    </util:map>
    
    <util:properties id="myProps">
    	<prop key="dbDriver">sqlDriverPath</prop>
    </util:properties>
</beans>
```

- Below is the main method, if you note here we can also pass **ClassName.class** in the `getBean()` method.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {


	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");
		
		Student st=factory.getBean(Student.class);
		System.out.println(st.toString());
	}

}

Output:
Student [ID=123, Name=Harsh, Address=Mumbai, list=[1, 2, 3], map={123=11, 124=22}, MyProps={dbDriver=sqlDriverPath}]
```

- The `#{...}` syntax inside a @Value annotation in Spring is known as **Spring Expression Language (SpEL)**. It allows you to define expressions that can perform operations such as accessing properties, invoking methods, manipulating collections, and evaluating logical expressions.
- Spring resolves this expressions during Runtime.
- Lets say we have a class Calculator

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("device")
public class Calculator {

	@Value("#{4+5}")
	private int addition;
	
	@Value("#{1>1}")
	private boolean isEqual;
	
	@Value("#{5*10/5}")
	private double checkExpression;
	
	/**
	 * Inject instance variable
	 */
	@Value("#{multiplierCalculatorModule.getMultiplier()}")
	private int anotherClassValue;
	
	/**
	 * Inject in-build modules of java
	 */
	@Value("#{ T(java.lang.Math).sqrt(25) }")
	private double squareRoot;
	

	public double getSquareRoot() {
		return squareRoot;
	}

	public void setSquareRoot(double squareRoot) {
		this.squareRoot = squareRoot;
	}

	public double getCheckExpression() {
		return checkExpression;
	}

	public int getAddition() {
		return addition;
	}

	public void setAddition(int addition) {
		this.addition = addition;
	}

	public boolean isEqual() {
		return isEqual;
	}

	public void setEqual(boolean isEqual) {
		this.isEqual = isEqual;
	}

	public double isCheckExpression() {
		return checkExpression;
	}

	public void setCheckExpression(double checkExpression) {
		this.checkExpression = checkExpression;
	}

	public int getAnotherClassValue() {
		return anotherClassValue;
	}

	public void setAnotherClassValue(int anotherClassValue) {
		this.anotherClassValue = anotherClassValue;
	}

	@Override
	public String toString() {
		return "Calculator [addition=" + addition + ", isEqual=" + isEqual + ", checkExpression=" + checkExpression
				+ ", anotherClassValue=" + anotherClassValue + ", squareRoot=" + squareRoot + "]";
	}
	
	
}

```
- Below is the class for MultiplierCalculatorModule.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MultiplierCalculatorModule {

	@Value("#{2*10}")
	private int multiplier;

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
}

```

- Below is the main method, we run the main method we can see how spring expression language gives all the values.

```
package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {


	public static void main(String[] args) {

		ApplicationContext factory = new ClassPathXmlApplicationContext("com/simple/AnnotationBasedConfiguration/springConfig.xml");

		
		Calculator cal=factory.getBean("device",Calculator.class);
		System.out.println(cal.toString());
		
		
	}

}

Output:
Calculator [addition=9, isEqual=false, checkExpression=10.0, anotherClassValue=20, squareRoot=5.0]
```

- The expression in the `@Value` annotation must be a valid expression. We can check that using **SpelExpressionParser** method. Below is the code for it.

```
		SpelExpressionParser temp=new SpelExpressionParser();
		Expression exp=temp.parseExpression("22+40");
		System.out.println(exp.getValue());

Output:
62
```

- Above learnings are implement [here](https://github.com/codophilic/LearnSpring/tree/main/Simple%20Spring%20Project/demo/src/main/java/com/simple/AnnotationBasedConfiguration).

## Java Bean based Configuration
- Until now we have saw, we have specified all the configuration in a XML file. What if we can avoid it and have a java class as our configuration? is it possible? yes, using `@Configuration` annotation we can do it.
- Lets say we created a class which is our config class name as **SpringConfig** just like we create **springConfig.xml** .

```
package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

@Configuration
public class SpringConfig {

	/**
	 * 
	 * <bean class="com.simple.XMLBasedConfiguration.Alien" name="objectOfAlien" scope="singleton"> </bean>
	 */
	@Bean
	public Alien displayAlien() {
		return new Alien();
	}
	
}

```

- In this class, we have specified `@Configuration` and `@Bean` which is similar to what we defined in XML based configuration.
- Below is the Alien class.

```
package com.simple.JavaBeanConfiguration.BeanBased;


public class Alien {

	public void show() {
		System.out.println("This is an Alien");
	}
}

```

- Below is the main method, post execution we see our bean is injected successfully.

```
package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

public class SimpleSpringProject {


	public static void main(String[] args) {
		
		/**
		 * Just like in XML we mentioned the XML file path
		 * Similarly here we need to mentioned our config java class
		 */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Alien al=context.getBean(Alien.class);
        al.show();
	
	}

}


Output:
This is an Alien
```

- Java-based configuration in Spring, often referred to as JavaConfig, allows you to configure the Spring container using Java classes instead of traditional XML files. This approach uses annotations and Java classes to define beans and their dependencies, offering a type-safe and more readable way to configure Spring applications.
- Lets say we wanted to give a bean a name, here how we can do it.

```
package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

@Configuration
public class SpringConfig {

	/**
	 * 
	 * <bean class="com.simple.XMLBasedConfiguration.Alien" name="objectOfAlien" scope="singleton"> </bean>
	 * by giving name we can ask spring to provide bean by using this names, here we have given multiple names
	 * to this bean.
	 */
	@Bean( name = {"objectOfAlien","instanceOfAlien"})
	public Alien displayAlien() {
		return new Alien(); 
	}
	
}
```

- Post execution of main method, we get the output.

```
package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

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

	}

}

Output:
This is an Alien
This is an Alien
This is an Alien
```

- When we used `@Bean` annotation, we did not use `@Component` annotation on every class.
- Here in the SpringConfig class we need to define `@Bean` annotation for every class which we need to use during execution. So basically if there are thousands of bean inside a package , is it worthy to write thousands of class over here? thats where we have an another annotation name `@ComponentScan` where we specify the package and all classes present in that package are treated as individual beans by spring.
- When we use `@ComponentScan` we need to specify `@Component` on whatever class we required.
- Lets say we have Processor class.

```
package com.simple.JavaBeanConfiguration.ComponentBased;

import org.springframework.stereotype.Component;

@Component
public class Processor {

	public void process() {
		System.out.println("This is a process");
	}
}

```

- Below is the configuration required to mentioned in the SpringConfig class.

```
package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

@Configuration
@ComponentScan( basePackages = {"com.simple.JavaBeanConfiguration.ComponentBased"})
public class SpringConfig {

	/**
	 * 
	 * <bean class="com.simple.XMLBasedConfiguration.Alien" name="objectOfAlien" scope="singleton"> </bean>
	 * by giving name we can ask spring to provide bean by using this names, here we have given multiple names
	 * to this bean.
	 */
	@Bean( name = {"objectOfAlien","instanceOfAlien"})
	public Alien displayAlien() {
		return new Alien(); 
	}
	
}

```

- In the above configuration, we have defined only the package name, this includes all the classes inside it.
- Below is the main method execution.

```
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
        
        Processor p=context.getBean(Processor.class);
        p.process();

	}

}


Output:
This is a process
```

- Abover learnings are implemented [here](https://github.com/codophilic/LearnSpring/tree/main/Simple%20Spring%20Project/demo/src/main/java/com/simple/JavaBeanConfiguration)
- XML configuration provides easy way to check all the configurations withing one file but it is tedious to write those configurations whereas annotation configuration are easier to implement.

## Bean life cycle in Java Spring
- The lifecycle of any object means when & how it is born, how it behaves throughout its life, and when & how it dies. Similarly, the bean life cycle refers to when & how the bean is instantiated, what action it performs until it lives, and when & how it is destroyed.
- Bean life cycle is managed by the spring container. When we run the program then, first of all, the spring container gets started. After that, the container creates the instance of a bean as per the request, and then dependencies are injected. And finally, the bean is destroyed when the spring container is closed.

![image](https://github.com/user-attachments/assets/400bc073-20c7-4a6b-af26-a6e218656416)

- When a Spring Container creates a bean, it provides two methods to every bean by default. These are:
1) public void init()
2) public void destroy()
- However, we can change the names of methods, but method signature must be the same. If we remain the method names as it is, the code becomes easy to understand.
- The `init()` method is called after bean construction and before requesting an object. `destroy()` method is called just before the destruction of a bean.

### What are the purpose of implementing Life Cycle Methods?
1. **init()**: If we want to initialize anything such as loading some configurations, creating database connections, we can write that code in init() method.
2. **destroy()**: If we want to cleanup something such as closing database connections, we can write that code in destroy() method.

## Implementation of Life Cycle Beans

#### 1. Using XML based configuration

- Lets say we have a class XMLBasedLC

```
package com.simple.BeanLifeCycle;

public class XMLBasedLC {

	public void initXmlBased() {
		System.out.println("Init Method loaded for XMLBasedLC , loading some pre-configurations");
	}
	
	
	public void operation() {
		System.out.println("Perform some operations");
	}
	
	public void destroyXmlBased() {
		System.out.println("Destory for XMLBasedLC, destory all the connections");
	}
}

```

- Below is the config xml for life cycle, here we use `init-method` and `destory-method` attributes in configurtion to create our own custom init and destory method

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >


	<bean name="xmlBasedObj" class="com.simple.BeanLifeCycle.XMLBased" init-method="initXmlBased" destroy-method="destroyXmlBased">
	</bean>
</beans>
```

- Below is the main method written, post execution we get below output. When spring containers are shutdown the destory method is called.

```
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
	}
}

Output:
Init Method loaded for XMLBasedLC , loading some pre-configurations
Perform some operations
Destory for XMLBasedLC, destory all the connections
```

#### 2. Annotation Based

- In annotation, to create init and destory method we have `@PostConstruct` and `@PreDestroy` annotation used.
- Below is the config XML file

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >

    <!--  Enabling Component Scanning Annotations  -->
	<context:component-scan base-package="com.simple.BeanLifeCycle"/> 

</beans>
```

- Below is the class AnnotationBasedLC.

```
package com.simple.BeanLifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedLC {
	
	@PostConstruct
	public void beforeSomething() {
		System.out.println("Initializing all AnnotationBasedLC");
	}

	public void operation() {
		System.out.println("Performs some operations");
	}
	
	@PreDestroy
	public void endofAll() {
		System.out.println("Closing every thing in AnnotationBasedLC");
	}
}

```

- Post Execution main method we get the output.

```
package com.simple.BeanLifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
	    
		ApplicationContext factory1 = new ClassPathXmlApplicationContext("com/simple/BeanLifeCycle/springConfig.xml");

	    
	    AnnotationBasedLC alc=factory1.getBean(AnnotationBasedLC.class);
	    alc.operation();
	    
	    ((ClassPathXmlApplicationContext) factory1).close();

	}
}

Output:
Initializing all AnnotationBasedLC
Performs some operations
Closing every thing in AnnotationBasedLC
```

#### 3. Programmatic Approach using Interface

- To provide the facility to the created bean to invoke custom `init()` method on the startup of a spring container and to invoke the custom `destroy()` method on closing the container, we need to implement our bean with two interfaces namely InitializingBean, DisposableBean and will have to override `afterPropertiesSet()` and `destroy()` method. afterPropertiesSet() method is invoked as the container starts and the bean is instantiated whereas, the `destroy()` method is invoked just after the container is closed.
- This approach using xml configuration but only bean declaration is required and not `init-method` and `destory-method` is required.
- Below is the config xml file.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >	
	
	<bean name="interfaceBased" class="com.simple.BeanLifeCycle.InterfaceApproach" >
	</bean>
</beans>
```

- A class name InterfaceApproach is created

```
package com.simple.BeanLifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InterfaceApproach implements InitializingBean, DisposableBean {
 
    @Override
    // It is the init() method
    // of our bean and it gets
    // invoked on bean instantiation
    public void afterPropertiesSet() throws Exception
    {
        System.out.println(
            "Bean HelloWorld has been "
            + "instantiated and I'm the "
            + "init() method");
    }
    
    public void operation() {
    	System.out.println("Processing something");
    }
 
    @Override
    // This method is invoked
    // just after the container
    // is closed
    public void destroy() throws Exception
    {
        System.out.println(
            "Container has been closed "
            + "and I'm the destroy() method");
    }

}
```

- Post Execution of main method we get the output

```
package com.simple.BeanLifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringProject {

	public static void main(String[] args) {
	    
		ApplicationContext factory2 = new ClassPathXmlApplicationContext("com/simple/BeanLifeCycle/springConfig.xml");

		InterfaceApproach Ia=factory2.getBean("interfaceBased",InterfaceApproach.class);
		
		Ia.operation();
		
	    ((ClassPathXmlApplicationContext) factory2).close();


	}
}

Output:
Bean HelloWorld has been instantiated and I'm the init() method
Processing something
Container has been closed and I'm the destroy() method
```

- Above learnings are implemented [here](https://github.com/codophilic/LearnSpring/tree/main/Simple%20Spring%20Project/demo/src/main/java/com/simple/BeanLifeCycle)


## Use of Maven and Spring
- Maven is a build automation tool that helps manage project dependencies and automate the software build process. It allows developers to easily manage external libraries and dependencies required for their project. For example, let's say a developer is building a web application that requires the use of the Apache Commons IO library. Instead of manually downloading and managing the library, the developer can use Maven to automatically download and add the library to their project's classpath.

- Spring, on the other hand, is a framework for building Java applications that provides a set of pre-built modules and libraries for common tasks, such as managing objects, handling database transactions, and providing a web application framework. For example, if a developer is building a web application, they can use Spring's MVC framework to handle requests, map them to controller methods, and render views. This allows the developer to focus on writing business logic, rather than the low-level details of the web application infrastructure.

- In summary, Maven and Spring serve different purposes in the development process. Maven is primarily focused on managing project dependencies and automating the software build process, while Spring provides a comprehensive programming model and set of tools for building complex enterprise applications.

- Maven and Spring can be used together in Java application development to manage dependencies and build the project. Here's an example of how they can be used together:

- Let's say we are building a web application using Spring and need to manage the dependencies for our project. We can use Maven to manage our project dependencies and build the project.

- To get started, we would create a Maven project using the Spring archetype, which would provide us with a basic project structure and the necessary dependencies for building a Spring-based web application. Once we have created the Maven project, we can add our application-specific dependencies to the pom.xml file, which is used by Maven to manage project dependencies. For example, if our application requires the use of the Apache Commons IO library.

- Maven would then automatically download and add the required library to our project's classpath.

- Next, we would use Spring to build our web application. We would create Spring controllers to handle requests, and use Spring's data access objects (DAOs) to interact with the database.

- Finally, we would build and package our application using Maven, which would create a deployable artifact that can be run on a server.

- Now lets learn about [Spring JDBC](https://github.com/codophilic/LearnSpring/blob/main/SpringJDBC.md)




















