package com.example.controllers;

import com.example.services.SatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SatisticsController {

    @Autowired
    private SatisticsService satisticsService;


    @GetMapping(value = "/api/manage_satistics")
    public ModelAndView satisticManagementView() {
        ModelAndView mav = new ModelAndView("admin/manage_satistics");
        mav.addObject("info",satisticsService.getTotalFromAllBranch());
        return mav;
    }
}
