package com.javaandthescripts.spillthejavabeans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaandthescripts.spillthejavabeans.services.CafeService;

@Controller
public class HomeController {
    @Autowired
    private CafeService cafeServ;
    
    
    @GetMapping("/") // reserve route
    public String index(Model model) {
//    	Cafe cafe = cafeServ.getCafe();
    	// if there is not a Cafe in the database
    	if(cafeServ.getCafe() == null) {	//	cafe == null
    		model.addAttribute("cafeName", "Spill the Java Beans");
    		// make a cafe
    		return "index.jsp"; // page where form exists
    	}
        return "redirect:/cafe"; // page that 
    }
}
