package com.example.repositories;

import com.example.dto.SatisticDTO;
import com.example.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

   @Query(value = "SELECT branch.name , sum(t.total) as total, (SELECT COUNT(e.branch_id) from Employee e inner join Branch b where e.branch_id = b.id and b.name = branch.name) as employee  FROM Ticket t " +
           "inner join Schedule s " +
           "inner join Branch branch where t.schedule_id = s.id and  s.branch_id = branch.id group by branch.name" , nativeQuery = true)
   public List<SatisticDTO> getTotalOfBranch();
}
