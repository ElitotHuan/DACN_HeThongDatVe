package com.example.repositories;

import com.example.dto.SatisticDTO;
import com.example.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

   @Query(value = "SELECT b.name , sum(t.total) as total FROM Ticket t " +
           "inner join Schedule s " +
           "inner join Branch b where t.schedule_id = s.id and  s.branch_id = b.id group by b.name" , nativeQuery = true)
   public List<SatisticDTO> getTotalOfBranch();
}
