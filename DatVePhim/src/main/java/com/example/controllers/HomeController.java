package com.example.controllers;


import com.example.models.*;
import com.example.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        List<Movie> movieList = movieService.getAll();
        ModelAndView mav = new ModelAndView("client/home");
        mav.addObject("movies", movieList);
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