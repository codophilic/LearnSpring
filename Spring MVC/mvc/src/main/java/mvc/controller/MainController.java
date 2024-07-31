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
	
	/**
	 * Here the id specified in the URL will get bind to the userid 
	 * argument of the method.
	 */
	@RequestMapping("/userid/{id}/{name}")
	public String fetchUserId(@PathVariable("id") int userid,@PathVariable("name") String username) {
		System.out.println("User ID: "+userid+", User Name: "+username);
		return "welcome";
	}
	

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
	
	@RequestMapping("/maths")
	public String arithmeticException() {
		int a=1/0;
		return "welcome";
	}
}
