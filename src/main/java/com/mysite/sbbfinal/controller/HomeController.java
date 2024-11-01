package com.mysite.sbbfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
	
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }
}
