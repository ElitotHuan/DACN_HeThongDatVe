package com.example.controllers;

import com.example.dto.FoodDTO;
import com.example.dto.MovieDTO;
import com.example.models.Ticket;
import com.example.dto.TicketDTO;
import com.example.services.FoodService;
import com.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/api/addTicket")
    public String addTicket(@RequestBody TicketDTO ticketDTO) {
        System.out.println(ticketDTO.toString());
        Boolean ad = ticketService.addTicket(ticketDTO);
        return "success";
    }
    @PutMapping(value = "/api/updateTicket/{id}")
    public String updateMovie(@PathVariable("id") int id, @RequestBody TicketDTO ticketDTO) {
        Boolean up = ticketService.updateMovie(ticketDTO, id);
        return "success";
    }

    @RequestMapping(value = "/api/deleteTicket/{id}", method = RequestMethod.GET)
    public String deleteTicket(@PathVariable("id") int id) {
        Boolean del = ticketService.deleteTicket(id);
        return "success";
    }
    @PostMapping("/ticket")
    public ModelAndView handleTicketRequest(@RequestParam("movie") String movie, @RequestParam("startdate") String startDate,@RequestParam("starttime") String startTime,@RequestParam("price") String price) {
        ModelAndView mav = new ModelAndView("client/ticket");
        mav.addObject("movie", movie);
        mav.addObject("startdate", startDate);
        mav.addObject("starttime", startTime);
        mav.addObject("price", price);
        return mav;
    }


    @PostMapping("/payment")
    public ModelAndView handleTicketSuccess(@RequestParam("movie") String movie,
                                            @RequestParam("count") int count,
                                            @RequestParam("price") double price,
                                            @RequestParam("seating") String seating,
                                            @RequestParam("startdate") String startdate,
                                            @RequestParam("starttime") String starttime
    ) {
        // Xử lý thông tin thanh toán ở đây
        ModelAndView mav = new ModelAndView("client/payment");
        // Truyền dữ liệu cho trang thanh toán
        mav.addObject("movie", movie);
        mav.addObject("count", count);
        mav.addObject("price", price);
        mav.addObject("seating", seating);
        mav.addObject("startdate", startdate);
        mav.addObject("starttime", starttime);
        return mav;
    }

}