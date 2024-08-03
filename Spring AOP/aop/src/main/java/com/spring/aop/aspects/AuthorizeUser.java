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
