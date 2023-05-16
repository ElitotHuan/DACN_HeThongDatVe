package com.example.controllers;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.models.Role;
import com.example.models.User;
import com.example.services.CustomUserDetailsService;
import com.example.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserManagerController {
    @Autowired
    private UserService userService;

    private Authentication authentication;

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

    @PostMapping("/admin/login")
    public ModelAndView loginAdmin(@ModelAttribute("user") @Valid LoginDTO user, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/login");
        }

        boolean result = userService.authenticateAdmin(user.getUsername(), user.getPassword());
        if (result) {
            User loggedInUser = userService.getUserByUsername(user.getUsername());

            List<Role> roles = loggedInUser.getRoles();
            String roleNames = "";
            for (Role role : roles) {
                roleNames += role.getName() + " ";
                System.out.println("ROLE" + roleNames);
            }
            session.setAttribute("loggedInUser", loggedInUser);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("successMsg", "Đăng Nhập thành công! Vai trò: " + roleNames);
            System.out.println(loggedInUser);

            return new ModelAndView("redirect:/api/admin_home");

        } else {
            model.addAttribute("errorMsg", "Tên đăng nhập hoặc mật khẩu không đúng hoặc bạn không có quyền truy cập !");
            return new ModelAndView("redirect:/admin/login?error=true");
        }
    }
}