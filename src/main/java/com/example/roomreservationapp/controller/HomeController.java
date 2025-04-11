package com.example.roomreservationapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ReCiclo/")
public class HomeController {

    @GetMapping()
    public String home(){
        return "home";
    }
}

