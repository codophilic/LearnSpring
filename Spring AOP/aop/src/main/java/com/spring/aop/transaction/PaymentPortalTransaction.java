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
