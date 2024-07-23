package com.simple.AnnotationBasedConfiguration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 
 * <bean class="com.simple.AnnotationBasedConfiguration" id="javaCodeId" primary="true"/>
 */

@Component
@Primary 
public class Ferrari implements Car {

	@Override
	public void engine() {
		System.out.println("Ferrari Engine");

	}

}
