package com.example.models;

import com.example.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Table(name = "ticket")
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Đặt tên cột trong cơ sở dữ liệu mà Ticket tham chiếu đến
    private User user;

    private String movieName;
    private String seating;
    private LocalDate startDate;
    private LocalTime startTime;
    private Double total;

    // Các getter và setter khác
}
