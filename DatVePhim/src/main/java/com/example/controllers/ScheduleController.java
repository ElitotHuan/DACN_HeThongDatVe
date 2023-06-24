package com.example.controllers;

import com.example.dto.ScheduleDTO;
import com.example.services.BranchService;
import com.example.services.MovieService;
import com.example.services.RoomService;
import com.example.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = {"/schedule"})
    public ModelAndView schedule() {
        ModelAndView mav = new ModelAndView("client/schedule");
        mav.addObject("movies", movieService.getAll());
        mav.addObject("rooms", roomService.getAll());
        mav.addObject("branches", branchService.getAll());
        mav.addObject("schedules", scheduleService.getAll());
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

    @PostMapping(value = "/api/addSchedule")
    public String addMovie(@RequestBody ScheduleDTO scheduleDTO) {
        System.out.println(scheduleDTO.toString());
        Boolean ad = service.addSchedule(scheduleDTO);
        return "success";
    }

    @PutMapping(value = "/api/updateSchedule/{id}")
    public String updateMovie(@PathVariable("id") int id, @RequestBody ScheduleDTO scheduleDTO) {
        System.out.println(scheduleDTO.toString());
        Boolean up = service.updateSchedule(id, scheduleDTO);
        return "success";
    }


    @GetMapping(value = "/api/deleteSchedule/{id}")
    public ModelAndView deleteMovie(@PathVariable("id") int id) {
        Boolean del = service.deleteSchedule(id);
        return new ModelAndView("redirect:/api/manage_schedule");
    }

}
