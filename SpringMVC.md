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
- The controller passes or delegates the response with this created model and lookup view or page (**model and view**) to the Front Controller.
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
> The default page url end-point does not have /index. If you add it , you will get recieve 404 tomcat error details page.
> Don't append .jsp sufix at the end of your page name. It will be only url.

- The above examples were based on static page, now lets work with dynaminc page. So to add dynamic data into view, controller provides a model and view in an Object.
- Let us see in the below example, we have an JSP file which display the value generated by the controller.

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/**
	 * URL -> http://localhost:8080/mvc/modelData
	 */
	@RequestMapping("/modelData")
	public String modelDataPageDetails(Model model) {
		
		/**
		 * Adding all the data into an object model
		 * providing a attribute key-name and a value.
		 * This value is fetched from HttpsRequest in JSP
		 */
		model.addAttribute("name","Harsh");
		model.addAttribute("age",22);
		List<String> techstacks=new ArrayList<>();
		techstacks.add("Spring MVC");
		techstacks.add("Java");
		model.addAttribute("alltechstacks",techstacks);
		return "modelData";
	}
	
	
}
```

- Below is the JSP file for modelData.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model data</title>
</head>
<body>
	<h1>Display Data using Model Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->
	<%@ page import="java.util.List" %>
	
	<% 
	String name=(String)request.getAttribute("name");
	Integer age=(Integer)request.getAttribute("age");
	List<String> alltechs=(List<String>) request.getAttribute("alltechstacks");
	%>
	
	<p>
	Name is <%=name %><br/>
	Age is <%=age %><br/>
	List of techs: </br>
	<%
		for(String i : alltechs){
			
			out.println(i); 	%> </br>
	<%

		}
	
	%>
	
	</p>
	
</body>
</html>
```

- Post Execution of MainController 

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/**
	 * URL -> http://localhost:8080/mvc/modelData
	 */
	@RequestMapping("/modelData")
	public String modelDataPageDetails(Model model) {
		
		/**
		 * Adding all the data into an object model
		 * providing a attribute key-name and a value.
		 * This value is fetched from HttpsRequest in JSP
		 */
		model.addAttribute("name","Harsh");
		model.addAttribute("age",22);
		List<String> techstacks=new ArrayList<>();
		techstacks.add("Spring MVC");
		techstacks.add("Java");
		model.addAttribute("alltechstacks",techstacks);
		return "modelData";
	}
	
	
}
```

![alt text](image-4.png) 

- Hold on, similar approach is been seen when we take values from normal servlet class and feed those values in JSP. In Spring MVC the controller sents model as well as view right? the Controller passes the view and model to viewResolver, here above we are passing only model.
- This is one of the approach to sent data to the viewResolver where the dynamic content is fetched from `request.getAttribute()`.
- Lets see how to pass both model as well as view.
- Below is modelAndView.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model and View</title>
</head>
<body>
<h1>Display Data using Model And View Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->
	<%@ page import="java.util.List" %>
	
	<% 
	String name=(String)request.getAttribute("name");
	Integer age=(Integer)request.getAttribute("age");
	List<String> alltechs=(List<String>) request.getAttribute("alltechstacks");
	%>
	
	<p>
	Name is <%=name %><br/>
	Age is <%=age %><br/>
	List of techs: </br>
	<%
		for(String i : alltechs){
			
			out.println(i); 	%> </br>
	<%

		}
	
	%>
	
	</p>
</body>
</html>
```

- Post execution we can see the output.

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 */
@Controller
public class MainController {

	/**
	 * URL -> http://localhost:8080/mvc/modelAndView
	 */
	@RequestMapping("/modelAndView")
	public ModelAndView sendModelviewData() {
		ModelAndView mav=new ModelAndView();
		/**
		 * Add all the data object in ModelAndView
		 */
		
		mav.addObject("name","Harsh");
		mav.addObject("age",22);
		List<String> techstacks=new ArrayList<>();
		techstacks.add("Spring MVC");
		techstacks.add("Java");
		mav.addObject("alltechstacks",techstacks);
		
		/**
		 * Add the view name 
		 */
		mav.setViewName("modelAndView");
		return mav;
	}
	
}
```

![alt text](image-5.png) 

- The Model object is used to add attributes to the model, which are then accessible in the view (such as a JSP) via request attributes. A method in a controller annotated with @Controller can have a Model parameter.
- ModelAndView is a container for both the model and the view. It allows you to set both the view name and the model attributes in a single object.
- **Use Model and String Return Type**:
    - When you prefer simplicity and clarity.
    - When the focus is on returning a logical view name and adding model attributes separately.
    - It is often more concise and aligns well with the convention-over-configuration approach.

- **Use ModelAndView**:
    - When you want to set both the model attributes and the view name in a single object.
    - When you need to return different views or handle complex scenarios where the view might change dynamically.
    - It can provide more explicit control over the view rendering process.

- Can we avoid this writting `request.getAttribute` multiple times and simply pass just the name of that attribute? yes, this can be done using expression language.
- So we have a modelAndViewJstl.jsp page

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model and View</title>
</head>
<body>
<h1>Display Data using Model And View Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->

	<p>
	Name is ${name}<br/>
	Age is ${age}<br/>
	List of techs: ${alltechstacks}
	
	</p>
</body>
</html>
```

