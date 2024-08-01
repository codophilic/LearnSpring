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
