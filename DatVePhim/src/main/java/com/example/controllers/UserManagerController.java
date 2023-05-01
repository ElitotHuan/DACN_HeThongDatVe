package com.example.controllers;

import com.example.dto.UserDTO;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserManagerController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        Boolean added = userService.addUser(userDTO);
        System.out.println(added);
        return "success";
    }

    @PutMapping(value = "/api/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        Boolean updated = userService.updateUser(userDTO, id);
        return "success";
    }

    @RequestMapping(value = "/api/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        Boolean deleted = userService.deleteUser(id);
        return new ModelAndView("redirect:/api/manage_user");
    }

    @GetMapping(value = "/api/getUser/{id}")
    public UserDTO getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/api/getAllUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
