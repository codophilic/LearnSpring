package com.simple.XMLBasedConfiguration;

public class Alien {
	
	private int age;


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		System.out.println("Setters method is set with value "+age);
		this.age = age;
	}

	public Alien() {
		System.out.println("Object created via constructor");
	}

	public void show() {
		System.out.println("Inside Alien");
	}
}
