package com.javaandthescripts.spillthejavabeans.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.services.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService cafeServ;	
	
	// CREATE CAFE
	@PostMapping("/cafe/create")
    public String createCafe(@Valid @ModelAttribute("modelForm") Cafe cafe, BindingResult result, Model model) {
        if(result.hasErrors()) { // for how this code is set up, this should NEVER trigger
            // get attribute from server
            // add attribute to model
    		model.addAttribute("cafeName", "Spill the Java Beans");
    		
            return "index.jsp"; 
        } else {
            cafeServ.createOne(cafe);
            return "redirect:/cafe";
        }
    }
	
	// READ CAFE
	@GetMapping("/cafe")
    public String cafe( Model model) {
		// get the cafe from the server
		model.addAttribute("cafe", cafeServ.getCafe());

        return "cafe.jsp";
    }
	
	// DELETE CAFE
	
}// CafeController
