package com.example.services;

import com.example.dto.UserDTO;
import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        // Set mặc định role to "USER"
        user.setRoles(Collections.singleton("USER"));

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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    // CRUD
    public boolean addUser(UserDTO userDTO) {
        User newUser = new User();
        setData(userDTO,newUser);
        return true;
    }

    public boolean updateUser(UserDTO userDTO, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userDTO.getFullName());
            user.setPassword(userDTO.getPassword());
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setRoles(userDTO.getRoles());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    public UserDTO getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setRoles(user.getRoles());
            return userDTO;
        } else {
            return null;
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setRoles(user.getRoles());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    private Boolean setData(UserDTO userDTO, User user) {
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        userRepository.save(user);
        return true;
    }

}
