package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchDTO {
    private String name;
    private String address;
    private String phoneNo;
}
