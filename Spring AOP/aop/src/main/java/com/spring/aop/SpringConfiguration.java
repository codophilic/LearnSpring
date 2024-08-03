package com.spring.aop;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.spring.aop")
@EnableAspectJAutoProxy // Enabling AOP so that spring knows we have class which follows AOP
public class SpringConfiguration {

}
