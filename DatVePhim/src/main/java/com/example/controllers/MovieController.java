package com.example.controllers;

import com.example.dto.MovieDTO;
import com.example.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping(value = "/api/addMovie")
    public ModelAndView addMovie(@RequestBody MovieDTO movieDTO) {
        System.out.println(movieDTO.toString());
        Boolean ad = service.addMovie(movieDTO);
        return new ModelAndView("redirect:/api/manage_movie");
    }

    @PutMapping(value = "/api/updateMovie/{id}")
    public ModelAndView updateMovie(@PathVariable("id") int id, @RequestBody MovieDTO movieDTO) {
        Boolean up = service.updateMovie(movieDTO, id);
        return new ModelAndView("redirect:/api/manage_movie");
    }

    @RequestMapping(value = "/api/deleteMovie/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMovie(@PathVariable("id") int id) {
        Boolean del = service.deleteMovie(id);
        return new ModelAndView("redirect:/api/manage_movie");
    }


}
