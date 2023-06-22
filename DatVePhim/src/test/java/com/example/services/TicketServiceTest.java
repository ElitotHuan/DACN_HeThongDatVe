package com.example.services;

import com.example.dto.TicketDTO;
import com.example.models.Schedule;
import com.example.models.Ticket;
import com.example.repositories.ScheduleRepository;
import com.example.repositories.TicketRepository;
import com.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testGetAll() {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Ticket ticket = new Ticket();
            ticket.setId(i + 1);
            ticket.setSeating("Seating" + (i + 1));
            ticket.setTotal(10.0 * (i + 1));
            ticket.setUsername("Username" + (i + 1));
            tickets.add(ticket);
        }

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAll();
        assertEquals(tickets.size(), result.size());
    }

    @Test
    void testSaveTicket() {
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setPrice(10.0);
        schedule.setStartDate(LocalDate.now());
        schedule.setStartTime(LocalTime.now());

        when(scheduleRepository.getReferenceById(1)).thenReturn(schedule);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSeating("New Seating");
        ticketDTO.setTotal(10.0);
        ticketDTO.setUsername("New Username");
        ticketDTO.setScheduleId(1);

        Ticket result = ticketService.saveTicket(ticketDTO);
        assertNotNull(result);

        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void testUpdateTicket() {
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setPrice(10.0);
        schedule.setStartDate(LocalDate.now());
        schedule.setStartTime(LocalTime.now());

        when(scheduleRepository.getReferenceById(1)).thenReturn(schedule);

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setSeating("Old Seating");
        ticket.setTotal(10.0);
        ticket.setUsername("Old Username");
        ticket.setSchedule(schedule);

        when(ticketRepository.getReferenceById(1)).thenReturn(ticket);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSeating("Updated Seating");
        ticketDTO.setTotal(20.0);
        ticketDTO.setUsername("Updated Username");
        ticketDTO.setScheduleId(1);

        boolean result = ticketService.updateTicket(ticketDTO, 1);
        assertTrue(result);

        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void testDeleteTicket() {
        boolean result = ticketService.deleteTicket(1);
        assertTrue(result);

        verify(ticketRepository).deleteById(1);
    }
}