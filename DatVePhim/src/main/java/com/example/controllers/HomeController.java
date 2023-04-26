package com.example.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("client/home");
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

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView NotFound404Page() {
        ModelAndView mav = new ModelAndView("client/404");
        return mav;
    }
}