package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Driver {

	@Autowired
	@Qualifier("astonMartin")
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void start() {
		System.out.println("Starting Engine..");
		car.engine();
	}
	
}
