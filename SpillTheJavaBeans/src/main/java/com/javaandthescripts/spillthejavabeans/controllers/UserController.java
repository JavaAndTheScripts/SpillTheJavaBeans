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

import com.javaandthescripts.spillthejavabeans.models.LoginUser;
import com.javaandthescripts.spillthejavabeans.models.User;
import com.javaandthescripts.spillthejavabeans.services.UserService;

@Controller
public class UserController {

	
	    @Autowired
	    private UserService userServ;

	    @GetMapping("/logReg")
	    public String logReg(Model model, HttpSession session){
	        // check for session
	        if(session.getAttribute("userID") != null) {
	            return "redirect:/dashboard";
	        }
	        // Bind empty User and LoginUser objects to the JSP
	        // to capture the form input
	        model.addAttribute("newUser", new User());
	        model.addAttribute("newLogin", new LoginUser());
	        return "logReg.jsp";
	    }

	    @PostMapping("/register")
	    public String register(@Valid @ModelAttribute("newUser") User newUser, 
	            BindingResult result, Model model, HttpSession session) {
	        // call a register method in the service 
	        // to do some extra validations and create a new user!
	        userServ.register(newUser, result);
	        if(result.hasErrors()) {
	            // Be sure to send in the empty LoginUser before 
	            // re-rendering the page.
	            model.addAttribute("newLogin", new LoginUser());
	            return "logReg.jsp";
	        }

	        // No errors! 
	        // Store their ID from the DB in session, i.e. log them in.
	        session.setAttribute("userID", newUser.getId());
	        return "redirect:/";
	    }

	    @PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	            BindingResult result, Model model, HttpSession session) {    
	        // Add once service is implemented:
	        User user = userServ.login(newLogin, result);

	        if(result.hasErrors()) {
	            model.addAttribute("newUser", new User());
	            return "logReg.jsp";
	        }

	        // No errors! 
	        // Store their ID from the DB in session, i.e. log them in.
	        session.setAttribute("userID", user.getId());
	        return "redirect:/";
	    }

	    // logout 
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/";
	    }
}
