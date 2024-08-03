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
