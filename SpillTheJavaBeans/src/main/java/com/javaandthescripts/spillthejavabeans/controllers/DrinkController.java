package com.javaandthescripts.spillthejavabeans.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
        }// if
        
        drinkServ.createOne(drink);
        return "redirect:/cafe";        
    }
    
    // READ DRINKS - in [CafeController -> menu()]
    
    // UPDATE DRINK
    @GetMapping("/drink/{id}/updateDrink")
    public String updateDrink(HttpSession session, @PathVariable("id") Long id, Model model) {
    	// make sure the manager is signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Subscriber")) {
	        return "redirect:/";
	    }// if
		//manager is signed in
		model.addAttribute("drink", drinkServ.getOne(id));
		model.addAttribute("allCoffee", coffeeServ.getAll());

		model.addAttribute("cafe", cafeServ.getCafe());
		return "updateDrink.jsp";
    }//updateDrink (get)
    @PutMapping("/drink/{id}/updateDrink")
    public String updateDrink(@PathVariable("id") Long id, @Valid @ModelAttribute("updateForm") Drink drink, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		//get drink info to return to the update/edit form
    		model.addAttribute("drink", drinkServ.getOne(id));
    		// get all coffee from server for the update/edit form
    		model.addAttribute("allCoffee", coffeeServ.getAll());

    		model.addAttribute("cafe", cafeServ.getCafe());
    		
            return "updateDrink.jsp";
        }// if
        
        drinkServ.updateOne(drink);
        return "redirect:/cafe/menu";        
    }
    
    // DELETE DRINK
 	//delete a drink
 	@DeleteMapping("/drink/{id}/deleteDrink")
 	public String destroy(@PathVariable("id") Long id, @ModelAttribute("deleteForm") Drink drink) {
 		drinkServ.deleteOne(id);
 		return "redirect:/cafe/menu";
 	}
    
    
} //end of DrinkController
