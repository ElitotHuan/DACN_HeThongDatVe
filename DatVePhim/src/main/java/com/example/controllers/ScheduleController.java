package com.example.controllers;

import com.example.dto.MovieDTO;
import com.example.dto.ScheduleDTO;
import com.example.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService service;

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
