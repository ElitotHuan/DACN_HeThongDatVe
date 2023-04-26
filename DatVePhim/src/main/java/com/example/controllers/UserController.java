package com.example.controllers;

import com.example.dto.UserDTO;
import com.example.models.User;
import com.example.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("client/register");
        }
        boolean result = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName());
        if (result) {
            model.addAttribute("successMsg", "Đăng ký thành công!");
            return new ModelAndView("redirect:/login");
        } else {
            model.addAttribute("errorMsg", "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác!");
        }
        return new ModelAndView("client/register");
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("client/login");
        }
        boolean result = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (result) {
            User loggedInUser = userService.getUserByUsername(user.getUsername());
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("successMsg", "Đăng Nhập thành công!");
            return new ModelAndView("redirect:/");
        } else {
            model.addAttribute("errorMsg", "Tên đăng nhập hoặc mật khẩu không đúng !");
        }
        return new ModelAndView("client/login");
    }
}