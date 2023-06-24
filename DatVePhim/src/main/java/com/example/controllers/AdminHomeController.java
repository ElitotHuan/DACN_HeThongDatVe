package com.example.controllers;

import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminHomeController {

    @GetMapping(value = {"/api/", "/api/admin_home"})
    public ModelAndView adminHomeView(@AuthenticationPrincipal UserDetails user) {
        ModelAndView mav = new ModelAndView("admin/admin_home");
        mav.addObject("user",user);

        mav.addObject("sessionScope","user" );
        mav.addObject("username",user.getUsername());
        return mav;
    }

}