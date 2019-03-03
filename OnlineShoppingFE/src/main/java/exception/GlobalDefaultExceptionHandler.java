package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	// to handle 404 error
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle" , "Page Not Available");
		mv.addObject("errorDescription" , "The page you are looking for is not available!!");
		mv.addObject("title" , "404 - Page not found");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle" , "Product Not Available");
		mv.addObject("errorDescription" , "The product you are looking for is not available!!");
		mv.addObject("title" , "500 - Product not found");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex)
	{
		ModelAndView mv = new ModelAndView("error");
		
		/* 
		 * 
		 * for debugging purpose
		 */ 
		  StringWriter sw = new StringWriter();
		  PrintWriter pw = new PrintWriter(sw);
		  
		  ex.printStackTrace(pw);
		  mv.addObject("errorDescription",sw);
		 
		  
		 

		mv.addObject("errorTitle" , "Error");
		//mv.addObject("errorDescription" , "The page you are looking for is not available...Please check your URL for mistakes and try again");
		mv.addObject("title" , "Error");

		/*
		 * 
		 * we can also print the exception in this method; can be used in real time project
		 * 
		 * mv.addObject("errorDescription",ex.toString());
		 * 
		 * */

		 //mv.addObject("errorDescription",ex.toString());

		
		return mv;
	}
	
}
