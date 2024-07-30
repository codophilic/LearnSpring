package mvc.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mvc.model.entities.Customer;
import mvc.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ModelAttribute
	public void welcomeMsg(Model model) {
		  String[] messages = {
		            "Hello, welcome to the portal",
		            "Greetings, welcome to the portal",
		            "Hi, welcome to the portal"};
		  Random random = new Random();
		  int index = random.nextInt(messages.length);
		  String randomMessage = messages[index];
		  model.addAttribute("greetingMsg",randomMessage);
	}

	@RequestMapping(path = "/register",method = RequestMethod.POST)
	public ModelAndView register(
		@ModelAttribute Customer customer 
			) {
		ModelAndView mav=new ModelAndView();
		int result=customerService.insert(customer);
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
		return "customer-form";
	}
}
