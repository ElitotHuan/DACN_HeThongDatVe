package com.example.dto;

import com.example.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String fullName;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    private String username;
    private String birthday;
    private String address;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    private String roles;

}
