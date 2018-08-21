package com.legato.webutil.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {
	private static final Logger LOGGER = Logger.getLogger(AuthController.class);
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(HttpServletRequest request, HttpServletResponse response, Principal principal){
		LOGGER.info((principal != null ? principal.getName() : principal) + " logged in.");
		return "redirect:/admin/home";
	}
}