package com.simple.AnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <bean class="com.simple.AnnotationBasedConfiguration" name="objectofLaptop"/>
 */
@Component("lap")
public class Laptop {
	
	/**
	 * <bean class="com.simple.XMLBasedConfiguration.Laptop" id="laptopId" autowire=
	 * "byType" > <constructor-arg value="110"></constructor-arg>
	 * <property name="code" ref="codeId"></property> </bean>
	 *
	 */
	@Autowired
	private PythonCode pyCode;

	public void start() {
		System.out.println("Laptop is starting ...");
		pyCode.run();
	}

	public PythonCode getPyCode() {
		return pyCode;
	}

	public void setPyCode(PythonCode pyCode) {
		this.pyCode = pyCode;
	}
}
