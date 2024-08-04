package security.config;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@RequestMapping("/welcome")
	public String welcomePage() {
		return "welcome"; 
	}
}
