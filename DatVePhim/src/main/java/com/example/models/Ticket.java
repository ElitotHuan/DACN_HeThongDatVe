package com.example.models;

import com.example.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;
    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "seating")
    private String seating;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "branch_name")
    private String branch;

    @Column(name = "room_name")
    private String room;

    @Column(name = "total")
    private Double total;



}
