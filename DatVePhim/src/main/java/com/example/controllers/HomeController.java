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
    @Autowired
    private TicketService ticketService;

    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        List<Movie> movieList = movieService.getAll();
        ModelAndView mav = new ModelAndView("client/home");
        mav.addObject("movies", movieList);
        return mav;
    }

    @RequestMapping(value = {"/food"}, method = RequestMethod.GET)
    public ModelAndView food() {
        List<Food> foodList = foodService.getAll();
        ModelAndView mav = new ModelAndView("client/food");
        mav.addObject("foods", foodList);
        return mav;
    }

    @RequestMapping(value = {"/schedule"}, method = RequestMethod.GET)
    public ModelAndView schedule() {
        ModelAndView mav = new ModelAndView("client/schedule");
        mav.addObject("movies", movieService.getAll());
        mav.addObject("rooms", roomService.getAll());
        mav.addObject("branches", branchService.getAll());
        mav.addObject("schedules",  scheduleService.getAll());
        return mav;
    }


    @RequestMapping(value = "/movie-detail/{id}", method = RequestMethod.GET)
    public ModelAndView movieDetail(@PathVariable("id") int id) {
        Movie m = movieService.getMovieById(id);
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie", m);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<String> search(@RequestParam("q") String input) {
        List<String> strings = movieService.doAutoComplete(input);
        System.out.println(strings);
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";

        try {
            resp = mapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
        }
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(value = "/searchMovie", method = RequestMethod.GET)
    public ModelAndView searchMovie(@RequestParam("name") String name) {
        Movie m = movieService.getMovieByName(name);
        if (m == null) {
            ModelAndView mav = new ModelAndView("client/not-found");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie", m);
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