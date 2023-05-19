package com.example.repositories;

import com.example.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface TicketRepository extends JpaRepository<Ticket, Integer> {
        // Các phương thức truy vấn hoặc tìm kiếm dữ liệu
        // ...

    @Query(value = "SELECT name from Ticket", nativeQuery = true)
    List<String> getNames();

        List<Ticket> findByUserId(String userId);
    }

