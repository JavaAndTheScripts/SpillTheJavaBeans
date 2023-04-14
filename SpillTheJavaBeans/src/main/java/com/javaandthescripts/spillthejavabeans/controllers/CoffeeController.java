/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}