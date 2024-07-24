package com.simple.BeanLifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InterfaceApproach implements InitializingBean, DisposableBean {
 
    @Override
    // It is the init() method
    // of our bean and it gets
    // invoked on bean instantiation
    public void afterPropertiesSet() throws Exception
    {
        System.out.println(
            "Bean HelloWorld has been "
            + "instantiated and I'm the "
            + "init() method");
    }
    
    public void operation() {
    	System.out.println("Processing something");
    }
 
    @Override
    // This method is invoked
    // just after the container
    // is closed
    public void destroy() throws Exception
    {
        System.out.println(
            "Container has been closed "
            + "and I'm the destroy() method");
    }

}
