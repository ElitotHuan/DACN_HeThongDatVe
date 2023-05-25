package com.example.controllers;

import com.example.models.Movie;
import com.example.models.Food;
import com.example.models.Schedule;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private FoodService foodService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private UserService userService;

    @Autowiredc
    private TicketService ticketService;

    @Autowired
    private SatisticsService satisticsService;
    
    @Autowired
    private RoleService roleService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"/api/", "/api/admin_home"})
    public ModelAndView adminHomeView(@AuthenticationPrincipal UserDetails user) {
        ModelAndView mav = new ModelAndView("admin/admin_home");
        mav.addObject("user",user);

        mav.addObject("sessionScope","user" );
        mav.addObject("username",user.getUsername());
        return mav;
    }

    @GetMapping(value = "/api/manage_movie")
    public ModelAndView movieMangamentView() {
        List<Movie> movieList =  movieService.getAll();
        ModelAndView mav = new ModelAndView("admin/manage_movie");
        mav.addObject("movies", movieList);
        return mav;
    }
    @GetMapping(value = "/api/manage_food")
    public ModelAndView foodMangamentView() {
        List<Food> foodList =  foodService.getAll();
        ModelAndView mav = new ModelAndView("admin/manage_food");
        mav.addObject("foods", foodList);
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


    @GetMapping(value = "/api/manage_user")
    public ModelAndView userManagementView(){
        ModelAndView mav = new ModelAndView("admin/manage_user");
        mav.addObject("users",userService.getAllUsers());
        mav.addObject("roles",roleService.getAllRoles());
        return mav;
    }

    @GetMapping("/api/manage_ticket")
    public ModelAndView ticketManagement() {
        ModelAndView mav = new ModelAndView("admin/manage_ticket");
        mav.addObject("tickets",ticketService.getAll());
        return mav;
    }

    @GetMapping(value = "/api/manage_satistics")
    public ModelAndView satisticManagementView() {
        ModelAndView mav = new ModelAndView("admin/manage_satistics");
        mav.addObject("info",satisticsService.getTotalFromAllBranch());
        return mav;
    }

    @GetMapping(value = "/api/login")
    public ModelAndView adminLoginView() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }
}