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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class UserManagerController {
    @Autowired
    private UserService userService;

    private Authentication authentication;

    @PostMapping(value = "/api/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
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


    @PostMapping("/admin/login")
    public ModelAndView loginAdmin(@ModelAttribute("user") @Valid LoginDTO user, BindingResult bindingResult, ModelMap model, HttpSession session) {
        System.out.println("login ne");
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/login");
        }

        boolean result = userService.authenticateAdmin(user.getUsername(), user.getPassword());


        if (result) {
            User loggedInAdmin = userService.getUserByUsername(user.getUsername());

            if (userService.hasRole(loggedInAdmin, "ADMIN")) {
                //check role trên rôi dưới này không cần làm lại
                // người dùng có vai trò "ADMIN", cho phép truy cập vào trang quản lý của admin
                List<Role> roles = loggedInAdmin.getRoles();
                String roleNames = "ADMIN";

                session.setAttribute("loggedInAdmin", loggedInAdmin);
                model.addAttribute("loggedInAdmin", loggedInAdmin);
                model.addAttribute("successMsg", "Đăng Nhập thành công! Vai trò: " + roleNames);
                System.out.println(roleNames);


                return new ModelAndView("redirect:/api/admin_home", model);
            } else {
                // người dùng không có vai trò "ADMIN"
                model.addAttribute("errorMsg", "Bạn không có quyền truy cập vào trang quản lý của admin!");
                return new ModelAndView("redirect:/api/login?error=true");
            }
        } else {
            // không tìm thấy người dùng hoặc mật khẩu không đúng
            model.addAttribute("errorMsg", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return new ModelAndView("redirect:/api/login?error=true");
        }
    }

    @GetMapping("/admin/logout")
    public ModelAndView logout(HttpSession session) {
        // Xóa thông tin người dùng đăng nhập khỏi session
        session.removeAttribute("loggedInAdmin");
        // Điều hướng đến trang đăng nhập
        return new ModelAndView("redirect:/api/login");
    }
}