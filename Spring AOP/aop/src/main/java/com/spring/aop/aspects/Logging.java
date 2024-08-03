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
