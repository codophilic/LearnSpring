package com.simple.BeanLifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedLC {
	
	@PostConstruct
	public void beforeSomething() {
		System.out.println("Initializing all AnnotationBasedLC");
	}

	public void operation() {
		System.out.println("Performs some operations");
	}
	
	@PreDestroy
	public void endofAll() {
		System.out.println("Closing every thing in AnnotationBasedLC");
	}
}
