package com.example.repositories;

import com.example.dto.SatisticDTO;
import com.example.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

   @Query(value = "SELECT Ticket.branch_name as name , SUM(Ticket.total) as total from Ticket group by Ticket.branch_name" , nativeQuery = true)
   public List<SatisticDTO> getTotalOfBranch();
}
