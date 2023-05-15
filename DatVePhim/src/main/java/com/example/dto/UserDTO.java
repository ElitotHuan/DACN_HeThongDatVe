package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String fullName;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    private String username;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
}
