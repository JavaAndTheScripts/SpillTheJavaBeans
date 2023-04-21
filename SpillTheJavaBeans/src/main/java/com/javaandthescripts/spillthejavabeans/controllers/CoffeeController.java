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

import com.javaandthescripts.spillthejavabeans.models.Coffee;
import com.javaandthescripts.spillthejavabeans.models.Drink;
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.CoffeeService;

@Controller
public class CoffeeController {
	@Autowired
	private CoffeeService coffeeServ;
	
	@Autowired
	private CafeService cafeServ;
	
	// read all
	@GetMapping("/coffee")
	public String coffee(Model model) {
		model.addAttribute("cafe", cafeServ.getCafe());
		model.addAttribute("allCoffee", coffeeServ.getAll());
		return "coffee.jsp";
	}
    // CREATE COFFEE	
	//	GetMapping  - add coffee page (/add coffee)
	@GetMapping("/coffee/create")
	public String createCoffee(HttpSession session, Model model) {
		// get the cafe from the server
		model.addAttribute("cafe", cafeServ.getCafe());
		return "createCoffee.jsp";
	} //end of create coffee (get)	
	// PostMapping - coffee create (/coffee create)
	@PostMapping("/coffee/create")
	public String createCoffee(@Valid @ModelAttribute("coffeeForm") Coffee newCoffee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			// get the cafe from the server
			model.addAttribute("cafe", cafeServ.getCafe());
			return "createCoffee.jsp";
		} else {
			coffeeServ.createOne(newCoffee);
			return "redirect:/coffee";
		}
	} // end of create coffee (post)
    
    // READ COFFEES
    
    // UPDATE COFFEE
	@GetMapping("/coffee/{id}/updateCoffee")
	   public String updateCoffee(HttpSession session, @PathVariable("id") Long id, Model model) {
    	// make sure the manager is signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Subscriber")) {
	        return "redirect:/";
	    }// if
		//manager is signed in
		model.addAttribute("coffee", coffeeServ.getOne(id));
		model.addAttribute("allCoffee", coffeeServ.getAll());
		// get the cafe from the server
		model.addAttribute("cafe", cafeServ.getCafe());
		return "updateCoffee.jsp";
    }//end updateCoffee (get)
    @PutMapping("/coffee/{id}/updateCoffee")
    public String updateCoffee(@PathVariable("id") Long id, @Valid @ModelAttribute("updateForm") Coffee coffee, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		//get coffee info to return to the update/edit form
    		model.addAttribute("coffee", coffeeServ.getOne(id));
    		// get all coffee from server for the update/edit form
    		model.addAttribute("allCoffee", coffeeServ.getAll());
    		// get the cafe from the server
    		model.addAttribute("cafe", cafeServ.getCafe());
            return "updateCoffee.jsp";
        }// if
        
        coffeeServ.updateOne(coffee);
        return "redirect:/coffee";        
    }

    
    // DELETE COFFEE
 	//delete a coffee
 	@DeleteMapping("/coffee/{id}/deleteCoffee")
 	public String destroy(@PathVariable("id") Long id, @ModelAttribute("deleteForm") Coffee coffee) {
 		coffeeServ.deleteOne(id);
 		return "redirect:/coffee";
 	}
	
} // end of coffee controller