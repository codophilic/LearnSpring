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
