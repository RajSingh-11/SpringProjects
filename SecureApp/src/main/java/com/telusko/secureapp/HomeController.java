package com.telusko.secureapp;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {

		return "home.jsp";
	}

	@RequestMapping("/login")
	public String loginPage() {

		return "login.jsp";
	}

	@RequestMapping("/logout-success")
	public String logoutPage() {

		return "logout.jsp";
	}

	/** This is the Principal from java security, which means the current user. */
	/** @ResponseBody is used for both json/xml , this will accept both */
	/**
	 * http://localhost:8080/user
	 * 
	 * If we hit this request this give us the details of current user and we can
	 * save it in our database or in session , according to our use, we can use the
	 * user's data.
	 */
	@RequestMapping("user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
}
