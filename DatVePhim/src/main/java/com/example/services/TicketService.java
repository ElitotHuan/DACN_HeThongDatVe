package com.example.services;

import com.example.dto.TicketDTO;
import com.example.models.Ticket;
import com.example.repositories.ScheduleRepository;
import com.example.repositories.TicketRepository;
import com.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private final ScheduleRepository scheduleRepository;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public Ticket saveTicket(TicketDTO ticketDTO) {
        Ticket newTicket = new Ticket();
        setData(ticketDTO, newTicket);
        ticketRepository.save(newTicket);
        return newTicket;
    }

    public Boolean updateTicket(TicketDTO ticketDTO, int id) {
        Ticket t = ticketRepository.getReferenceById(id);
        setData(ticketDTO, t);
        ticketRepository.save(t);
        return true;
    }


    public Boolean deleteTicket(int id) {
        ticketRepository.deleteById(id);
        return true;
    }

    private void setData(TicketDTO ticketDTO, Ticket newTicket) {
        newTicket.setSeating(ticketDTO.getSeating());
        newTicket.setSchedule(scheduleRepository.getReferenceById(ticketDTO.getScheduleId()));
        newTicket.setTotal(ticketDTO.getTotal());
        newTicket.setUser(userRepository.findByUsername(ticketDTO.getUsername()));
    }
}
