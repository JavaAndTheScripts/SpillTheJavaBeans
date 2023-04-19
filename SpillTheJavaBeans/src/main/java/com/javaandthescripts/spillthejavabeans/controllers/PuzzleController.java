package com.javaandthescripts.spillthejavabeans.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.models.Puzzle;
import com.javaandthescripts.spillthejavabeans.models.Subscriber;
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.PuzzleService;
import com.javaandthescripts.spillthejavabeans.services.SubscriberService;

@Controller
public class PuzzleController {
    @Autowired
    private PuzzleService puzzleServ;
    
    @Autowired
    private CafeService cafeServ; 
    
    @Autowired
    private SubscriberService subServ;
    
    // Get to determine if creating or updating a puzzle
    @GetMapping("/puzzle/edit")
    public String editPuzzle(HttpSession session, @ModelAttribute("modelForm") Puzzle puzzle, Model model) {
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
    	model.addAttribute("puzzle", puzzleServ.getOne());
       return "editPuzzle.jsp";
	}// editPuzzle (get)
    
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
    public String puzzle(HttpSession session, Model model) {
    	if(session.getAttribute("userTYPE") == "Subscriber") {
    		model.addAttribute("subscriber", subServ.getOne((Long)session.getAttribute("userID")));
    	}
    	model.addAttribute("cafe", cafeServ.getCafe());
    	model.addAttribute("puzzle", puzzleServ.getOne());	
		return "puzzle.jsp";
    }// puzzle
 	
 	// UPDATE puzzle
 	@PutMapping("/puzzle/edit")
     public String editPuzzle(@Valid @ModelAttribute("modelForm") Puzzle puzzle, BindingResult result, Model model) {
         if(result.hasErrors()) {
            // get attribute from server
            // add attribute to model
         	model.addAttribute("cafe", cafeServ.getCafe());
        	model.addAttribute("puzzle", puzzleServ.getOne());
            return "editPuzzle.jsp";
         } //if
//         System.out.println("== REACHED 98 ==");
         // reset all subscribers boolean and puzzle
         List<Subscriber> subs = subServ.getAll();
         System.out.println("=".repeat(20));
         System.out.printf("\n\tSUBS SIZE [102]:\t %s\n\n", subs.size());
         System.out.println("=".repeat(20));
         for(Subscriber s : subs) {
        	 s.setSolvedPuzzle(false); // reset boolean
        	 s.setPuzzle(null); // reset relationship
        	 subServ.updateOne(s);
         }// for each
         puzzleServ.updateOne(puzzle);
         return "redirect:/cafe/puzzle";
     }// editPuzzle (put) 
 	
 	// MAKE A GUESS
 	@PostMapping("/puzzle/guess")
 	public String guess(HttpSession session, @RequestParam("guess") String guess, Model model) {
 		Puzzle puzzle = puzzleServ.getOne();
 		Subscriber user = subServ.getOne((Long)session.getAttribute("userID"));
 		
 		// mark that user submitted guess
 		user.setSolvedPuzzle(true);
 		
 		// if the guess was right
 		if(guess.equals(puzzle.getSolution())) {
 			// set up relationship
 			user.setPuzzle(puzzle);
 		}// if
 		// otherwise if the guess is wrong
 		// save changes on user
 		subServ.updateOne(user);
 		// redirect to puzzle
 		return "redirect:/cafe/puzzle";
 	}
 	
}// PuzzleController
