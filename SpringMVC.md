# About Spring MVC

- [Learn about MVC first](https://github.com/codophilic/LearnSpring/blob/main/Architectures%20and%20Design%20Pattern.md).
- Spring MVC is a sub-framework of Spring which is used to build web applications.
- It is built on a Servlet API
- Spring MVC provides additional component along with MVC design patterns.
- Lets understand the Spring MVC flow.

![alt text](image.png)

- Whenever client sents a request, the request first goes to the **Front Controller** (also known as **DispatcherServlet**). The Spring Web model-view-controller (MVC) framework is designed around a **DispatcherServlet**.
- Now when the Front Controller gets a request, it delegates the request to a **Controller/Handler**. Now there could be multiple controllers whats why one way to understand is that we have a **Front Controller** decides to whom (Controller) it has to hand over the request, based on the URL mappings or **RequestMapping**. So lets say if client as enter `/getStudent` so the controller who manages the `/getStudent` url will be delegated by Front Controller.
- The respected controller that took the request, processes the request, by sending it to suitable service class, if requires the DAO layers is access and the data is send back to the controller.
- The respected controller creates a model using the data. It also finds out which view or page is required to show this data.
- The controller passes or delegates the response with this created model and lookup view or page to the Front Controller.
- Now the Front Controller passes this response to **ViewResolver** which resolves the page name (This step involves determining which page template should be used to render the response) , figures out the page name and add this model data into the page, transforming it from static to dynamic web page and sends back to the Front Controller.
- The Front Controller sends back response to client.


- Lets start implementing it.
- Create a **maven-archetype-web** project and not quickstart project.
- First we need to download the dependencies

```
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.0.1</version>
  <scope>provided</scope>
</dependency>
    
    <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.4.RELEASE</version>
</dependency>
```

- Now we need to invoke Front Controller or DispatchServlet in the web xml on server startup.

```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  
  <!-- Configure Dispatcher Servlet -->
  <!-- So whenever the server is started up, Dispatcher Servlet will be invoked or initialized -->
    <servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

	<!--  
	Based on the mapping, if users enters the url any page using our context path (springmvc) , frontcontroller will accept the request
	  	URL examples
	  	- localhost:8080/springmvc/getPage
	  	- localhost:8080/springmvc/getPageinfo
	The DispatcherServlet will handle all requests under the context path /springmvc.
-->
    <servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
  <!-- 
  	How will spring know where the bean mapping is done? is it based on the servletName-servlet.xml
  	So basically here we have created a servlet name 'spring' (<servlet-name>spring</servlet-name>)
  	which is followed by Spring framework. So using the convection spring will get to know where are the bean mapping is done.
  	Spring looks for a file named servletName-servlet.xml (in this case, spring-servlet.xml) in the WEB-INF directory for configuration settings, including bean definitions and view resolver configurations.
   -->
</web-app>
```

- Now in the web.xml we have given spring as our servlet name, so we need to create a file with servletName-servlet.xml (under WEB-INF) folder where all the spring related configuration will be done.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >

    <!--  Enabling Component Scanning Annotations  -->
	<context:component-scan base-package="springmvc.controller"/> 

<!-- Defining view resolver and passing properties of it, which it requires for resolving -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" name="viewResolver">
	<!-- Prefix tells spring where all the pages are present -->
	<property name="prefix" value="/WEB-INF/pages/"></property>
	<!-- Suffix tells what extension of files are to be considered (here all .jsp files) -->
	<property name="suffix" value=".jsp"></property>
	
	<!-- Basically Controllers gives view name, FrontController passes this view to ViewResolver
		So example if Controller gives welcome, then the ViewResolver returns the page /WEB-INF/pages/welcome.jsp
	 -->
	
</bean>

</beans>
```

- Here for Spring we will be using annotation based configuration, so we have create a MainController which is our Front Controller and we have provided various RequestMapping which basically tells Front Controller that if any url ending with `welcome` call this method and take the view name from that method.

```
package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 */
@Controller
public class MainController {

	/**
	 * URL -> http://localhost:8080/mvc/welcome
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		System.out.println("Returns page name welcome, thus /WEB-INF/pages/welcome.jsp");
		return "welcome";
	}
	
	/**
	 * URL -> http://localhost:8080/mvc/error
	 */
	@RequestMapping("/error")
	public String error() {
		System.out.println("Returns page name error, thus /WEB-INF/pages/error.jsp");
		return "error";
	}
	
	
}
Output:
Returns page name welcome, thus /WEB-INF/pages/welcome.jsp
Returns page name error, thus /WEB-INF/pages/error.jsp
```

- Welcome page url : http://localhost:8080/mvc/welcome

![alt text](image-1.png) 

- Error page url : http://localhost:8080/mvc/error 

![alt text](image-2.png)  

- Default page url : http://localhost:8080/mvc/

![alt text](image-3.png)

>[!IMPORTANT]
> Don't append .jsp sufix at the end of your page name.






































