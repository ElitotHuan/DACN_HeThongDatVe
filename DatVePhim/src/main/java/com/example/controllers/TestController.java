package com.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping(value = "/")

    public String getResponseAsText() {

        return "home";
    }
}

