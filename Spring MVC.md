# About Spring MVC

- [Learn about MVC first](https://github.com/codophilic/LearnSpring/blob/main/Architectures%20and%20Design%20Pattern.md).
- Spring MVC is a sub-framework of Spring which is used to build web applications.
- It is built on a Servlet API
- Spring MVC provides additional component along with MVC design patterns.
- Lets understand the Spring MVC flow.

![alt text](Images/springmvc/image.png)

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

![alt text](Images/springmvc/image-1.png) 

- Error page url : http://localhost:8080/mvc/error 

![alt text](Images/springmvc/image-2.png)  

- Default page url : http://localhost:8080/mvc/ 

![alt text](Images/springmvc/image-3.png)

>[!IMPORTANT]
> The default page url end-point does not have /index. If you add it , you will get recieve 404 tomcat error details page.
> Don't append .jsp sufix at the end of your page name. It will be only url.
> If we organize jsp files into different folders, then we need to specify `<property name="prefix" value="/WEB-INF/"></property>` as prefix in spring-servlet.xml and need to ensure that method which returns the view must also return the corresponding folder name and jsp file name, so example for above code will be like "pages/welcome" , "pages/error" . This will help ViewResolver to find the path using prefix value which are `/WEB-INF/pages/welcome.jsp` or `/WEB-INF/pages/error.jsp`.


- The above examples were based on static page, now lets work with dynamic page. So to add dynamic data into view, controller provides a model and view in an Object.
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

![alt text](Images/springmvc/image-4.png) 

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

![alt text](Images/springmvc/image-5.png) 

- The Model object is used to add attributes to the model, which are then accessible in the view (such as a JSP) via request attributes. A method in a controller annotated with `@Controller` can have a Model parameter.
- ModelAndView is a container for both the model and the view. It allows you to set both the view name and the model attributes in a single object.
- `addObject` is a method of the ModelAndView class. It is used to add attributes to the model, which is essentially a map of key-value pairs that will be passed to the view for rendering.
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

![alt text](Images/springmvc/image-6.png)

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

![alt text](Images/springmvc/image-7.png)

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

![alt text](Images/springmvc/image-8.png) 

- If we add @RequestMapping on a class each endpoint url will have this handler url as prefix. 
- So example the url without it we were accessing -> `http://localhost:8080/mvc/welcome` will change to `http://localhost:8080/mvc/mypage/welcome`

#### About RequestMapping annotation
- `@RequestMapping` is an annotation in Spring MVC that is used to map web requests to specific handler methods or classes. It acts as a bridge between an HTTP request and a method in a controller, allowing you to specify the URL path, HTTP method (such as GET, POST, PUT, DELETE), and other parameters that determine how the request should be handled. `@RequestMapping` is an interface which has implementation method `RequestMappingHandlerMapping`.
- When a user enters a URL in their browser, the request is processed by the Spring DispatcherServlet, which is configured to intercept requests. The DispatcherServlet uses a HandlerMapping implementation (like RequestMappingHandlerMapping) to map the URL to the appropriate controller and method based on the `@RequestMapping` annotation.
- Uptil now we have sent data from controller to view, now how to sent data from view to controller.
- Now lets have a form.jsp which will take input and pass that to a controller, url - `http://localhost:8080/mvc/myform/contact`

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ar" dir="rtl">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-dpuaG1suU0eT09tx5plTaGMLBsfDLzUCCUXOY2j/LSvXYuG6Bqs43ALlhIqAJVRb" crossorigin="anonymous">

  </head>
  <body>

	<div class="container mt-5 align-items-center">
			<h3 class="text-center"> Registration Form using RequestParam</h3>
	<!-- 
		Due to action parameter view will pass on the request to controller and using the action value processing-details
		controller will call the respected handler method.
		Making the transmission secure using on of the HTTPs method
	 -->	
	<form action="processing-details" method="post">
		<div class="form-group" align="left">
		  <label for="emailAddressInput" >Email address</label>
		  <input name="fieldEmail" type="email" id="emailAddressInput" class="form-control" aria-describedby="emailHelp" placeholder="name@example.com">
		 
		</div>
		<div class="form-group" align="left">
		  <label for="userNameInput" >User name</label>
		  <input name="fieldUserName" type="text" id="userNameInput" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="left">
		  <label for="userPassIput" >User Password</label>
		  <input name="fieldPassword" type="password" id="userPassIput" class="form-control" aria-describedby="emailHelp">
		</div>
		<!-- Optional field age -->
		<div class="form-group" align="left">
		  <label>Security Code</label>
		  <input name="fieldSecurityCode" type="text" id="code" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="center">
			
			<button type="submit" class="btn btn-primary mt-5">Login</button>
		</div> 
	  </form>
	</div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
  </body>
</html>
```

- Here there are 4 fields, email ID, user name, password and security code.
- We have created a new class for Controller named `FormController`  and a method `processingDetails` which accepts the form input details. Wait how does it accepts the input field details? , under the form there is an attribute `action="processing-details"` which tells the controller to pass on to which RequestMapper or handler.
- The HTTPS `method="post"` secures the communication. So basically when users click on Login button the Request Mapper `processing-details` is called , below is the code for `FormController`.

```
package mvc.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/myform")
public class FormController {

	/**
	 * URL -> http://localhost:8080/mvc/myform/processing-details
	 * When user click on Login button, whatever url mentioned in the <form action> is called
	 * by the controller and it also checks if the Request Method is post which is defined in 
	 * form
	 * 
	 * Default Get method is used if not specified
	 * 
	 * Now here it some input fields of form could be optional, default all arguments are considered
	 * as mandatory.
	 */
	@RequestMapping(path = "/processing-details", method=RequestMethod.POST)
	public ModelAndView processingDetails(
			@RequestParam(name = "fieldEmail",required = true) String emailId,
			@RequestParam(name = "fieldUserName",required = true) String username,
			@RequestParam(name = "fieldPassword",required = true) String password,
			@RequestParam(name = "fieldSecurityCode",required = false) String securitycode			
		) {
		System.out.println("Email ID: "+emailId+", User Name: "+username+", Password: "+password+", Security Code: "+securitycode);
		ModelAndView mav= new ModelAndView();
		
		/**
		 * add attributes to the model
		 */
		mav.addObject("name",username);
		mav.setViewName("success");
		return mav;
	}
	
	@RequestMapping("/contact")
	public String returnContactPage() {
		return "form";
	}
}

Output:
Email ID: hpandya301@gmail.com, User Name: Harsh, Password: hpandya301, Security Code: 45
```

- Post when users clicks on Login button, success.jsp is called

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Login Successful for user ${name } with security code ${securitycode }</h1>

</body>
</html>
```

![alt text](Images/springmvc/image-9.png)

![alt text](Images/springmvc/image-10.png)



- The user fills out the form in **form.jsp** and submits it. The form data is sent to the server with the URL `processing-details`. The Spring DispatcherServlet intercepts the request and uses HandlerMapping to find the appropriate controller method that matches the URL `processing-details`.
- The corresponding method in the controller, annotated with `@RequestMapping(value = "/processing-details", method = RequestMethod.POST)`, is invoked. The method processes the form data, performs business logic, and possibly interacts with the service layer or database.
- When a form is submitted, the form data is sent as request parameters. `@RequestParam` can be used to bind these parameters to method arguments in the controller.
- After processing, the method returns a logical view name (e.g., "success"), which is resolved by the ViewResolver to an actual view file (e.g., success.jsp) to render the response.

>[!IMPORTANT]
> When using form, whatever the form action="/pathname" has the pathname, that gets populated when we click on button and not the jsp page name.
> In the very first example, jsp page name were present in the url.
> In above example when user clicks on contact form page (url http://localhost:8080/mvc/myform/contact), method processingDetails returns "success" so ideally the redirected url should be http://localhost:8080/mvc/myform/success, but in the form action attribute we have specified /processing-details, thus the url is redirected to http://localhost:8080/mvc/myform/processing-details

- Such similar working is seen when servlet works, it takes attributes from the HttpServletRequest like below example.

```
public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException { 
		
		int num1=0;
		int num2=0;
		num1=Integer.parseInt(req.getParameter("num1")); // Get parameter provides value in String so we parse it in Integer.
		num2=Integer.parseInt(req.getParameter("num2")); 
		
		/*
		 getWriter() calls PrintWriter class 
		 */
		PrintWriter out = res.getWriter() ; // Object is provided by Tomcat
		
		out.println("result is "+(num1+num2)); // This won't work when we call servlet from a servlet.
		
	}
```

- What if we have a class which binds the input fields into instance variables?
- Lets say we create a class `UserClass` which has instance field name as same as input field names.

```
package mvc.model;

public class UserClass {

	/**
	 * Instance variable names are same as form input field 
	 * name attributes
	 * 
	 * <input name="fieldEmail">
	 * <input name="fieldUserName">
	 * <input name="fieldPassword">
	 * <input name="fieldSecurityCode">
	 * 
	 */
	private String fieldEmail;
	private String fieldUserName;
	private String fieldPassword;
	private String fieldSecurityCode;
	
	
	public String getFieldEmail() {
		return fieldEmail;
	}
	public void setFieldEmail(String fieldEmail) {
		this.fieldEmail = fieldEmail;
	}
	public String getFieldUserName() {
		return fieldUserName;
	}
	public void setFieldUserName(String fieldUserName) {
		this.fieldUserName = fieldUserName;
	}
	public String getFieldPassword() {
		return fieldPassword;
	}
	public void setFieldPassword(String fieldPassword) {
		this.fieldPassword = fieldPassword;
	}
	public String getFieldSecurityCode() {
		return fieldSecurityCode;
	}
	public void setFieldSecurityCode(String fieldSecurityCode) {
		this.fieldSecurityCode = fieldSecurityCode;
	}
	
	
}
```

- So here we created a seperate JSP file with name form-class.jsp which has an action method `processing-details-via-class`. This jsp gets called when user enters `http://localhost:8080/mvc/myform/contact-class`.

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ar" dir="rtl">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-dpuaG1suU0eT09tx5plTaGMLBsfDLzUCCUXOY2j/LSvXYuG6Bqs43ALlhIqAJVRb" crossorigin="anonymous">

  </head>
  <body>

	<div class="container mt-5 align-items-center">

	<h3 class="text-center">Registration Form creating field as Class</h3>
	<!-- 
		Due to action parameter view will pass on the request to controller and using the action value processing-details
		controller will call the respected handler method.
		Making the transmission secure using on of the HTTPs method
	 -->	
	<form action="processing-details-via-class" method="post">
		<div class="form-group" align="left">
		  <label for="emailAddressInput" >Email address</label>
		  <input name="fieldEmail" type="email" id="emailAddressInput" class="form-control" aria-describedby="emailHelp" placeholder="name@example.com">
		 
		</div>
		<div class="form-group" align="left">
		  <label for="userNameInput" >User name</label>
		  <input name="fieldUserName" type="text" id="userNameInput" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="left">
		  <label for="userPassIput" >User Password</label>
		  <input name="fieldPassword" type="password" id="userPassIput" class="form-control" aria-describedby="emailHelp">
		</div>
		<!-- Optional field age -->
		<div class="form-group" align="left">
		  <label>Security Code</label>
		  <input name="fieldSecurityCode" type="text" id="code" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="center">
			
			<button type="submit" class="btn btn-primary mt-5">Login</button>
		</div> 
	  </form>
	</div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
  </body>
</html>
```

- Below is the controller details

```
package mvc.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.UserClass;

@Controller
@RequestMapping("/myform")
public class FormController {

	@RequestMapping("/contact-class")
	public String returnContactPageUsingClass() {
		return "form-class";
	}
	
	/**
	 * URL -> http://localhost:8080/mvc/myform/processing-details-via-class
	 * When user click on Login button, whatever url mentioned in the <form action> is called
	 * by the controller and it also checks if the Request Method is post which is defined in 
	 * form
	 * 
	 * Default Get method is used if not specified
	 * 
	 * Now here it some input fields of form could be optional, default all arguments are considered
	 * as mandatory.
	 */
	@RequestMapping(path = "/processing-details-via-class", method=RequestMethod.POST)
	public String processingDetailsForClass(
			@RequestParam(name = "fieldEmail",required = true) String emailId,
			@RequestParam(name = "fieldUserName",required = true) String username,
			@RequestParam(name = "fieldPassword",required = true) String password,
			@RequestParam(name = "fieldSecurityCode",required = false) String securitycode,
			Model md
		) {
		System.out.println("Email ID: "+emailId+", User Name: "+username+", Password: "+password+", Security Code: "+securitycode);
		UserClass uc= new UserClass();
		uc.setFieldEmail(emailId);
		uc.setFieldPassword(password);
		uc.setFieldSecurityCode(securitycode);
		uc.setFieldUserName(username);
		
		/**
		 * Setting Class as a attribute
		 */
		md.addAttribute("user",uc);
		return "success-class";
	}
}
```

- Post login on button, success-class.jsp is called, here if you see we have add an object to the model in a Spring MVC controller using model.`addAttribute("user", uc)`, you can access its properties directly in the JSP using the object name as the prefix.
- In the JSP, you use `${user.fieldUserName}` and `${user.fieldSecurityCode}` to access the name and id properties of the User object, respectively.

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Login Successful for user ${user.fieldUserName } with security code ${user.fieldSecurityCode }</h1>

</body>
</html>
```

- This works because Spring MVC automatically makes the model attributes available in the view under the key provided. The JSP uses the provided key (`user` in this case) to access the object's properties using standard JavaBean property conventions.

![alt text](Images/springmvc/image-11.png) 

![alt text](Images/springmvc/image-12.png) 

- What if the form fields have large number of input parameters? you need to write multiple times the `RequestParam` right? we can avoid this using another annotation `@ModelAttribute`.
- `@ModelAttribute` and `@RequestParam` are both annotations in Spring MVC used to bind HTTP request parameters to method arguments in controller methods, but they serve different purposes and are used in different contexts.
- Lets have a another form with name form-model-attribute.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ar" dir="rtl">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-dpuaG1suU0eT09tx5plTaGMLBsfDLzUCCUXOY2j/LSvXYuG6Bqs43ALlhIqAJVRb" crossorigin="anonymous">

  </head>
  <body>

	<div class="container mt-5 align-items-center">

	<h3 class="text-center">Registration Form using Model Attribute</h3>
	<!-- 
		Due to action parameter view will pass on the request to controller and using the action value processing-details
		controller will call the respected handler method.
		Making the transmission secure using on of the HTTPs method
	 -->	
	<form action="processing-details-via-modelattribute" method="post">
		<div class="form-group" align="left">
		  <label for="emailAddressInput" >Email address</label>
		  <input name="fieldEmail" type="email" id="emailAddressInput" class="form-control" aria-describedby="emailHelp" placeholder="name@example.com">
		 
		</div>
		<div class="form-group" align="left">
		  <label for="userNameInput" >User name</label>
		  <input name="fieldUserName" type="text" id="userNameInput" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="left">
		  <label for="userPassIput" >User Password</label>
		  <input name="fieldPassword" type="password" id="userPassIput" class="form-control" aria-describedby="emailHelp">
		</div>
		<!-- Optional field age -->
		<div class="form-group" align="left">
		  <label>Security Code</label>
		  <input name="fieldSecurityCode" type="text" id="code" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="center">
			
			<button type="submit" class="btn btn-primary mt-5">Login</button>
		</div> 
	  </form>
	</div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
  </body>
</html>
```

- We have a new class defined for model attribute

```
package mvc.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.UserClass;

@Controller
@RequestMapping("/myform")
public class FormController {
	
	@RequestMapping("/contact-model-attribute")
	public String returnContactPageUsingModelAttribute() {
		return "form-model-attribute";
	}	
	
	/**
	 * URL -> http://localhost:8080/mvc/myform/processing-details-via-modelattribute
	 */
	@RequestMapping(path = "/processing-details-via-modelattribute", method=RequestMethod.POST)
	public String processingDetailsViaModelAttribute(@ModelAttribute UserClass uc) {
		/**
		 * ModelAttribute binds the HTTP request data into instance variable of uc
		 * To access this attribute via JSP we need to make first character in lowercase "userClass"
		 * and access each properties of it like userClass.fieldfieldEmail
		 */
		System.out.println(uc.getFieldEmail());
		return "success-model-attribute";
	}
	
}

Output:
hpandya301@gmail.com
```

- Below is the success-model-attribute.jsp

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Login Successful for user ${userClass.fieldUserName } with security code ${userClass.fieldSecurityCode }</h1>

</body>
</html>
```
![alt text](Images/springmvc/image-14.png)

![alt text](Images/springmvc/image-13.png) 

- `@ModelAttribute` is used to bind multiple request parameters to a model object or method parameter. It's typically used when you have a form with multiple fields that correspond to properties of a Java object, and you want to bind all of them at once. 
- `@ModelAttribute` annotation in Spring MVC binds form data, query parameters, or session attributes to Java objects. This annotation is useful when working with forms that have many fields, as it eliminates the need to manually extract each form parameter.
- The binding of HTTP request parameters to the model object's properties is handled automatically by Spring, based on naming conventions and type conversion.
- For example, if the form has fields named **name** and **id**, and the **User** class has properties with the same names, Spring will automatically map the form data to the User object when the request is submitted. This mapping is facilitated by Spring's data binding infrastructure, which matches request parameter names to property names in the model object.
- Lets say whenever users clicks on form page, you wanna display some random welcome message like Hello , Hi etc.. how will you do that? because jsp can access Http attributes only when it has been set by a method in java right?, again `@ModelAttribute` will use.
- `@ModelAttribute` methods are invoked before controller methods annotated with `@RequestMapping` because the model object needs to be created before processing starts in the controller methods.

```
package mvc.controller;

import java.util.Random;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.UserClass;

@Controller
@RequestMapping("/myform")
public class FormController {

	/**
	 * Binding attributes to the model before controller
	 * getting invoked
	 */
	@ModelAttribute
	public void RandomMsgGenerator(Model model) {
	  String[] messages = {
	            "Hello, world!",
	            "How are you today?",
	            "Java is awesome!",
	            "Keep coding!",
	            "Have a great day!"};
	  Random random = new Random();
	  int index = random.nextInt(messages.length);
	  String randomMessage = messages[index];
	  System.out.println(randomMessage);
	  model.addAttribute("randomMsg",randomMessage);
	}
	
	@RequestMapping("/contact-model-attribute")
	public String returnContactPageUsingModelAttribute() {
		return "form-model-attribute";
	}	
	
	/**
	 * URL -> http://localhost:8080/mvc/myform/processing-details-via-modelattribute
	 */
	@RequestMapping(path = "/processing-details-via-modelattribute", method=RequestMethod.POST)
	public String processingDetailsViaModelAttribute(@ModelAttribute UserClass uc) {
		/**
		 * ModelAttribute binds the HTTP request data into instance variable of uc
		 * To access this attribute via JSP we need to make first character in lowercase "userClass"
		 * and access each properties of it like userClass.fieldfieldEmail
		 */
		System.out.println(uc.getFieldEmail());
		return "success-model-attribute";
	}
	
}

Output:

```
- contact-model-attribute and success-mode-attribute JSP files


```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ar" dir="rtl">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-dpuaG1suU0eT09tx5plTaGMLBsfDLzUCCUXOY2j/LSvXYuG6Bqs43ALlhIqAJVRb" crossorigin="anonymous">

  </head>
  <body>
	
	<h2 class="text-center">${randomMsg }</h2>
	<div class="container mt-5 align-items-center">

	<h3 class="text-center">Registration Form using Model Attribute</h3>
	<!-- 
		Due to action parameter view will pass on the request to controller and using the action value processing-details
		controller will call the respected handler method.
		Making the transmission secure using on of the HTTPs method
	 -->	
	<form action="processing-details-via-modelattribute" method="post">
		<div class="form-group" align="left">
		  <label for="emailAddressInput" >Email address</label>
		  <input name="fieldEmail" type="email" id="emailAddressInput" class="form-control" aria-describedby="emailHelp" placeholder="name@example.com">
		 
		</div>
		<div class="form-group" align="left">
		  <label for="userNameInput" >User name</label>
		  <input name="fieldUserName" type="text" id="userNameInput" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="left">
		  <label for="userPassIput" >User Password</label>
		  <input name="fieldPassword" type="password" id="userPassIput" class="form-control" aria-describedby="emailHelp">
		</div>
		<!-- Optional field age -->
		<div class="form-group" align="left">
		  <label>Security Code</label>
		  <input name="fieldSecurityCode" type="text" id="code" class="form-control" aria-describedby="emailHelp">
		</div>
		<div class="form-group" align="center">
			
			<button type="submit" class="btn btn-primary mt-5">Login</button>
		</div> 
	  </form>
	</div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
  </body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Login Successful for user ${userClass.fieldUserName } with security code ${userClass.fieldSecurityCode }</h1>
<h2>${randomMsg }</h2>
</body>
</html>
```

![alt text](Images/springmvc/image-15.png) 

![alt text](Images/springmvc/image-16.png)

- **When `@ModelAttribute` used on a method (not as a parameter), @ModelAttribute indicates that the method should add one or more attributes to the model. These attributes are available to all handler methods in the controller and can be used in the view, the attributes set in the model is available to all the class**.
- In the above example, the data is bind with model and it is accessible by any view. Here the data is randomly generated.

## Comparison of `@RequestParam` and `@ModelAttribute` in Spring MVC

| **Aspect**                  | **`@RequestParam`**                                                                                              | **`@ModelAttribute`**                                                                                               |
|-----------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| **Purpose**                 | `@RequestParam` binds individual request parameters to method arguments in a controller. It extracts data from query parameters, form fields, or URL parts. | `@ModelAttribute` binds all relevant request parameters to an object or adds attributes to the model. It is useful for form submissions where multiple fields map to a Java object or for setting up common data for views. |
| **Typical Use Case**        | Ideal for extracting specific query parameters or form fields, especially when you need fine-grained control over individual parameters. | Best for handling complex form submissions where the data needs to be bound to a Java object, or for setting up common attributes required across multiple requests. |
| **Usage Example**           | ```MethodName(@RequestParam("name") String name)```<br>This captures the `name` parameter from the request and binds it to the method argument `name`. | ```MethodName(@ModelAttribute User user)```<br>This binds all matching request parameters to the fields of the `User` object. |
| **Method-Level Usage**      | Not applicable. `@RequestParam` is used only on method parameters. | When used on methods (not parameters), `@ModelAttribute` methods are invoked before `@RequestMapping` methods to populate the model with common attributes needed by multiple views or controllers. |
| **Data Binding**            | Suitable for simple data types like strings, integers, or basic collections. Spring automatically converts the request parameter value to the specified type. | Suitable for binding complex objects such as JavaBeans or domain objects. Spring populates the object's fields based on matching request parameters. |
| **Annotation Position**     | Placed directly on method parameters to bind specific request parameters. | Can be placed on method parameters to bind form data or on methods to add attributes to the model. |
| **Default Value Handling**  | Allows specifying a default value if the request parameter is not present using the `defaultValue` attribute. For example:<br>`@RequestParam(value = "name", defaultValue = "Guest")` | Default values are typically handled within the object itself or in the method that initializes the object. Not specified directly through the annotation. |
| **Optional Parameters**     | Can mark parameters as optional by setting the `required` attribute to `false`. For example:<br>`@RequestParam(value = "age", required = false)` | Handles optional fields within the model object itself. If some fields are not present in the request, the corresponding object properties will remain unset or take default values of the class. |
| **Pre-processing**          | Not applicable. `@RequestParam` directly binds parameters when the method is invoked. | `@ModelAttribute` methods are called before `@RequestMapping` methods, allowing for data preparation or object initialization before the main request handling. |


- **`@RequestParam`:** Ideal for extracting and binding simple request parameters to controller method arguments. It provides straightforward control over individual parameters, with options for setting defaults and marking parameters as optional.

- **`@ModelAttribute`:** Designed for binding complex objects or adding common attributes to the model. It is particularly useful for form submissions where multiple fields need to be mapped to an object or when common data preparation is required before the main request handling.

- What if , we want to get redirected from one page to another? is it possible in spring mvc? yes, we have two ways to do it, **redirect** and **RedirectView**. Lets see an example of both

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 * If we add @RequestMapping on a class each endpoint url will have this handler url as prefix
 * So example the url without it we were accessing -> http://localhost:8080/mvc/welcome
 * Will change to http://localhost:8080/mvc/mypage/welcome
 */
@Controller
//@RequestMapping("/mypage")
public class MainController {

	/**
	 * redirect:/ approach
	 * Entered URL -> http://localhost:8080/mvc/redirectform
	 * Redirect to the URL -> http://localhost:8080/mvc/customer/create
	 */
	@RequestMapping("/redirectform")
	public String redirectingPageusingRedirect() {
		System.out.println("Redirected to the customer form page using redirect approach");
		return "redirect:/customer/create";
	}
	
	
	/**
	 * redirect:/ approach
	 * Entered URL -> http://localhost:8080/mvc/redirectviewform
	 * Redirect to the URL -> http://localhost:8080/mvc/customer/create
	 */
	@RequestMapping("/redirectviewform")
	public RedirectView redirectingPageusingRedirectView() {
		System.out.println("Redirected to the customer form page using redirectview approach");
		RedirectView rv= new RedirectView();
		rv.setUrl("customer/create");
		return rv;
	}
	
	/**
	 * Redirect to codechef platform
	 */
	@RequestMapping("/codechef")
	public RedirectView redirectingtoCodechef() {
		System.out.println("Redirected to codechef platform");
		RedirectView rv= new RedirectView();
		rv.setUrl("https://www.codechef.com");
		return rv;
	} 
}


Output:
Redirected to the customer form page using redirect approach
Redirected to the customer form page using redirectview approach
Redirected to codechef platform
```

- **/redirectform** redirected to **/customer/create**

![alt text](Images/springmvc/image-20.png) 

![alt text](Images/springmvc/image-21.png) 

- **/redirectviewform** redirected to **/customer/create** 

![alt text](Images/springmvc/image-22.png) 

![alt text](Images/springmvc/image-21.png)  

- **/codechef** redicted to codechef platform.

![alt text](Images/springmvc/image-23.png) 

![alt text](Images/springmvc/image-24.png) 

- The `redirect:/` prefix is simpler and more convenient for straightforward use cases where you only need to redirect to another URL.
- `RedirectView` provides more control and flexibility, allowing for detailed configuration of the redirect process, such as setting custom HTTP status codes or handling model attributes in specific ways.

- Lets say you developing an API which takes userid from URI example `https://organization/employee/userid/12345` . Suppose its is an APU url and user id is 12345, so using spring can you access this numerical id?, yes using `@PathVariable` annotation.
- When we hit `http://localhost:8080/mvc/userid/34/harsh`, we get the output.

```
package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 * If we add @RequestMapping on a class each endpoint url will have this handler url as prefix
 * So example the url without it we were accessing -> http://localhost:8080/mvc/welcome
 * Will change to http://localhost:8080/mvc/mypage/welcome
 */
@Controller
//@RequestMapping("/mypage")
public class MainController {

	/**
	 * Here the id specified in the URL will get bind to the userid 
	 * argument of the method.
	 */
	@RequestMapping("/userid/{id}/{name}")
	public String fetchUserId(@PathVariable("id") int userid,@PathVariable("name") String username) {
		System.out.println("User ID: "+userid+", User Name: "+username);
		return "welcome";
	}
}


Output:
User ID: 34, User Name: harsh
```

![alt text](Images/springmvc/image-17.png) 

- The `@PathVariable` annotation in Spring MVC is used to extract values from the URI path in a request and bind them to method parameters in a controller. It allows you to handle dynamic parts of the URI, such as IDs or other path segments, in a type-safe manner.
- This approach is commonly used in RESTful APIs where resources are identified by unique IDs, such as users, products, orders, etc.
- Uptil now we have seen commonly used annotation in Spring MVC, but when the annotation binding fails does spring provides any exception handler? usually we write multiple try-catch blocks which can provide same exception for different methods, so can spring handle exception for all sort of methods? lets see `@ExceptionHandler` annotation.
- Below is the MainController

```
package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Front Controller, this controller acts as a front controller in the MVC pattern
 * If we add @RequestMapping on a class each endpoint url will have this handler url as prefix
 * So example the url without it we were accessing -> http://localhost:8080/mvc/welcome
 * Will change to http://localhost:8080/mvc/mypage/welcome
 */
@Controller
//@RequestMapping("/mypage")
public class MainController {


	@RequestMapping("/string")
	public String stringEvent() {
		String st=null;
		st.equalsIgnoreCase(""); // NullPointerException
		return "welcome";
	}
	
	/**
	 * Defining a exception handler
	 * for NullPointerException and IOException
	 */
	@ExceptionHandler({NullPointerException.class,IOException.class})
	public ModelAndView exceptionHandlerMethod() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("errorhandler","NullPointerException");
		mav.setViewName("error");
		return mav;
	}
	
	@RequestMapping("/number")
	public String integerEvent() {
		String st="abc";
		Integer.parseInt(st); //NumberFormatException
		return "welcome";
	}
	/**
	 * Defining NumberFormatException handler
	 */
	@ExceptionHandler(value=NumberFormatException.class)
	public ModelAndView exceptionHandlerMethod1() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("errorhandler","NumberFormatException");
		mav.setViewName("error");
		return mav;
	}
}
```

![alt text](Images/springmvc/image-19.png)

![alt text](Images/springmvc/image-18.png)


- The `@ExceptionHandler` annotation is used to define a method that will handle specific exceptions thrown by controller methods. This allows you to encapsulate the exception handling logic in a dedicated method, improving code organization and readability.
- When an exception is thrown in a controller method, Spring searches for an `@ExceptionHandler` method within the same controller that matches the exception type. If found, the `@ExceptionHandler` method is invoked, allowing you to handle the exception and return an appropriate response.
- In the above code, the scope of exception handler was defined for a single controller, can we define a single exception handler across all controller? , yes using `@ControllerAdvice`.
- So here we have AllExceptionHandler class.

```
package mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Generic Exception Handler for all controllers
 */
@ControllerAdvice
public class AllExceptionHandler {

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=ArithmeticException.class)
	public ModelAndView ArithmeticExceptionHandler() {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("error");
		mav.addObject("errorhandler","ArithmeticException");
		return mav;
	}
}
```

- MainController class

```
	@RequestMapping("/maths")
	public String arithmeticException() {
		int a=1/0;
		return "welcome";
	}
```

![alt text](Images/springmvc/image-25.png)

- `@ControllerAdvice` is a specialization of the `@Component` annotation, which allows you to handle exceptions across the whole application, not just within a single controller. It can be used to define global exception handling methods, data binding methods, and model attribute methods.
- `@ControllerAdvice` classes can contain `@ExceptionHandler` methods that will be applied across all controllers in the application.
Spring will search for a matching `@ExceptionHandler` method in the `@ControllerAdvice` class if no match is found within the controller itself.
- Spring uses the following mechanism to determine which `@ExceptionHandler` method to call:
	- Controller Level: First, Spring checks if there is a matching `@ExceptionHandler` method within the controller that threw the exception.
	- ControllerAdvice Level: If no matching handler is found at the controller level, Spring looks for a suitable `@ExceptionHandler` method in `@ControllerAdvice` classes.
	- Default Exception Handling: If no specific handler is found, Spring's default exception handling mechanism will take over, typically resulting in a generic error message.
	- Inheritance Hierarchy: If multiple handlers could match, Spring chooses the one with the closest match in the inheritance hierarchy.

- What if you wanted some filtertion or validation or log the request before it reaches out the controller? you require some intercepting layer before your request reaches out controller? we can achieve it using **Spring MVC Interceptor**.



![alt text](Images/springmvc/SpringInterceptor.jpg)


- In Spring MVC, an interceptor is a component that allows you to intercept HTTP requests and responses, much like a filter in the Servlet API. Interceptors provide a way to apply certain logic or operations before the request reaches the controller or after the response leaves the controller, without modifying the controller itself.

#### Why Interceptors Are Required
- Cross-Cutting Concerns: Interceptors are often used to handle cross-cutting concerns that are common across multiple controllers or endpoints. These concerns can include logging, security checks, performance monitoring, and more.

- Pre-Processing and Post-Processing: They provide hooks to execute code before (pre-processing) and after (post-processing) the main processing logic of a request.

- Centralized Logic: By using interceptors, you can centralize common logic in one place, making it easier to manage and modify without touching the business logic in the controllers.

- In Spring MVC, Handler Interceptor ( interface ) and HandlerInterceptorAdapter ( abstract class ) are key components used to intercept HTTP requests and responses, allowing for pre-processing and post-processing logic. 

- Lets us see the code, so we have created a new MyHandlerClassInterceptorAdapter which extends HandlerInterceptorAdapter

```
package mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyHandlerClassInterceptorAdapter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Inside Prehandle of MyHandlerClassInterceptorAdapter");
		return true;
	}
}
```

- Now which request should be intercept by the handler? there could be multiple URI for our applications right? so we need to define a configuration were we will mentioned that whenever the request is from `/welcome` (example) , handler should intercept it.

```
<!--  Interceptor -->
<mvc:interceptors>
	<mvc:interceptor>
		<mvc:mapping path="/welcome"/>
		<bean class= "mvc.interceptor.MyHandlerClassInterceptorAdapter"/>
	</mvc:interceptor>
	<!-- Defining multiple interceptor for different URI
		<mvc:interceptor>
		<mvc:mapping path="/welcome"/>
		<bean class= "mvc.interceptor.MyHandlerClassInterceptorAdapter"/>
	</mvc:interceptor>
	 -->
</mvc:interceptors>
```

- When we hit the url `http://localhost:8080/mvc/welcome`.

```
Output:
Inside Prehandle of MyHandlerClassInterceptorAdapter
Returns page name welcome, thus /WEB-INF/pages/welcome.jsp
```

- Below are other methods.

![alt text](Images/springmvc/image-26.png)

- Lets try Handler Interceptor , so we have defined another class MyHandlerInterceptor

```
package mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Inside PreHandle of MyHandlerInterceptor");
		return true;
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Inside Posthandle of MyHandlerInterceptor");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("Inside afterCompletion of MyHandlerInterceptor");
	}
}
```

- Below is the configuration done for the same `/welcome` URI.

```
<!--  Interceptor -->
<mvc:interceptors>
	<mvc:interceptor>
		<mvc:mapping path="/welcome"/>
		<bean class= "mvc.interceptor.MyHandlerClassInterceptorAdapter"/>
	</mvc:interceptor>
	<mvc:interceptor>
		<mvc:mapping path="/welcome"/>
		<bean class= "mvc.interceptor.MyHandlerInterceptor"/>
	</mvc:interceptor>
	<!-- Defining multiple interceptor for different URI
		<mvc:interceptor>
		<mvc:mapping path="/welcome"/>
		<bean class= "mvc.interceptor.MyHandlerClassInterceptorAdapter"/>
	</mvc:interceptor>
	 -->
</mvc:interceptors>
```

```
Output:
Inside Prehandle of MyHandlerClassInterceptorAdapter
Inside PreHandle of MyHandlerInterceptor
Returns page name welcome, thus /WEB-INF/pages/welcome.jsp
Inside Posthandle of MyHandlerInterceptor
Inside afterCompletion of MyHandlerInterceptor
```

- **HandlerInterceptor**: Use this interface if you need to implement all or most of the methods, or if you want to implement a specific method that is not covered by HandlerInterceptorAdapter.
- **HandlerInterceptorAdapter**: Use this class if you only need to override a few methods, as it provides a convenient way to avoid implementing unnecessary methods.

# Spring MVC and ORM

- Lets create a actual form which takes input from page and saves those input details into database. Suppose we have IT portal form for a Students who will be registering to get placed in **MAANG** companies. Here we will use hibernate as ORM tool.
- First lets download the dependencies using maven

```
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-orm -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.2.4.RELEASE</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.10.Final</version> <!-- or any compatible version -->
</dependency>

<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.1</version>
</dependency>
    
```

- Perform some configurations, here will use annotations configuration method.

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context 
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/tx 
           http://www.springframework.org/schema/tx/spring-tx.xsd       
           http://www.springframework.org/schema/mvc
           http://www.springframework.org/schema/mvc/spring-mvc.xsd"
           >
<!-- Configure static resource handling -->
	<mvc:annotation-driven />
	<mvc:resources mapping="/images/**" location="/images/" />
    <!--  Enabling Component Scanning Annotations for all packages -->
	<context:component-scan base-package="mvc"/> 
	<tx:annotation-driven />
	<!-- DataSource bean: Providing resources (database connection details) -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/springhibernate" />
        <property name="username" value="root" />
        <property name="password" value="Meetpandya40@" />
    </bean>

    <!-- Hibernate properties: Configuration settings -->
    <bean id="hibernateProperties" class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <property name="properties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.format_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">create</prop>
            </props>
        </property>
    </bean>

    <!-- SessionFactory bean: Coordinating the overall database interaction -->
    <bean id="factory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="hibernateProperties" ref="hibernateProperties" />
        <!-- Scanning for annotated entity classes -->
	<property name="annotatedClasses">
			<list>
			<value>mvc.model.entities.Student</value>
			</list>
			</property>    
	</bean>

    <!-- Transaction manager: Ensures transaction management -->
    <bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
        <property name="sessionFactory" ref="factory" />
    </bean>

<!-- Defining view resolver and passing properties of it, which it requires for resolving -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" name="viewResolver">
	<!-- Prefix tells spring where all the pages are present -->
	<property name="prefix" value="/WEB-INF/pages/"></property>
	<!-- Suffix tells what extension of files are to be considered (here all .jsp files) -->
	<property name="suffix" value=".jsp"></property>
	
	<!-- Basically Controllers gives view name, FrontController passes this view to ViewResolver
		So example if Controller gives hello, then the ViewResolver returns the page /WEB-INF/pages/hello.jsp
	 -->
</bean>
</beans>
```
- `<mvc:resources mapping="/images/**" location="/images/" />`: This element configures the handling of static resources, such as images, CSS, JavaScript, and other static files, in your Spring MVC application.
- **mapping**: Specifies the URL pattern that static resources will be served from. In this case, all requests that start with /images/ will be mapped to static resources.
- **location**: Specifies the path where the static resources are located. The path is relative to the root of the web application. For example, `location="/images/"` means that the server will look for static resources in the images directory inside your web application's root like below.
`
```
your-webapp/
├── images/
│   └── background.jpg
├── WEB-INF/
│   ├── web.xml
│   ├── spring-mvc-config.xml
│   └── views/
│       └── your-jsp-file.jsp
```
- By including `<mvc:annotation-driven />` in your configuration, you enable the use of these annotations without needing to define the corresponding handler beans manually. It also integrates additional features like data binding, formatting, and validation.
- Below is the Student & Address object class.

```
Student Class

package mvc.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "student_form_details")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="unique_id")
	private int uniqueId;
	
	@Column(name="student_name")
	private String stdname;

	@Column(name="college_roll_number")
	private long stdrollnum;
	
	@DateTimeFormat(pattern="dd-MMMM-yyyy")
	@Column(name="date_of_birth")
	private Date stddatedob;
	

    @ElementCollection
    @CollectionTable(name="student_subjects_data", joinColumns = @JoinColumn(name="student_subjects_id"))
    @Column(name="foreign_key_student_subjects_id")
    private List<String> stdsubjects;
	
	@Column(name="gender")
	private String gender;

	@Column(name="type")
	private String stdtype;

	@Column(name="foreign_key_address_id")
	@Embedded
	private Address studentAddress;

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
		this.stdname = stdname;
	}

	public long getStdrollnum() {
		return stdrollnum;
	}

	public void setStdrollnum(long stdrollnum) {
		this.stdrollnum = stdrollnum;
	}

	public Date getStddatedob() {
		return stddatedob;
	}

	public void setStddatedob(Date stddatedob) {
		this.stddatedob = stddatedob;
	}

	public List<String> getStdsubjects() {
		return stdsubjects;
	}

	public void setStdsubjects(List<String> stdsubjects) {
		this.stdsubjects = stdsubjects;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStdtype() {
		return stdtype;
	}

	public void setStdtype(String stdtype) {
		this.stdtype = stdtype;
	}

	public Address getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	
	
}



Address Class

package mvc.model.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String addressline1;
	
	private String addressline2;

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	
	
}
```

- `@ElementCollection`: This annotation is used to define a collection of basic types (like String, Integer, etc.) or embeddable types in JPA. It indicates that the collection of elements is not a separate entity but a part of the entity to which it belongs.
- In this case, stdsubjects is a List<String>, and the `@ElementCollection` annotation specifies that this list should be stored in a separate table rather than in the main entity table.
- `@CollectionTable`: This annotation specifies the details of the table that will store the collection elements. It includes the table name and the join columns used to map the collection elements to the owning entity.
- `name="student_subjects_data"`: This defines the name of the table that will store the collection elements (List<String> items in stdsubjects).
- `joinColumns = @JoinColumn(name="student_subjects_id")`: This specifies the column in the `student_subjects_data` table that will be used to join the table to the owning entity's table. The `name="student_subjects_id"` indicates the name of the foreign key column in the `student_subjects_data` table that links to the primary key of the owning entity's table.
- For example, if a Student entity has an ID of 1 and subjects ["Math", "Science"], the `student_subjects_data` table might look like this:



| student_subjects_id | foreign_key_student_subjects_id |
| ------------------- | ------------------------------- |
| 1                   | Math                            |
| 1                   | Science                         |


- Below is the Dao interface and its implementation

```
Interface:

package mvc.dao;

import mvc.model.entities.Student;

public interface StudentDao {

	public int insert(Student student);
}


Implementation:
package mvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mvc.model.entities.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	
	/*
	 * Injecting Dependencies
	 */
	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public int insert(Student student) {
		int rowsAffected=(int) factory.getCurrentSession().save(student);
		System.out.println("Rows affected -"+rowsAffected);
		return rowsAffected;
	}

	
}
```

- Below service layer

```
Interface:
package mvc.service;

import mvc.model.entities.Student;

public interface StudentService {

	public int insert(Student student);
}


Implementation:
package mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.dao.StudentDao;
import mvc.model.entities.Student;


/**
 * Database changes are either committed (saved permanently) or rolled back (canceled) as a single unit via Transactional
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	/**
	 * Injecting Dependencies
	 */
	@Autowired
	private StudentDao studentDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public int insert(Student student) {
		return studentDao.insert(student);
	}

	
}
```

- Hold on? what is this `@Repository` annotation, it should be `@Component` right?
- Both `@Service` and `@Repository` are specializations of the `@Component` annotation, meaning they are essentially `@Component` with additional semantics.
- `@Service` this annotation marks a class as a service provider. It's typically used for classes that contain business logic. In essence, it indicates that the class is part of the service layer in your application.   
- `@Repository` this annotation marks a class as a data access object (DAO). It's used for classes that interact with the database, such as performing CRUD operations. It's part of the persistence layer.

###### Why to use Service and Repository inplace of Component
- Clearly defines the role of a class within the application architecture. Improves code readability and understanding.
- One of the key benefits of `@Repository` is that it provides a mechanism for exception translation. Spring automatically converts database-related exceptions into Spring's DataAccessException hierarchy. This makes the data access layer less coupled to the underlying persistence technology.

- Below are the JSP files

```
student-form.jsp

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <title>IT Application Form</title>
    <style>
        body {
	        background-image: url('<c:url value="/images/background.jpg" />');
	        background-repeat: no-repeat;
	        background-size: cover;
	        background-position: center;
	        background-attachment: fixed;
        }
        .card {
            border-radius: 15px;
            border: none;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            background: #cccccc;
        }
        .card-body {
            padding: 2rem;
        }
        .form-control {
            border-radius: 10px;
            border: 1px solid #ced4da;
        }
        .btn-primary {
            border-radius: 10px;
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .form-group label {
            font-weight: bold;
            color: #333;
        }
        .form-check-label {
            margin-left: 0.5rem;
            color: #333;
        }
        .form-group input[type="text"], .form-group input[type="date"], .form-group select {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 40px;
            margin-bottom: 40px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-4">IT Application Form for Students,path variable, adding images, error handling</h3>
                    <form action="register" method="post">
                        <div class="form-group">
                            <label for="stdname">Your Name</label>
                            <input name="stdname" type="text" class="form-control" id="stdname" placeholder="Enter Name">
                        </div>

                        <div class="form-group">
                            <label for="stdrollnum">Your College Roll Number</label>
                            <input name="stdrollnum" type="text" class="form-control" id="stdrollnum" placeholder="Enter Roll Number">
                        </div>

                        <div class="form-group">
                            <label for="dob">Your DOB</label>
                            <input name="stddatedob" type="text" class="form-control" id="dob" placeholder="dd-MM-yyyy">
                        </div>

                        <div class="form-group">
                            <label for="stdsubjects">Select Courses</label>
                            <select name="stdsubjects" class="form-control" id="stdsubjects" multiple>
                                <option>Java</option>
                                <option>Python</option>
                                <option>C++</option>
                                <option>Spring</option>
                                <option>Hibernate</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <span class="mr-3">Select Gender</span>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="male">
                                <label class="form-check-label" for="inlineRadio1">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="female">
                                <label class="form-check-label" for="inlineRadio2">Female</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="stdtype">Select Type</label>
                            <select class="form-control" name="stdtype" id="stdtype">
                                <option value="oldstudent">Old Student</option>
                                <option value="normalstudent">Normal Student</option>
                            </select>
                        </div>

                        <div class="card mt-4">
                            <div class="card-body">
                                <p>Your Address</p>
                                <div class="form-group">
                                    <input name="studentAddress.addressline1" type="text" class="form-control" id="addressline1" placeholder="Address Line 1">
                                </div>
                                <div class="form-group">
                                    <input name="studentAddress.addressline2" type="text" class="form-control" id="addressline2" placeholder="Address Line 2">
                                </div>
                            </div>
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous"></script>

<script>
    $(function() {
        $("#dob").datepicker({
            dateFormat: "dd-MM-yy",
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0"
        });
    });
</script>
</body>
</html>


registered-success.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Login Successful for user ${student.stdname } , your address is ${student.studentAddress.addressline1 }</h1>
</body>
</html>
```

- Below is the StudentController class

```
package mvc.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.entities.Student;
import mvc.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService customerService;
	
	public StudentService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(StudentService customerService) {
		this.customerService = customerService;
	}

	@ModelAttribute
	public void welcomeMsg(Model model) {
		  String[] messages = {
		            "Hello, welcome to the IT portal",
		            "Greetings, welcome to the IT portal",
		            "Hi, welcome to the IT portal"};
		  Random random = new Random();
		  int index = random.nextInt(messages.length);
		  String randomMessage = messages[index];
		  model.addAttribute("greetingMsg",randomMessage);
	}

	@RequestMapping(path = "/register",method = RequestMethod.POST)
	public ModelAndView register(
		@ModelAttribute Student student 
			) {
		System.out.println("Student Bind details: "+student);
		ModelAndView mav=new ModelAndView();
		int result=customerService.insert(student);
		if(result!=1) {
			mav.addObject("registrationMsg","unsuccessful");
		}
		else {
			mav.addObject("registrationMsg","successful");
		}
		mav.setViewName("registered-success");
		return mav;
	}
	
	@RequestMapping("/create")
	public String create() {
		return "student-form";
	}
}

Output:
Hibernate: 
    
    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB
Hibernate: 
    
    insert into hibernate_sequence values ( 1 )
Hibernate: 
    
    create table student_form_details (
       unique_id integer not null,
        gender varchar(255),
        date_of_birth datetime(6),
        student_name varchar(255),
        college_roll_number bigint,
        type varchar(255),
        addressline1 varchar(255),
        addressline2 varchar(255),
        primary key (unique_id)
    ) engine=InnoDB
Hibernate: 
    
    create table student_subjects_data (
       student_subjects_id integer not null,
        foreign_key_student_subjects_id varchar(255)
    ) engine=InnoDB
Hibernate: 
    
    alter table student_subjects_data 
       add constraint FK938ff9j5qoc7knp703nn00d20 
       foreign key (student_subjects_id) 
       references student_form_details (unique_id)
Student Bind details: mvc.model.entities.Student@1a50b37f
Hibernate: 
    select
        next_val as id_val 
    from
        hibernate_sequence for update
            
Hibernate: 
    update
        hibernate_sequence 
    set
        next_val= ? 
    where
        next_val=?
Rows affected -1
Hibernate: 
    insert 
    into
        student_form_details
        (gender, date_of_birth, student_name, college_roll_number, type, addressline1, addressline2, unique_id) 
    values
        (?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        student_subjects_data
        (student_subjects_id, foreign_key_student_subjects_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        student_subjects_data
        (student_subjects_id, foreign_key_student_subjects_id) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        student_subjects_data
        (student_subjects_id, foreign_key_student_subjects_id) 
    values
        (?, ?)
```

![alt text](Images/springmvc/image-27.png)


![alt text](Images/springmvc/image-28.png)


- DB Output

![alt text](Images/springmvc/image-29.png)

![alt text](Images/springmvc/image-30.png) 

- Checkout how to configure Spring MVC using [Java Configuration](https://github.com/codophilic/LearnSpring/blob/main/Spring%20Security.md#about-spring-security)
- Above learnings are implement [here](https://github.com/codophilic/LearnSpring/tree/main/Spring%20MVC/mvc/src/main/java/mvc).
- Learn about [Spring AOP](https://github.com/codophilic/LearnSpring/blob/main/Spring%20AOP.md)
