package com.simple.JavaBeanConfiguration.ComponentBased;

import org.springframework.stereotype.Component;

@Component
public class Processor {

	public void process() {
		System.out.println("This is a process");
	}
}
