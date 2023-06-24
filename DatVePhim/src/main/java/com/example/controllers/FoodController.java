package com.example.controllers;

import com.example.dto.FoodDTO;
import com.example.models.Food;
import com.example.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class FoodController {

    @Autowired
    private FoodService service;


    @GetMapping(value = {"/food"})
    public ModelAndView food() {
        List<Food> foodList = service.getAll();
        ModelAndView mav = new ModelAndView("client/food");
        mav.addObject("foods", foodList);
        return mav;
    }

    @GetMapping(value = "/api/manage_food")
    public ModelAndView foodMangamentView() {
        List<Food> foodList =  service.getAll();
        ModelAndView mav = new ModelAndView("admin/manage_food");
        mav.addObject("foods", foodList);
        return mav;
    }

    @PostMapping(value = "/api/addFood")
    public String addFood(@RequestBody FoodDTO foodDTO) {
        System.out.println(foodDTO.toString());
        Boolean ad = service.addFood(foodDTO);
        return "success";
    }


    @PutMapping(value = "/api/updateFood/{id}")
    public String updateFood(@PathVariable("id") int id, @RequestBody FoodDTO foodDTO) {
        Boolean up = service.updateFood(foodDTO, id);
        return "success";
    }

    @RequestMapping(value = "/api/deleteFood/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFood(@PathVariable("id") int id) {
        Boolean del = service.deleteFood(id);
        return new ModelAndView("redirect:/api/manage_food");
    }



}