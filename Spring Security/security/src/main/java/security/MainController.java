package security;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	/**
	 * Same as @RequestMapping(method = RequestMethod.GET)
	 */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
    	System.out.println("Inside Login Method");
        if (error != null) {
        	System.out.println("Invalid username or password.");
            model.addAttribute("error", "Invalid username or password.");
        }
        if (logout != null) {
        	System.out.println("Logged out successfully.");
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }
	
	/**
	 * Same as @RequestMapping(method = RequestMethod.GET)
	 */
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
