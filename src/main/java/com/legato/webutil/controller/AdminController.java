package com.legato.webutil.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, 
			HttpServletResponse response, Principal principal){
		return new ModelAndView("home").addObject("username", principal != null ? principal.getName() : "");
	}
}