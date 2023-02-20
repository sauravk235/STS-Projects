package com.form.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.entity.EnrollStudent;
import com.form.entity.User;
import com.form.repository.FormUserRepositry;
import com.form.repository.User2Repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// DATABASE Issue => [ commit mode- none, table create mode-create ]

@Controller
public class Controller1 {

	@Autowired
	private FormUserRepositry formrepo;

	@Autowired
	private User2Repository userrepo;

	@RequestMapping("/home")
	public String homePage() {
		return "index.html";
	}

	@RequestMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		return "logout.html";
	}

	@RequestMapping("/registeruser")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "signup_form.html";
	}

	@RequestMapping("/process_register")
	public String registerUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		formrepo.save(user);
		return "success.html";
	}

	@RequestMapping("/basicFormRegistration")
	public String basicForm(Model model) {
        EnrollStudent std=new EnrollStudent(); 
		model.addAttribute("student", std);		// object created for basicFormRegistration
		return "basicFormRegistration.html";
	}

	// Post data from UI form to DB
	// t-h:action="@{/register}"
	@RequestMapping("/register")
	public String add(EnrollStudent std, Model model) {		// "student" obj pass the ref to "std" object.
		System.out.println(std);
		EnrollStudent obj = userrepo.save(std);				// saved to DB
		if (obj != null) {
			model.addAttribute("std1", obj);				// to view set values on html page
		}
		return "registerSuccefully.html";
	}

	// fetch data from DB and display to UI(html Page)
	// Model - it is created to do UI to DB communication
	@RequestMapping("/fetch")				
	public String fetchData(Model model) {
		List<EnrollStudent> list = userrepo.findAll();	// find all data from DB and stored in list.
		model.addAttribute("EnrollStudent", list);		// EnrollStudent is a local obj to set model.
		return "fetch.html";
	}

}
