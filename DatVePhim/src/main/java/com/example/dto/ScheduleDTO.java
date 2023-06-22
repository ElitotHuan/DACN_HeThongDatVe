package com.example.dto;

import com.example.models.Branch;
import com.example.models.Movie;
import com.example.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ScheduleDTO {
    private String startDate;
    private String startTime;
    private double price;
    private int movieId;
    private int branchId;
    private String roomName;
}
