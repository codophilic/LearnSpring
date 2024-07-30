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
	
	@RequestMapping("/contact")
	public String returnContactPage() {
		return "form";
	}
	
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
		mav.addObject("securitycode",securitycode);
		mav.setViewName("success");
		return mav;
	}

	
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
