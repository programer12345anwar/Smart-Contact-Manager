package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")//to access this controller
public class UserController {
	
	 @RequestMapping("/index")
	public String dashboard() {
		
		System.out.println("user dashboard is opened");
		return "user_dashboard";//user_dashboard.html 
	}
	  

}
