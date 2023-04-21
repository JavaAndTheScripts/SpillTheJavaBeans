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
import com.javaandthescripts.spillthejavabeans.services.CafeService;
import com.javaandthescripts.spillthejavabeans.services.ManagerService;
import com.javaandthescripts.spillthejavabeans.services.SubscriberService;

@Controller
public class UserController {
	
    @Autowired
    private SubscriberService subServ;
    
    @Autowired
    private ManagerService manaServ;
    
    @Autowired
    private CafeService cafeServ;
    
// ==========================
//  	    USER
// ==========================   
    // logout 
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    } // logout
    
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
        model.addAttribute("cafe", cafeServ.getCafe());
        return "subsLog.jsp"; 
    }// subsLogin (get)
    @PostMapping("/subs/login")
    public String subsLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {    
        // Add once service is implemented:
        Subscriber subscriber = (Subscriber) subServ.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new Subscriber());
            return "subsLog.jsp"; 
        }

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", subscriber.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }// subsLogin (post)
    
    // register a new subscriber
    @GetMapping("/subs/register")
    public String subsReg(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }// if
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Subscriber());
        model.addAttribute("newLogin", new LoginUser());
        model.addAttribute("cafe", cafeServ.getCafe());
        return "subsReg.jsp"; 
    }// subsReg (get)
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
            model.addAttribute("cafe", cafeServ.getCafe());
            return "subsReg.jsp"; 
        }// if

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", newUser.getId());
        session.setAttribute("userTYPE", "Subscriber");
        return "redirect:/";
    }// subsReg (post)
    
// ==========================
//  	   MANAGER
// ========================== 
 // manager login
    @GetMapping("/mana/login")
    public String manaLogin(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }// if
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Manager());
        model.addAttribute("newLogin", new LoginUser());
        model.addAttribute("cafe", cafeServ.getCafe());
        return "manaLog.jsp"; 
    }// manaLogin (get)
    @PostMapping("/mana/login")
    public String manaLogin(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {    
        // Add once service is implemented:
        Manager manager = (Manager) manaServ.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new Subscriber());
            model.addAttribute("cafe", cafeServ.getCafe());
            return "manaLog.jsp"; 
        }// if

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", manager.getId());
        session.setAttribute("userTYPE", "Manager");
        return "redirect:/";
    }// manaLogin (post)
    
    // register a new manager
    @GetMapping("/mana/register")
    public String manaReg(Model model, HttpSession session){
        // check for session
        if(session.getAttribute("userID") != null) {
            return "redirect:/";
        }// if
        // Bind empty Subscriber and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new Manager());
        model.addAttribute("newLogin", new LoginUser());
        model.addAttribute("cafe", cafeServ.getCafe());
        return "manaReg.jsp"; 
    }// manaReg (get)
    @PostMapping("/mana/register")
    public String manaReg(@Valid @ModelAttribute("newUser") Manager newUser, 
            BindingResult result, Model model, HttpSession session) {    	
        // call a register method in the service 
        // to do some extra validations and create a new user!
        manaServ.register(cafeServ.getCafe(), newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            model.addAttribute("cafe", cafeServ.getCafe());
            return "manaReg.jsp"; 
        }// if

        // No errors! 
        // Store their ID from the DB in session, i.e. log them in.
        session.setAttribute("userID", newUser.getId());
        session.setAttribute("userTYPE", "Manager");
        return "redirect:/";
    }// manaReg (post)
}// UserController
