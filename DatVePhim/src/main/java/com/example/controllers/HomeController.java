package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    //@RequestMapping(value = "/home", method = RequestMethod.GET)
//    @GetMapping("home")
//    public String home(){
//        return "home";
//    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/client/home");
        System.out.println(mav);
        return mav;
    }

    @RequestMapping(value = "/movie-detail", method = RequestMethod.GET)
    public ModelAndView movieDetail() {
        ModelAndView mav = new ModelAndView("client/movie-detail");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
        ModelAndView mav = new ModelAndView("client/login");
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView RegisterView() {
        ModelAndView mav = new ModelAndView("client/register");
        return mav;
    }
}