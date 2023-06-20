package com.example.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private int id;

    private String name;
    private int capacity;
    private double totalArea;
    private int branchId;
}
