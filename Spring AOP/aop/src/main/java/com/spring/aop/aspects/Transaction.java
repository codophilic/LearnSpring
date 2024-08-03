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
