package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean registerUser(String username, String password, String email, String fullName) {
        // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu chưa
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        // Kiểm tra xem địa chỉ email đã được sử dụng bởi một tài khoản khác chưa
        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        // Tạo một đối tượng User mới
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu bằng BCrypt trước khi lưu vào cơ sở dữ liệu
        user.setEmail(email);
        user.setFullName(fullName);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        userRepository.save(user);

        return true;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
