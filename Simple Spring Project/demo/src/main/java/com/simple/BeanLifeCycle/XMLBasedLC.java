package com.simple.BeanLifeCycle;

public class XMLBasedLC {

	public void initXmlBased() {
		System.out.println("Init Method loaded for XMLBasedLC , loading some pre-configurations");
	}
	
	
	public void operation() {
		System.out.println("Perform some operations");
	}
	
	public void destroyXmlBased() {
		System.out.println("Destory for XMLBasedLC, destory all the connections");
	}
}
