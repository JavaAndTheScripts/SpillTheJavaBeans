package com.javaandthescripts.spillthejavabeans.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.models.Subscriber;
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.CoffeeService;
import com.javaandthescripts.spillthejavabeans.services.DrinkService;
import com.javaandthescripts.spillthejavabeans.services.SubscriberService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService cafeServ;
	
	@Autowired
	private CoffeeService coffeeServ;
	
	@Autowired
	private DrinkService drinkServ;
	
	@Autowired
	private SubscriberService subServ;
	
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
    }// createCafe (post)
	
	// READ CAFE 
	// - should only reach here if cafe exists in database [HomeController -> index()]
	@GetMapping("/cafe")
    public String cafe(HttpSession session, Model model) {
		// get the cafe from the server
		model.addAttribute("cafe", cafeServ.getCafe());

        return "cafe.jsp";
    }// cafe
	
	// READ MENU
	@GetMapping("/cafe/menu")
    public String menu(HttpSession session, Model model) {
		// get the cafe from the server
		model.addAttribute("cafe", cafeServ.getCafe());
		model.addAttribute("allDrinks", drinkServ.getAll());
        return "menu.jsp";
    }
	
	// UPDATE Coffee of the Month
	@GetMapping("/cafe/coffee/edit")
    public String monthlyCoffee(HttpSession session, Model model) {
		// make sure the manager is signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Subscriber")) {
	        return "redirect:/";
	    }// if
		
		// get cafe from server
		model.addAttribute("cafe", cafeServ.getCafe());
		// get all coffee from server
		model.addAttribute("allCoffee", coffeeServ.getAll());
		
        return "monthlyCoffee.jsp";
    }// monthlyCoffee (get)
	@PutMapping("/cafe/coffee/edit")
    public String monthlyCoffee(@Valid @ModelAttribute("modelForm") Cafe cafe, BindingResult result, Model model) {			
		if(result.hasErrors()) {
			// get cafe from server
			model.addAttribute("cafe", cafeServ.getCafe());
			// get all coffee from server
			model.addAttribute("allCoffee", coffeeServ.getAll());
            return "monthlyCoffee.jsp";
        }// if 
        // update cafe in server
        cafeServ.updateOne(cafe);
        
        return "redirect:/cafe";
    }// monthlyCoffee (put)
	
	// DELETE CAFE    
	
	
// ===========================
//		SUBSCRIBER PAGES
// ===========================
	@GetMapping("/cafe/coupons")
	public String coupons(HttpSession session, @ModelAttribute("subForm") Subscriber user, Model model) {
		// make sure subscriber in signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Manager")) {
			return "redirect:/";
		}// if
		// get cafe from server
		model.addAttribute("cafe", cafeServ.getCafe());
		// get subscriber from server
		model.addAttribute("subscriber", subServ.getOne((Long)session.getAttribute("userID")));
		return "coupons.jsp";
	}// coupons (get)
	
	@PutMapping("/cafe/coupons/useBday") 
	public String bdayCoupon(HttpSession session, @Valid @ModelAttribute("subForm") Subscriber user, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			// get cafe from server
			model.addAttribute("cafe", cafeServ.getCafe());
			// get subscriber from server
			model.addAttribute("subscriber", subServ.getOne((Long)session.getAttribute("userID")));
            return "coupons.jsp";
        }// if 		
		
		// update information in server
		subServ.updateOne(user);
		// redirect to coupon page		
		return "redirect:/cafe/coupons";
	}// bdayCoupons (put)
	
	@PutMapping("/cafe/coupons/usePuzzle")
	public String puzzleCoupon(HttpSession session, @Valid @ModelAttribute("subForm") Subscriber user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			// get cafe from server
			model.addAttribute("cafe", cafeServ.getCafe());
			// get subscriber from server
			model.addAttribute("subscriber", subServ.getOne((Long)session.getAttribute("userID")));
            return "coupons.jsp";
        }// if 		
		
		// update information in server
		subServ.updateOne(user);
		// redirect to coupon page		
		return "redirect:/cafe/coupons";
	}
	
	
}// CafeController
