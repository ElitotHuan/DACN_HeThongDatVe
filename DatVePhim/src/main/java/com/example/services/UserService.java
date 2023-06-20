package com.example.services;

import com.example.dto.UserDTO;
import com.example.models.Movie;
import com.example.models.Role;
import com.example.models.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public boolean registerUser(String username, String password, String email, String fullName,LocalDate birthday, String address) {
        // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu chưa
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        // Kiểm tra xem địa chỉ email đã được sử dụng bởi một tài khoản khác chưa
        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        // Tạo một đối tượng Role có tên là USER
        Role userRole = roleRepository.findByName("USER");

        // Nếu không tìm thấy Role có tên là USER thì tạo mới
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }
        // Tạo một đối tượng User mới
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu bằng BCrypt trước khi lưu vào cơ sở dữ liệu
        user.setEmail(email);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setBirthday(birthday);
        user.getRoles().add(userRole);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        System.out.println("Đăng Kí: " + user);
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

    public boolean authenticateAdmin(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                if ("ADMIN".equals(role.getName())) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean hasRole(User user, String roleName) {
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
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

    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        //encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }


    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDTO convertEntityToDto(User user) {
        UserDTO userDto = new UserDTO();

        userDto.setFullName(user.getFullName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    // CRUD
    public boolean addUser(UserDTO userDTO) {
        User newUser = new User();
        setData(userDTO, newUser);
        userRepository.save(newUser);
        return true;
    }

    public boolean updateUser(UserDTO userDTO, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userDTO.getFullName());
            user.setPassword(userDTO.getPassword());
            user.setUsername(userDTO.getUsername());
            user.setAddress(userDTO.getAddress());
            user.setBirthday(LocalDate.parse(userDTO.getBirthday()));
            user.setEmail(userDTO.getEmail());

            // Xóa tất cả cácvai trò hiện tại của người dùng
            if(userDTO.getRoles() != null){
                user.getRoles().clear();

                // Thêm vai trò mới vào danh sách vai trò của người dùng
                List<Role> roles = new ArrayList<>();
                roles.add(roleRepository.findByName(userDTO.getRoles()));
                user.setRoles(roles);
            }
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
            // Xóa tất cả các mối quan hệ giữa người dùng và các vai trò trước khi xóa người dùng
            user.getRoles().clear();
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
            userDTO.setAddress(user.getAddress());
            userDTO.setBirthday(user.getBirthday().toString());
            userDTO.setEmail(user.getEmail());
            String roles=null;
            for (Role ur:  user.getRoles()
            ) {
                roles+= ur.getName();
            }
            userDTO.setRoles(roles);
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
            if (user.getBirthday()!= null ) {
                userDTO.setBirthday(user.getBirthday().toString());}
            userDTO.setAddress(user.getAddress());
            String roles=null;
            for (Role ur:  user.getRoles()
            ) {
                roles+= ur.getName();
            }
            userDTO.setRoles(roles);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    private Boolean setData(UserDTO userDTO, User user) {
        user.setFullName(userDTO.getFullName());
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(userDTO.getUsername());
        user.setBirthday(LocalDate.parse(userDTO.getBirthday()));
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        List<Role> roles = new ArrayList<>();
        Role roleAdmin = roleRepository.findByName("ADMIN");
        roles.add(roleAdmin);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }
    public boolean checkAdminRoleByUsername(String username) {
        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userRepository.findByUsername(username);

        // Kiểm tra xem người dùng có tồn tại hay không
        if (user != null) {
            // Lấy danh sách các role của người dùng
            List<Role> roles = user.getRoles();

            // Kiểm tra xem người dùng có role là "ADMIN" hay không
            for (Role role : roles) {
                System.out.println(role.getName());
                if (role.getName().equals("ADMIN")) {
                    return true;
                }
            }
        }

        // Người dùng không có role là "ADMIN"
        return false;
    }

    public boolean changePassword(String username, String currentPassword, String newPassword) {
        // Tìm kiếm người dùng trong cơ sở dữ liệu bằng tên đăng nhập
        User user = userRepository.findByUsername(username);

        // Kiểm tra xem mật khẩu hiện tại có khớp với mật khẩu trong cơ sở dữ liệu hay không
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }

        // Mã hóa mật khẩu mới bằng BCrypt
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Cập nhật mật khẩu mới cho người dùng
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return true;
    }
}
