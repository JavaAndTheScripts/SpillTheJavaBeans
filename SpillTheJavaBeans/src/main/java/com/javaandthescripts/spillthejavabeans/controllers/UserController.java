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
import com.javaandthescripts.spillthejavabeans.models.Manager;
import com.javaandthescripts.spillthejavabeans.models.Subscriber;
import com.javaandthescripts.spillthejavabeans.services.ManagerService;
import com.javaandthescripts.spillthejavabeans.services.SubscriberService;

@Controller
public class UserController {
	
    @Autowired
    private SubscriberService subServ;
    
    @Autowired
    private ManagerService manaServ;
    
// ==========================
//  	    USER
// ==========================   
    // logout 
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
// ==========================
//        SUBSCRIBER
// ==========================     
    // subscriber login
    @GetMapping("/subs/login")
    public String subsLogin(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Subscriber());
        model.addAttribute("newLogin", new LoginUser());
        return "subsLog.jsp"; // HERE
    }
    @PostMapping("/subs/login")
    public String subsLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {    
        // Add once service is implemented:
        Subscriber subscriber = (Subscriber) subServ.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new Subscriber());
            return "subsLog.jsp"; // HERE
        }

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", subscriber.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }
    
    // register a new subscriber
    @GetMapping("/subs/register")
    public String subsReg(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Subscriber());
        model.addAttribute("newLogin", new LoginUser());
        return "subsReg.jsp"; // HERE
    }
    @PostMapping("/subs/register")
    public String subsReg(@Valid @ModelAttribute("newUser") Subscriber newUser, 
            BindingResult result, Model model, HttpSession session) {
        // call a register method in the service 
        // to do some extra validations and create a new user!
        subServ.register(newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "subsReg.jsp"; // HERE
        }

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", newUser.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }
    
// ==========================
//  	   MANAGER
// ========================== 
 // subscriber login
    @GetMapping("/mana/login")
    public String manaLogin(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Subscriber());
        model.addAttribute("newLogin", new LoginUser());
        return "manaLog.jsp"; 
    }
    @PostMapping("/mana/login")
    public String manaLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {    
        // Add once service is implemented:
        Subscriber subscriber = (Subscriber) subServ.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new Subscriber());
            return "manaLog.jsp"; 
        }

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", subscriber.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }
    
    // register a new subscriber
    @GetMapping("/mana/register")
    public String manaReg(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Manager());
        model.addAttribute("newLogin", new LoginUser());
        return "manaReg.jsp"; 
    }
    @PostMapping("/mana/register")
    public String manaReg(@Valid @ModelAttribute("newUser") Manager newUser, 
            BindingResult result, Model model, HttpSession session) {    	
        // call a register method in the service 
        // to do some extra validations and create a new user!
        manaServ.register(session, newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "manaReg.jsp"; 
        }

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", newUser.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }
}
