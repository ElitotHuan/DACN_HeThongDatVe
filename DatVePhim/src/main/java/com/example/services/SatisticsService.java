package com.example.services;

import com.example.dto.SatisticDTO;
import com.example.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatisticsService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<SatisticDTO> getTotalFromAllBranch() { return ticketRepository.getTotalOfBranch(); }

}
