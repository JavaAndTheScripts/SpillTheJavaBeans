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

import com.javaandthescripts.spillthejavabeans.models.Drink;
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.CoffeeService;
import com.javaandthescripts.spillthejavabeans.services.DrinkService;

@Controller
public class DrinkController {
    @Autowired
    private DrinkService drinkServ;
    
    @Autowired
    private CafeService cafeServ;
    
    @Autowired
    private CoffeeService coffeeServ;

    @GetMapping("/drink")
    public String drink() {
        return "drink.jsp";
    }
    
    // CREATE DRINK
    @GetMapping("/drink/create")
    public String createDrink(HttpSession session, Model model) {
    	// make sure the manager is signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Subscriber")) {
	        return "redirect:/";
	    }// if
		// get cafe info from server
		model.addAttribute("cafe", cafeServ.getCafe());
		// get all coffee from server
		model.addAttribute("allCoffee", coffeeServ.getAll());
        return "createDrink.jsp";
    }// createDrink (get)
    @PostMapping("/drink/create")
    public String createDrink(@Valid @ModelAttribute("modelForm") Drink drink, BindingResult result, Model model) {
        if(result.hasErrors()) {
        	// get cafe info from server
    		model.addAttribute("cafe", cafeServ.getCafe());
    		// get all coffee from server
    		model.addAttribute("allCoffee", coffeeServ.getAll());
    		
            return "createDrink.jsp";
        } else {
            drinkServ.createOne(drink);
            return "redirect:/cafe";
        }
    }
    
    // READ DRINKS - in [CafeController -> menu()]
    
    // UPDATE DRINK
    
    // DELETE DRINK
}