- Post execution of main method , our page wil display the values

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 */
@Controller
public class MainController {

	/**
	 * URL -> http://localhost:8080/mvc/modelAndViewJstl
	 */
	@RequestMapping("/modelAndViewJstl")
	public ModelAndView sendModelViewDataUsingJstl() {
		ModelAndView mav=new ModelAndView();
		/**
		 * Add all the data object in ModelAndView
		 */
		
		mav.addObject("name","Harsh");
		mav.addObject("age",22);
		List<String> techstacks=new ArrayList<>();
		techstacks.add("Spring MVC");
		techstacks.add("Java");
		mav.addObject("alltechstacks",techstacks);
		
		/**
		 * Add the view name 
		 */
		mav.setViewName("modelAndViewJstl");
		return mav;
	}
	
}
```

![alt text](image-6.png)

- Wait why aren't our list items not getting print out sequentially? for that we need to use JSTL
- Lets us use JSTL (JavaServer Pages Standard Tag Library). So to use JSTL we need to download the dependencies and add this `<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>` to the top of the JSP page.

```
    <dependency> 
	<groupId>javax.servlet</groupId> 
	<artifactId>jstl</artifactId> 
	<version>1.2</version> 
</dependency>
```

- Below is the modelAndViewJstl.jsp file

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Model and View</title>
</head>
<body>
<h1>Display Data using Model And View Only</h1>
	<!-- JSP files is converted into servlet by tomcat -->

	<p>
	Name is ${name}<br/>
	Age is ${age}<br/>
	List of techs:
	
	<!-- One way to print it -->
	<h3>One Way to print using expression</h3>
	<c:forEach var="i" items="${alltechstacks}">
		<h4>${i}</h4>
	</c:forEach>

	<h3>Second Way to print using c:out</h3>
	<!-- Second way to print it -->
	<c:forEach var="i" items="${alltechstacks}">
		<h4><c:out value="${i }"/></h4>
	</c:forEach>	
	</p>
</body>
</html>
```

![alt text](image-7.png)

- We have applied `@RequestMapping` on a method , what happens if we applied it on the class?, so the url which we were accessing will change to a new url which will consist of the RequestMapping url name.
- See the below example

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 * If we add @RequestMapping on a class each endpoint url will have this handler url as prefix
 * So example the url without it we were accessing -> http://localhost:8080/mvc/welcome
 * Will change to http://localhost:8080/mvc/mypage/welcome
 */
@Controller
@RequestMapping("/mypage")
public class MainController {

	/**
	 * URL -> http://localhost:8080/mvc/welcome
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		System.out.println("Returns page name welcome, thus /WEB-INF/pages/welcome.jsp");
		return "welcome";
	}	
}

```

![alt text](image-8.png) 

- If we add @RequestMapping on a class each endpoint url will have this handler url as prefix. 
- So example the url without it we were accessing -> `http://localhost:8080/mvc/welcome` will change to `http://localhost:8080/mvc/mypage/welcome`

#### About RequestMapping annotation
- `@RequestMapping` is an annotation in Spring MVC that is used to map web requests to specific handler methods or classes. It acts as a bridge between an HTTP request and a method in a controller, allowing you to specify the URL path, HTTP method (such as GET, POST, PUT, DELETE), and other parameters that determine how the request should be handled. `@RequestMapping` is an interface which has implementation method `RequestMappingHandlerMapping`.
- When a user enters a URL in their browser, the request is processed by the Spring DispatcherServlet, which is configured to intercept requests. The DispatcherServlet uses a HandlerMapping implementation (like RequestMappingHandlerMapping) to map the URL to the appropriate controller and method based on the `@RequestMapping` annotation.
- Uptil now we have sent data from controller to view, now how to sent data from view to controller.
- Now lets have a form which will take input and pass that to a controller.
- 































