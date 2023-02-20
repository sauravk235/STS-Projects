package com.form.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.form.entity.Eemployee;
import com.form.entity.Employee;
import com.form.repository.Erepository;

@Controller
@RequestMapping("/Employee")
public class Econtroller {
	
	@Autowired
	private Erepository erepo;

	@GetMapping("/eRegister")
	public String formPostData(Model model) {
		Eemployee emp=new Eemployee();
		model.addAttribute("employee", emp);
		return "eRegister.html";
	}
	
	@PostMapping("/register")
	public String registerEmpData(Eemployee emp1, Model model) {
		erepo.save(emp1);
		return "success";
	}
	
	@PostMapping("/save")
	public String saveEmpData(Eemployee emp1, Model model) {
		erepo.save(emp1);
		List<Eemployee> list=new ArrayList<Eemployee>();	// List is Interface
		list = erepo.findAll();
		model.addAttribute("std2", list);
		return "efetch.html";
	}
	
	@GetMapping("/listOfEmployee")
	public String listEmployee(Model model) {
		List<Eemployee> list=new ArrayList<Eemployee>();	// List is Interface
		list=erepo.findAll();
		model.addAttribute("std2", list);
		return "efetch.html";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String editEmployee(@PathVariable(value = "id") long id, Model model) {
			Eemployee employee = erepo.getById(id);
	        model.addAttribute("employee", employee);
	        return "eupdate.html";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable ("id") long id, Model model) {
		erepo.deleteById(id);
		List<Eemployee> list=new ArrayList<Eemployee>();	// List is Interface
		list = erepo.findAll();
		model.addAttribute("std2", list);
		return "efetch";
	}
}
 