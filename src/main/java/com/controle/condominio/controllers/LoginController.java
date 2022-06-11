package com.controle.condominio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
