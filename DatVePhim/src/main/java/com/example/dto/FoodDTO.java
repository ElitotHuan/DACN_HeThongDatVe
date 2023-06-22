package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodDTO {
    private String name;
    private String urlImage;
    private  int price;
}


