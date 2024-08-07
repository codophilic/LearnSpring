# About AOP (Aspect-Oriented Programming)
- Imagine you have an online shopping cart.
- People will add or remove items and accordingly the quatities will be updated, calculation of total price will be done, if any discount is applicable then it will be applied , processing payment will be done etc..
- Now the core feature in this online shopping cart for individual user includes
  - User adding or removing items
  - Updating quantities
  - Calculation total price
- Other concerns which are essential but not part of core are
  - Logging users action
  - Applying discounts
  - Checking inventory
  - Processing payments
- Now these concerns could be bind with each core (each user will be assigned with inventory, logging, payments etc.) or cut it out from the core and have a generalize layer which deals with all users shopping cart.
- So which is better? to have a generalize layer rights which deals with all the users shopping cart , that is AOP.
- **AOP helps manage cross-cutting concerns—tasks ( like payments, logging, applying discounts etc..) that affect multiple parts (individual users) of an application—separately from the main logic (core features like adding or removing items from the cart)**.
- **Think of it as handling additional tasks (cross-cutting concerns) like logging user actions, checking inventory, and applying discounts. AOP allows you to define these tasks separately and automatically weave them into the shopping cart operations where needed.**
- **This cross-cutting concerns are referred as aspect**


![image](https://github.com/user-attachments/assets/f457afe9-98fc-457f-920b-977c5f7f8853)

- As per the defination it seems we would have a separate class which will consists of method and those method can be manually called before/after of execution of core functionalities, so what's the big difference without AOP?

### Manual Handling vs. AOP
- **Manual Handling Approach**:
  - Code Clutter: When you manually handle cross-cutting concerns, you have to explicitly call the methods for logging, security checks, transaction management, etc., within your main business logic. This leads to repetitive and cluttered code.
  - Tight Coupling: Your business logic becomes tightly coupled with cross-cutting concerns. Any change in the logging or security mechanism would require changes in multiple places.
  - Scalability Issues: As your application grows, managing these concerns manually can become cumbersome and error-prone.

- **AOP Approach**:
  - Separation of Concerns: AOP cleanly separates cross-cutting concerns from the main business logic. Aspects are defined separately and applied declaratively, making the core logic cleaner and more maintainable.
  - Reduced Code Duplication: You define an aspect (e.g., logging) once, and it can be applied to multiple methods or classes without duplicating the logging code in each place.
  - Loose Coupling: Your business logic remains independent of the cross-cutting concerns, allowing changes to aspects without modifying the core logic.
  - Dynamic Weaving: AOP allows you to dynamically apply or change aspects at runtime without altering the existing codebase.
 
- AOP improves the organization of your code by keeping different types of functionality separate, reducing repeated code, and making your main business logic clearer and easier to manage. It enhances modularity by separating concerns, reduces code duplication, and promotes cleaner and more focused core logic. 

## AOP VS OOP
#### Object-Oriented Programming (OOP):
- OOP is a programming paradigm that focuses on creating objects that represent entities and their behaviors. Considering online shopping car example,
  - Encapsulation: In the shopping cart example, encapsulation would mean bundling data (items, quantities) and methods (addItem, removeItem, calculateTotal) into the ShoppingCart class.
  - Inheritance: If there are different types of shopping carts (e.g., GuestCart, MemberCart or PremiumCart), they could inherit from a base ShoppingCart class.
  - Polymorphism: Different types of discounts (e.g., percentage-based, buy-one-get-one-free) can be applied in a polymorphic way.
- In OOP all the cross-cutting concerns and core functionalities are intermixed in some class which creates duplication of code. This is avoided in AOP thus providing modularity or seperation of concerns.

![image](https://github.com/user-attachments/assets/30766a4b-f0e9-4bd2-8eb8-6b6c3ab9ea90)

- In the online shopping cart example, let us consider transaction as our aspect which is require to be implemented post calculation of total amount. How will the transaction aspect will know where to be called and when to be call while execution of calculation of total amount? thats why AOP has concepts of JoinPoint, Advice, Weaving and Pointcut which determine where and when the transaction aspect should be executed.
- AOP is implemented by multiple frameworks but commonly used are AspectJ and Spring.

## AOP Core Concepts

![image](https://github.com/user-attachments/assets/3a92b7bb-236a-4695-b7b1-4c7edd469685)

1. **Aspect**: An aspect is a class that implements enterprise application concerns that cut across multiple classes, such as transaction management.
2. **Join Point:**
  - In online shopping cart. Every action you take on the cart is a potential join point. This could be adding an item, removing an item, checking out, applying a discount, etc. These are specific moments in the shopping cart's lifecycle where something could happen.
  - A join point represents a specific point during the execution of a program.
3. **Pointcut:**
    - A pointcut is an expression that determines where advice should be applied in the application.
    - A pointcut is like a filter that selects which join points you're interested in. If you only want to apply a discount when the cart total exceeds a certain amount, your pointcut would be "cart total greater than 1000". This filter will only activate the advice when the cart total meets this condition.
4. **Advice**
    - Advice is the action you want to take at the selected join points out of all set of join points. In our shopping cart example, the advice could be applying a 10% discount to the cart total. This action will be executed whenever the pointcut condition is met (cart total greater than 1000).    
5. **Weaving**
  - The process of combining the transaction management aspect with the checkout method. This can be imagined as weaving threads together to create a fabric.
  - Weaving is the process of integrating or combining core concepts (like the main business logic) with aspect units (like cross-cutting concerns such as transaction management, logging, or security).
  - In Spring, weaving can be accomplished through either compile-time or runtime weaving.
    - Compile-Time: The process of integrating aspects into the application code during the compilation process. The resulting code already includes the aspect logic.
    - Run-Time: The process of integrating aspects into the application code at runtime using dynamic proxies. The aspect logic is added on the fly when the application is running.
    - Spring AOP primarily uses runtime weaving. This means that the aspects are applied to your application's objects when they are created and used. This offers flexibility as you can modify aspects without recompiling the entire application. However, it might have a slight performance overhead compared to compile-time weaving.

##### Different types of Advice
- Before Advice: Executed before the join point, this advice allows developers to perform actions such as logging or validation before the actual method execution.
- After Returning Advice: Executed after the method successfully returns a value. Useful for actions like logging the returned result.
- After Throwing Advice: Executed if the method throws an exception. Useful for logging or handling exceptions.
- After Advice (finally): Executed after the method, regardless of its outcome. Useful for cleanup tasks or finalization.
- **Advice methods can have pointcut expressions directly in their annotations (provide the method name directly) or reference a named pointcut method (providing point cut reference name).**

## Spring AOP

- Lets write some codes using Java beans as spring configurations.
- First download the depedencies

```
  
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>6.1.6</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.6</version>
</dependency>

<!-- Spring AOP -->
<!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>6.1.6</version>
</dependency>

<!-- AspectJ Runtime -->
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjrt -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.9.6</version>
    <scope>runtime</scope>
</dependency>


<!-- This is required for AspectJ-based weaving at runtime. -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version> 
</dependency>
```

- Below is spring configuration using Java beans

```
package com.spring.aop;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.spring.aop")
@EnableAspectJAutoProxy // Enabling AOP so that spring knows we have class which follows AOP
public class SpringConfiguration {

}
```

- Below is our Shopping Cart class

```
package com.spring.aop.cart;

import org.springframework.stereotype.Component;


@Component
public class ShoppingCart {
	
	/**
	 * Tracks User items
	 */
	private int userItems=0;

	public int getUserItems() {
		return userItems;
	}

	public void setUserItems(int userItems) {
		this.userItems = userItems;
	}

	/**
	 * User takes a cart for shopping
	 */
	public void userTakesCart() {
		System.out.println("Class - ShoppingCart: User took a cart");
	}
	
	/*
	 * User adds a item in cart
	 */
	public String addItems() {
		this.userItems+=1;
		System.out.println("Class - ShoppingCart: 1 item added successfully, total items - "+this.userItems);
		return "success";
	}
	
	/**
	 * User removes items from his/her cart
	 */
	public int removesItems(int numberOfItemsToRemoved) {
		this.userItems-=numberOfItemsToRemoved;
		System.out.println("Class - ShoppingCart: "+numberOfItemsToRemoved+" item removed successfully, total items - "+this.userItems);
		return this.userItems;
	}
	
	/**
	 * Checkout of items from a cart
	 */
    public int checkout() {
        System.out.println("Class - ShoppingCart: Checking out total "+this.userItems+" items");
        return this.userItems;
    }
    
    /**
     * Authorize user before request for payment
     */
    public void authorizeUserBeforePayment() {
    	System.out.println("Class - ShoppingCart: User Authorized");
    }
      
}
```

- Below is Payment Portal Transaction class

```
package com.spring.aop.transaction;


import org.springframework.stereotype.Component;

@Component
public class PaymentPortalTransaction {

	public String paymentGateway(float amount,String pin) {
		if("1111".equals(pin)) {
			System.out.println("Class - PaymentPortalTransaction, pin is valid");
			return "success";
		}
		return "failure";
	}

}
```

- Below are all the aspect created which includes logging, authorization and transaction aspects.

```
Logging Aspect:

package com.spring.aop.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Declaring Logging class as our cross cutting concern or aspect
 */
@Aspect
@Component
public class Logging {
	
	/**
	 * - Any aspect requires a Pointcut, so here for Logging aspect we have given Pointcut reference
	 * 	 by mentioned the method directly
	 */

	/**
	 * Advice Type = Before
	 * 	- @Before requires argument as values which consist of execution of Pointcut
	 * 	- The Pointcut could be fully qualified method name or reference method name.
	 * 	- Here , as an simple example we have provided fully qualified name of method
	 * 	- This method logs whenever a user takes a cart
	 */
	@Before(value = "execution(public void com.spring.aop.cart.ShoppingCart.userTakesCart())")
	public void beforeLogging() {
		System.out.println("Aspect - Logging: User details logged");
	}
	
	/**
	 * Advice Type = After
	 * 	- Similarly like @Before , @After requires value of Pointcut reference.
	 * 	- Here we have provided method name but not a specific method, a wildcard method name which can belong 
	 * 	  to any package.
	 *  - "execution(* *.*Items(..))" -> any method name which has ending 'Items' and may or may not have input
	 *     arguments irrespective of return type, will be JoinPoint for afterLogging() method
	 */
	@After("execution(* *.*Items(..))")
	public void afterLogging() {
		System.out.println("Aspect - Logging: User operation related to an item logged");
	}
	
	/**
	 * Advice Type = AfterReturning
	 * 	- On completed of method without any error or exception, AfterReturning advice will execute
	 * 	- To fetch whatever a method returns, we need to declare a variable 'returning="result"'
	 * 	  which is an object.
	 * 	- This object will be argument of the advice method
	 */
	@AfterReturning(pointcut="execution(* com.spring.aop.cart.ShoppingCart.checkout())",returning="result")
	public void afterSuccessOnly(Object result) {
		System.out.println("Aspect - Logging: Checkout "+result+" items successfully, proceeding towards payment");
	}
}



Authorization Aspect:

package com.spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.*;

/**
 * Declaring AuthorizeUser class as our cross cutting concern or aspect
 */
@Aspect
@Component
public class AuthorizeUser {

	/**
	 * - Any aspect requires a Pointcut, so here for AuthorizeUser aspect we have created a Pointcut reference
	 * 	 and pass that pointcut reference name to advice 
	 */
	@Pointcut("execution(* com.spring.aop.cart.ShoppingCart.authorizeUserBeforePayment())")
	public void authorizationOfUserPointCut() {}
	
	/**
	 * Advice Type = Before
	 * 	- Pointcut reference name 'authorizationOfUserPointCut()' is given to @Before advice
	 * 	- To provide multiple pointcut reference name in advice we can define like below
	 * 	  pointCutRef1() && pointCutRef2() || pointCutRef3()
	 */
	@Before("authorizationOfUserPointCut()")
	public void authorizationOfUser() {
		System.out.println("Aspect - AuthorizeUser, user details are authorized...");
	}

}



Transaction Aspect:

package com.spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Declaring Transaction class as our cross cutting concern or aspect
 */
@Aspect
@Component
public class Transaction {
	
	/**
	 * - Any aspect requires a Pointcut, so here for AuthorizeUser aspect we have created a Pointcut reference
	 * 	 and pass that pointcut reference name to advice 
	 */
	
	/**
	 * Here we have specified all the methods mentioned within the class PaymentPortalTransaction
	 * are Join point which will have a pointcut reference to paymentAmountDebitedFromBankPointCut
	 */
	@Pointcut("within(com.spring.aop.transaction.PaymentPortalTransaction)")
	public void paymentAmountDebitedFromBankPointCut() {}
	
	
	/**
	 * To get input values passed to a method (Join Point) during execution can be access
	 * using JoinPoint
	 */
	@After("paymentAmountDebitedFromBankPointCut()")
	public void paymentAmountDebitedFromBank(JoinPoint jp) {
		
		System.out.println("Aspect - Transaction: Amount - "+jp.getArgs()[0]+" is debited");
	}
}
```

- Main method

```
package com.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aop.cart.ShoppingCart;
import com.spring.aop.transaction.PaymentPortalTransaction;

public class MainMethod 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    	
    	/**
    	 * Get bean
    	 */
    	ShoppingCart sc= context.getBean(ShoppingCart.class);
    	
    	/**
    	 * Join Points
    	 */
    	for(int i=0;i<5;i++) {
    		sc.addItems();
    	}
    	sc.removesItems(2);
    	if(sc.getUserItems()>0) {
    		sc.checkout();
    		sc.authorizeUserBeforePayment();
        	PaymentPortalTransaction ppt= context.getBean(PaymentPortalTransaction.class);
        	ppt.paymentGateway(1000, "1111");

    	}
    }
}


Output:
Class - ShoppingCart: 1 item added successfully, total items - 1
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: 1 item added successfully, total items - 2
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: 1 item added successfully, total items - 3
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: 1 item added successfully, total items - 4
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: 1 item added successfully, total items - 5
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: 2 item removed successfully, total items - 3
Aspect - Logging: User operation related to an item logged
Aspect - Logging: User operation related to an item logged
Class - ShoppingCart: Checking out total 3 items
Aspect - Logging: Checkout 3 items successfully, proceeding towards payment
Aspect - AuthorizeUser, user details are authorized...
Class - ShoppingCart: User Authorized
Class - PaymentPortalTransaction, pin is valid
Aspect - Transaction: Amount - 1000.0 is debited
```

- Reference [video](https://www.youtube.com/watch?v=xdl1b97GANk&t=1474s)
- Above learnings are implemented [here](https://github.com/codophilic/LearnSpring/tree/main/Spring%20AOP/aop/src/main/java/com/spring/aop).
- Now lets learn about [Spring Security](https://github.com/codophilic/LearnSpring/blob/main/Spring%20Security.md)

