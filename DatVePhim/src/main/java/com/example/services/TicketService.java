package com.example.services;

import com.example.dto.MovieDTO;
import com.example.dto.TicketDTO;
import com.example.models.Movie;
import com.example.models.Ticket;
import com.example.models.User;
import com.example.repositories.TicketRepository;
import com.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }
    public Boolean saveTicket(TicketDTO ticketDTO) {
        Ticket newTicket = new Ticket();
        setData(ticketDTO, newTicket);
        ticketRepository.save(newTicket);
        return true;
    }
    private void setData(TicketDTO ticketDTO, Ticket newTicket) {
        newTicket.setMovieName(ticketDTO.getMovieName());
        newTicket.setSeating(ticketDTO.getSeating());
        newTicket.setStartDate(ticketDTO.getStartDate());
        newTicket.setStartTime(ticketDTO.getStartTime());
        newTicket.setTotal(ticketDTO.getTotal());
        newTicket.setUsername(ticketDTO.getUsername());
    }
}
