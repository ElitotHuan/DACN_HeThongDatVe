package com.example.dto;

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
    private String password;
    private String username;
    private String email;
    private Set<String> roles;
}
