package com.javaandthescripts.spillthejavabeans.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.services.CafeService;

@Controller
public class HomeController {
    @Autowired
    private CafeService cafeServ;
    
    
    @GetMapping("/") // reserve route
    public String index(HttpSession session, Model model) {
    	Cafe cafe = cafeServ.getCafe();
    	// if there is not a Cafe in the database
    	if(cafe == null) {	//	cafeServ.getCafe() == null
    		model.addAttribute("cafeName", "Spill the Java Beans");
    		// make a cafe
    		return "index.jsp"; // page where form exists
    	}
    	// set cafe id in session 
    	session.setAttribute("cafeID", cafe.getId());
        return "redirect:/cafe"; // page that will show the cafe
    }
}
