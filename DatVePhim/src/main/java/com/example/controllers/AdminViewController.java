package com.example.controllers;

import com.example.models.Movie;
import com.example.models.Schedule;
import com.example.services.BranchService;
import com.example.services.MovieService;
import com.example.services.RoomService;
import com.example.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AdminViewController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BranchService branchService;


    @GetMapping(value = {"/api/", "/api/admin_home"})
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

    @GetMapping(value = "/api/manage_schedule")
    public ModelAndView scheduleMangamentView() {
        ModelAndView mav = new ModelAndView("admin/manage_schedule");
        mav.addObject("movies", movieService.getAll());
        mav.addObject("rooms", roomService.getAll());
        mav.addObject("branches", branchService.getAll());
        mav.addObject("schedules",  scheduleService.getAll());
        return mav;
    }

    @GetMapping(value = "api/manage_branch")
    public ModelAndView branchManagementView() {
        ModelAndView mav = new ModelAndView("admin/manage_branch");
        mav.addObject("branches", branchService.getAll());
        return mav;
    }

    @GetMapping(value = "api/manage_room")
    public ModelAndView roomManagementView() {
        ModelAndView mav = new ModelAndView("admin/manage_room");
        mav.addObject("rooms", roomService.getAll());
        mav.addObject("branches", branchService.getAll());
        return mav;
    }

    @GetMapping(value = "/api/login")
    public ModelAndView adminLoginView() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }
}