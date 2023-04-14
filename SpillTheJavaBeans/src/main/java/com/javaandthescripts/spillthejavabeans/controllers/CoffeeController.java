/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaandthescripts.spillthejavabeans.models.Coffee;
import com.javaandthescripts.spillthejavabeans.services.CoffeeService;

@Controller
public class CoffeeController {
	@Autowired
	private CoffeeService coffeeServ;
	
	// read all
	@GetMapping("/coffee")
	public String coffee(Model model) {
		model.addAttribute("allCoffee", coffeeServ.getAll());
		return "coffee.jsp";
	}
	
//	GetMapping  - add coffee page (/add coffee)
	@GetMapping("/coffee/create")
	public String createCoffee(HttpSession session, Model model) {
		
//		Student oneStudent = studentServ.getOne(student_id);
//		model.addAttribute("s", oneStudent);
//		
		return "createCoffee.jsp";
	} //end of create coffee 
	
	// PostMapping - coffee create (/coffee create)
	@PostMapping("/coffee/create")
	public String createCoffee(@Valid @ModelAttribute("coffeeForm") Coffee newCoffee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			return "createCoffee.jsp";
		} else {
			coffeeServ.createOne(newCoffee);
			return "redirect:/coffee";
		}
	} // end of create coffee
	
	
	
} // end of coffee controller