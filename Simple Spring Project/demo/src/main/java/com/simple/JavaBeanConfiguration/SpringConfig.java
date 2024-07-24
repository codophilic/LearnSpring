package com.simple.JavaBeanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.simple.JavaBeanConfiguration.BeanBased.Alien;

@Configuration
@ComponentScan( basePackages = {"com.simple.JavaBeanConfiguration.ComponentBased"})
public class SpringConfig {

	/**
	 * 
	 * <bean class="com.simple.XMLBasedConfiguration.Alien" name="objectOfAlien" scope="singleton"> </bean>
	 * by giving name we can ask spring to provide bean by using this names, here we have given multiple names
	 * to this bean.
	 */
	@Bean( name = {"objectOfAlien","instanceOfAlien"})
	public Alien displayAlien() {
		return new Alien(); 
	}
	
}
