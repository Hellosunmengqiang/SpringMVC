package com.springmvcdemo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice(basePackages = "com.dao")
public class ExController {
	
	 @ExceptionHandler({ArithmeticException.class})
	    public ModelAndView handlerEx(Exception ex) {
	        ModelAndView mv=new ModelAndView();
	        mv.addObject("ex",ex.getMessage());
	        mv.setViewName("error");
	    	return mv;
	    }
	 
	 @ExceptionHandler({RuntimeException.class})
	    public ModelAndView RuntimeEx(Exception ex) {
	        ModelAndView mv=new ModelAndView();
	        mv.addObject("ex",ex.getMessage());
	        mv.setViewName("error");
	    	return mv;
	    }



}
