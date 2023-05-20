package com.example.models;

import com.example.models.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket")
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

    @Column(name = "total")
    private Double total;


    public void setMovieName(String movieName) {
        this.movieName=movieName;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate= startDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime=startTime;
    }

    public void setTotal(Double total) {
        this.total=total;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getMovieName() {
        return movieName;
    }
}
