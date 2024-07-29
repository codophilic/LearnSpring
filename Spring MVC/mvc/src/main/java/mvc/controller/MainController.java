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
//@RequestMapping("/mypage")
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
