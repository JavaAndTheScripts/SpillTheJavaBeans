package com.javaandthescripts.spillthejavabeans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaandthescripts.spillthejavabeans.services.DrinkService;

@Controller
public class DrinkController {
    @Autowired
    private DrinkService drinkServ;

    @GetMapping("/drink")
    public String drink() {
        return "drink.jsp";
    }
    
    // CREATE DRINK
    
    // READ DRINKS
    
    // UPDATE DRINK
    
    // DELETE DRINK
}
