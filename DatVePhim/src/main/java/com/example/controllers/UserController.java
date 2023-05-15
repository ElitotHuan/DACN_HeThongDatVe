package com.example.controllers;

import com.example.dto.LoginDTO;
import com.example.models.User;
import com.example.services.UserService;
import javax.validation.Valid;

import com.example.utils.Email;
import com.example.utils.EmailUtils;
import com.example.utils.PasswordUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


    @RestController
    public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

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
        public ModelAndView loginUser(@ModelAttribute("user") @Valid LoginDTO user, BindingResult bindingResult, Model model, HttpSession session) {

            if (bindingResult.hasErrors()) {
                return new ModelAndView("client/login");
            }
            boolean result = userService.authenticateUser(user.getUsername(), user.getPassword());
            if (result) {
                User loggedInUser = userService.getUserByUsername(user.getUsername());
                session.setAttribute("loggedInUser", loggedInUser);
                model.addAttribute("loggedInUser", loggedInUser);
                model.addAttribute("successMsg", "Đăng Nhập thành công!");
                System.out.println(loggedInUser);
                return new ModelAndView("redirect:/");
            } else {
                model.addAttribute("errorMsg", "Tên đăng nhập hoặc mật khẩu không đúng !");
                return new ModelAndView("client/login");
            }
        }
        // Get Quên mật khẩu
        @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
        public ModelAndView ForgotPasswordPage() {
            ModelAndView mav = new ModelAndView("client/forgot-password");
            return mav;
        }

        // Get Quên mật khẩu
        @RequestMapping(value = "/reset-password-success", method = RequestMethod.GET)
        public ModelAndView ResetPasswordPage() {
            ModelAndView mav = new ModelAndView("client/reset-password-success");
            return mav;
        }

        @GetMapping("/logout")
        public ModelAndView logout(HttpSession session) {
            // Xóa thông tin người dùng đăng nhập khỏi session
            session.removeAttribute("loggedInUser");
            // Điều hướng đến trang đăng nhập
            return new ModelAndView("redirect:/");
        }

        @PostMapping("/forgot-password")
        public ModelAndView forgotPassword(@RequestParam("email") String email, Model model) {
            User user = userService.findByEmail(email);
            if (user == null) {
                model.addAttribute("errorMsg", "Địa chỉ email không tồn tại trong hệ thống");
                return new ModelAndView("client/forgot-password");
            }
            // Tạo mật khẩu mới cho người dùng
            String newPassword = PasswordUtils.generateNewPassword();
            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.saveUser(user);
            // Gửi email chứa mật khẩu mới đến địa chỉ email của người dùng
            try {
                Email emailObj = new Email();
                emailObj.setFrom("nguyenthaiducbhsmn@gmail.com");
                emailObj.setFromPassword("hjfxosozfyrtzrfc");
                emailObj.setTo(email);
                emailObj.setSubject("Mật khẩu mới của bạn");
                emailObj.setContent("Chào bạn,\n\nMật khẩu mới của bạn là: " + newPassword + "\n\nVui lòng đăng nhập bằng mật khẩu mới này và đổi lại mật khẩu của riêng bạn để đảm bảo an toàn tài khoản.\n\nTrân trọng,\nĐội ngũ hỗ trợ của chúng tôi");
                EmailUtils.send(emailObj);
            } catch (Exception e) {
                model.addAttribute("errorMsg", "Lỗi gửi email: " + e.getMessage());
                return new ModelAndView("client/forgot-password");
            }

            return new ModelAndView("client/reset-password-success");
        }

        // CRUD


}