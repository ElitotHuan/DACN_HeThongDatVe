package com.example.controllers;

import com.example.models.Movie;
import com.example.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        List<Movie> movieList =  movieService.getAll();
        ModelAndView mav = new ModelAndView("client/home");
        mav.addObject("movies", movieList);
        return mav;
    }

    @RequestMapping(value = "/movie-detail/{id}", method = RequestMethod.GET)
    public ModelAndView movieDetail(@PathVariable("id") int id) {
        Movie m = movieService.getMovieById(id);
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie" , m);
        return mav;
    }

    @RequestMapping(value = "/searchMovie", method = RequestMethod.GET)
    public ModelAndView searchMovie(@RequestParam(required = true) String name) {
        Movie m = movieService.getMovieByName(name);
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie" , m);
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
