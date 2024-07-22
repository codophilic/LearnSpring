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

### Dependency Injection (DI) and Inversion of Control (Ioc)
- DI means that you don't create objects yourself. Instead, you let an IoC container create them for you. The IoC container will also inject the dependencies of those objects into them. This means that you don't have to worry about creating and configuring objects yourself. The IoC container will do it for you.
- Dependency injection (DI) is a technique that focuses on providing the dependencies (objects or services) that a class requires from an external source, rather than having the class create or obtain those dependencies itself. The idea is to "inject" the dependencies into a class from the outside.
- Spring IoC container acts as a centralized repository of objects (beans) in your application. It creates and manages instances of objects, resolves their dependencies, and injects them where needed. It takes care of object lifecycle management and ensures that dependencies are correctly wired together.
- Now any object we want in our code it will be provided by IoC, but how shall we ask for which object we want to IoC? thats where we assign every bean an ID. Now every bean has an ID or a bean name, this ID needs to be store somewhere right? thats where we have a configuration file in spring.
- Spring uses configuration files (like XML or YAML) to define how objects (called beans) are created, configured, and wired together. These configuration files don't directly assign IDs to objects. Instead, they use a bean definition mechanism:
  - You specify the class type of the bean (e.g., com.Cars.Engine).
  - You can optionally provide a unique identifier (id="EngineOfcar") for the bean, but it's not mandatory. Spring can use class names by default.
  - The configuration allows you to set properties on the bean (e.g., engine type) and define relationships between beans (dependencies).
- In eclipse we need to install new software for spring configuration file or we can use STS.














