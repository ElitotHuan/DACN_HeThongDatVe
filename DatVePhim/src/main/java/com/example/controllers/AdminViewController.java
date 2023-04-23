package com.example.controllers;

import com.example.models.Movie;
import com.example.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AdminViewController {

    @Autowired
    private MovieService movieService;


    @GetMapping(value = {"/api", "/api/admin_home"})
    public ModelAndView adminHomeView() {
        ModelAndView mav = new ModelAndView("admin/admin_home");
        return mav;
    }

    @GetMapping(value = "/api/manage_movie")
    public ModelAndView movieMangamentView() {
        List<Movie> movieList =  movieService.getAll();
        ModelAndView mav = new ModelAndView("admin/manage_movie");
        mav.addObject("movies", movieList);
        return mav;
    }

    @GetMapping(value = "/api/login")
    public ModelAndView scheduleMangamentView() {
        ModelAndView mav = new ModelAndView("admin/manage_schedule");
        return mav;
    }

    @GetMapping(value = "/api")
    public ModelAndView adminLoginView() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }
}
