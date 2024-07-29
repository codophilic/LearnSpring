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
		mav.addObject("securitycode",securitycode);
		mav.setViewName("success");
		return mav;
	}
	
	@RequestMapping("/contact")
	public String returnContactPage() {
		return "form";
	}
}
