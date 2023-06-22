package com.example.services;

import com.example.dto.UserDTO;
import com.example.models.Role;
import com.example.models.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    // Kiểm tra register
    void testRegisterUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);
        when(userRepository.findByEmail("test@example.com")).thenReturn(null);
        when(roleRepository.findByName("USER")).thenReturn(new Role());

        boolean result = userService.registerUser("testuser", "password", "test@example.com", "Test User");
        assertTrue(result);

        verify(userRepository).save(any(User.class));
    }

    @Test
    // kiểm tra authenicateUser
    void testAuthenticateUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedpassword")).thenReturn(true);

        boolean result = userService.authenticateUser("testuser", "password");
        assertTrue(result);
    }

    @Test
    // kiểm tra authenicateAdmin
    void testAuthenticateAdmin() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        user.getRoles().add(adminRole);

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedpassword")).thenReturn(true);

        boolean result = userService.authenticateAdmin("testuser", "password");
        assertTrue(result);
    }

    @Test
    // Kiểm tra hasRole
    void testHasRole() {
        User user = new User();
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        user.getRoles().add(adminRole);

        boolean result = userService.hasRole(user, "ADMIN");
        assertTrue(result);
    }

    @Test
    // Kiểm tra getUserByName
    void testGetUserByUsername() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        User result = userService.getUserByUsername("testuser");
        assertEquals(user, result);
    }

    @Test
    // Kiểm tra findByEmail
    void testFindByEmail() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User result = userService.findByEmail("test@example.com");
        assertEquals(user, result);
    }

    @Test
    // Kiểm tra saveUser
    void testSaveUser() {
        User user = new User();

        userService.saveUser(user);

        verify(userRepository).save(user);
    }

    @Test
    // Kiểm tra addUser
    void testAddUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setEmail("test@example.com");
        userDTO.setFullName("Test User");

        boolean result = userService.addUser(userDTO);
        assertTrue(result);

        verify(userRepository).save(any(User.class));
    }

    @Test
    // kiểm tra updateUser
    void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("test@example.com");
        user.setFullName("Test User");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("updateduser");
        userDTO.setPassword("updatedpassword");
        userDTO.setEmail("updated@example.com");
        userDTO.setFullName("Updated User");
        userDTO.setRoles(new ArrayList<>()); // Set the roles field to an empty list

        boolean result = userService.updateUser(userDTO, 1);
        assertTrue(result);

        verify(userRepository).save(any(User.class));
    }

    @Test
    // kiểm tra deleteUser
    void testDeleteUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("test@example.com");
        user.setFullName("Test User");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        boolean result = userService.deleteUser(1);
        assertTrue(result);

        verify(userRepository).delete(any(User.class));
    }

    @Test
    // kiểm tra getUserById
    void testGetUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("test@example.com");
        user.setFullName("Test User");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    // kiểm tra getAllUsers
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUsername("testuser" + (i + 1));
            user.setPassword("encodedpassword" + (i + 1));
            user.setEmail("test" + (i + 1) + "@example.com");
            user.setFullName("Test User" + (i + 1));
            users.add(user);
        }

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAllUsers();
        assertEquals(users.size(), result.size());
    }

    @Test
    // Kiểm tra changePassword
    void testChangePassword() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("$2a$10$123456789012345678901u12345678901234567890123456789012"); // encoded "password"

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$123456789012345678901u12345678901234567890123456789012");

        boolean result = userService.changePassword("testuser", "password", "newpassword");
        assertTrue(result);

        verify(userRepository).save(any(User.class));
    }
}