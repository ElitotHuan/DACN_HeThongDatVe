package com.example.repositories;

import com.example.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room , Integer> {

    @Query(value = "select * from Room where Room.name=:name AND Room.branch_id=:id", nativeQuery = true)
    Room searchByName(@Param("name") String name, @Param("id") int branchId);
}
