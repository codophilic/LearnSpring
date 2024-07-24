package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("device")
public class Calculator {

	@Value("#{4+5}")
	private int addition;
	
	@Value("#{1>1}")
	private boolean isEqual;
	
	@Value("#{5*10/5}")
	private double checkExpression;
	
	/**
	 * Inject instance variable
	 */
	@Value("#{multiplierCalculatorModule.getMultiplier()}")
	private int anotherClassValue;
	
	/**
	 * Inject in-build modules of java
	 */
	@Value("#{ T(java.lang.Math).sqrt(25) }")
	private double squareRoot;
	

	public double getSquareRoot() {
		return squareRoot;
	}

	public void setSquareRoot(double squareRoot) {
		this.squareRoot = squareRoot;
	}

	public double getCheckExpression() {
		return checkExpression;
	}

	public int getAddition() {
		return addition;
	}

	public void setAddition(int addition) {
		this.addition = addition;
	}

	public boolean isEqual() {
		return isEqual;
	}

	public void setEqual(boolean isEqual) {
		this.isEqual = isEqual;
	}

	public double isCheckExpression() {
		return checkExpression;
	}

	public void setCheckExpression(double checkExpression) {
		this.checkExpression = checkExpression;
	}

	public int getAnotherClassValue() {
		return anotherClassValue;
	}

	public void setAnotherClassValue(int anotherClassValue) {
		this.anotherClassValue = anotherClassValue;
	}

	@Override
	public String toString() {
		return "Calculator [addition=" + addition + ", isEqual=" + isEqual + ", checkExpression=" + checkExpression
				+ ", anotherClassValue=" + anotherClassValue + ", squareRoot=" + squareRoot + "]";
	}
	
	
}
