package com.example.services;

import com.example.dto.FoodDTO;
import com.example.dto.MovieDTO;
import com.example.dto.TicketDTO;
import com.example.models.Food;
import com.example.models.Movie;
import com.example.models.Ticket;
import com.example.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUserId(String userId) {
        return ticketRepository.findByUserId(userId);
    }

    private void setData(TicketDTO ticket, Ticket newTicket) {
        newTicket.setUser(ticket.getUser());
        newTicket.setMovieName(ticket.getMovieName());
        newTicket.setStartDate(ticket.getStartDate());
        newTicket.setStartTime(ticket.getStartTime());
        newTicket.setSeating(ticket.getSeating());
        newTicket.setTotal(ticket.getTotal());

    }
    public Boolean addTicket(TicketDTO ticket) {
        Ticket newTicket = new Ticket();
        setData(ticket, newTicket);
        ticketRepository.save(newTicket);
        return true;
    }

    public boolean deleteTicket(int id) {
        ticketRepository.deleteById(id);
        return true;
    }
    public boolean updateMovie(TicketDTO ticket, int id) {
        Ticket m = ticketRepository.getReferenceById(id);
        setData(ticket, m);
        ticketRepository.save(m);
        return true;
    }
}