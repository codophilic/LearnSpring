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
