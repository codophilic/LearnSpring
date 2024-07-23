package com.simple.AnnotationBasedConfiguration;

import org.springframework.stereotype.Component;

@Component
public class AstonMartin implements Car {

	@Override
	public void engine() {
		System.out.println("AstonMartin Engine");

	}

}
