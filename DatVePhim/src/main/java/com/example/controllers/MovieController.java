package com.example.controllers;

import com.example.dto.MovieDTO;
import com.example.models.Movie;
import com.example.services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping(value = "/api/addMovie")
    public String addMovie(@RequestBody MovieDTO movieDTO) {
        System.out.println(movieDTO.toString());
        Boolean ad = service.addMovie(movieDTO);
        return "success";
    }

    @PutMapping(value = "/api/updateMovie/{id}")
    public String updateMovie(@PathVariable("id") int id, @RequestBody MovieDTO movieDTO) {
        Boolean up = service.updateMovie(movieDTO, id);
        return "success";
    }

    @RequestMapping(value = "/api/deleteMovie/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMovie(@PathVariable("id") int id) {
        Boolean del = service.deleteMovie(id);
        return new ModelAndView("redirect:/api/manage_movie");
    }


    @RequestMapping(value = "/movie-detail/{id}", method = RequestMethod.GET)
    public ModelAndView getMovieInfo(@PathVariable("id") int id, RedirectAttributes ra) {
        Movie m = service.getMovieById(id);
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie", m);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<String> search(@RequestParam("q") String input) {
        List<String> strings = service.doAutoComplete(input);
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
        Movie m = service.getMovieByName(name);
        if (m == null) {
            ModelAndView mav = new ModelAndView("client/not-found");
            return mav;
        }
        ModelAndView mav = new ModelAndView("client/movie-detail");
        mav.addObject("movie", m);
        return mav;
    }

}