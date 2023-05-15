package com.example.controllers;

import com.example.dto.FoodDTO;
import com.example.models.Movie;
import com.example.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class TicketController {

    @PostMapping("/ticket")
    public ModelAndView handleTicketRequest(@RequestParam("movie") String movie, @RequestParam("startdate") String startDate,@RequestParam("starttime") String startTime,@RequestParam("price") String price) {
        ModelAndView mav = new ModelAndView("client/ticket");
        mav.addObject("movie", movie);
        mav.addObject("startdate", startDate);
        mav.addObject("starttime", startTime);
        mav.addObject("price", price);
        return mav;
    }

}