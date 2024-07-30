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
