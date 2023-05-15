package com.example.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int capacity;
    private double totalArea;

    @ManyToOne
    @JoinColumn(nullable = false, name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;


}
