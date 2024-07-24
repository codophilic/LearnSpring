package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MultiplierCalculatorModule {

	@Value("#{2*10}")
	private int multiplier;

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
}
