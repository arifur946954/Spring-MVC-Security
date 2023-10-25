package com.SecurityMVC.Security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/home")
    public String ShowHome(){
        return "/home";
    }

    @GetMapping("/leaders")
    public String ShowLeaders(){
        return "/leader";
    }

    @GetMapping("/systems")
    public String ShowAdmin(){
        return "/admin";
    }
}


