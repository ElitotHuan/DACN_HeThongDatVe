package com.example.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private String name;
    private String smallImageURl;
    private String description;
    private String largeImageURL;
    private String director;
    private String actors;
    private String categories;
    private String releaseDate;
    private int duration;
    private String language;
    private  int isShowing;
}
