

package com.example.controllers;

import com.example.dto.BranchDTO;
import com.example.dto.RoomDTO;
import com.example.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping(value = "/api/addRoom")
    public String addMovie(@RequestBody RoomDTO roomDTO) {
        System.out.println(roomDTO.toString());
        Boolean ad = service.addRoom(roomDTO);
        return "success";
    }

    @PutMapping(value = "/api/updateRoom/{id}")
    public String updateMovie(@PathVariable("id") int id, @RequestBody RoomDTO roomDTO) {
        Boolean up = service.updateRoom(id, roomDTO);
        return "success";
    }

    @RequestMapping(value = "/api/deleteRoom/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMovie(@PathVariable("id") int id) {
        Boolean del = service.deleteRoom(id);
        return new ModelAndView("redirect:/api/manage_room");
    }
}