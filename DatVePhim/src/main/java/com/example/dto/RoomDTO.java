package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDTO {
    private int id;
    private String name;
    private int capacity;
    private double totalArea;
    private int branchId;
}
