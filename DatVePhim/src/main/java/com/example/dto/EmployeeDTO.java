package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private String fullName;
    private String birthday;
    private String email;
    private String address;
    private String position;
    private int branchId;
}
