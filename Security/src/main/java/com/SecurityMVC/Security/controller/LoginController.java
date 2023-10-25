package com.SecurityMVC.Security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {
    @GetMapping("/showMyLoginPages")
    public String showLoginPages(){
        //return "/login-form";
        return "/fancy-login";

    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        //return "/login-form";
        return "/accessDenied";

    }
}
