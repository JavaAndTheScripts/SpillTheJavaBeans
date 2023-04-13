package com.javaandthescripts.spillthejavabeans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //@Autowired
    //private <SERVICE_NAME> <service_name>;
    
    @GetMapping("/") // reserve route
    public String index() {
        return "index.jsp"; // map route to jsp
    }
}
