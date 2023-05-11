package com.example.repositories;

import com.example.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room , Integer> {

    Room searchByName(String name);
}
