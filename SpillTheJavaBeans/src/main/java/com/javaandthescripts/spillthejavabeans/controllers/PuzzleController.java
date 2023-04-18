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
import org.springframework.web.bind.annotation.PutMapping;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.models.Puzzle;
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.PuzzleService;

@Controller
public class PuzzleController {
    @Autowired
    private PuzzleService puzzleServ;
    
    @Autowired
    private CafeService cafeServ; 
    
    // Get to determine if creating or updating a puzzle
    @GetMapping("/puzzle/edit")
    public String editPuzzle(HttpSession session, Model model) {
    	// make sure the manager is signed in
		if(session.getAttribute("userID") == null || session.getAttribute("userTYPE").equals("Subscriber")) {
	        return "redirect:/";
	    }// if
    	
        // get the cafe from the server
		Cafe cafe = cafeServ.getCafe();
		
		// save cafe to model
		model.addAttribute("cafe", cafe);
		
		// see if puzzle in cafe is not null
		if(cafe.getPuzzle() == null) {
			// go to create a puzzle
			return "createPuzzle.jsp";
		}// if
		
		// otherwise
		// go to edit 
       return "editPuzzle.jsp";
	}// edit
    
 	// CREATE puzzle
 	@PostMapping("/puzzle/create")
    public String createPuzzle(@Valid @ModelAttribute("modelForm") Puzzle puzzle, BindingResult result, Model model) {
        if(result.hasErrors()) {
            // get attribute from server
            // add attribute to model
        	model.addAttribute("cafe", cafeServ.getCafe());
        	
            return "createPuzzle.jsp";
        } //if

            puzzleServ.updateOne(puzzle);
            return "redirect:/";

    }// createPuzzle (post)

    // READ puzzle
    @GetMapping("/cafe/puzzle")
    public String puzzle(Model model) {
    	model.addAttribute("cafe", cafeServ.getCafe());
    	model.addAttribute("puzzle", puzzleServ.getOne());
        return "puzzle.jsp";
    }
 	
 	// UPDATE puzzle
 	@PutMapping("/puzzle/edit")
     public String editPuzzle(@Valid @ModelAttribute("modelForm") Puzzle puzzle, BindingResult result, Model model) {
         if(result.hasErrors()) {
             // get attribute from server
             // add attribute to model
         	model.addAttribute("cafe", cafeServ.getCafe());
             return "editPuzzle.jsp";
         } //if

             puzzleServ.updateOne(puzzle);
             return "redirect:/";

     }// editPuzzle (put) 
}// PuzzleController
