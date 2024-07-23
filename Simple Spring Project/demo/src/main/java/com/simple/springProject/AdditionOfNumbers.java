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
