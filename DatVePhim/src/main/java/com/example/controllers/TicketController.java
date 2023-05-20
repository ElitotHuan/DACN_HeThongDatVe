package com.example.controllers;

import com.example.models.Ticket;
import com.example.dto.TicketDTO;
import com.example.models.User;
import com.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/saveTicket")
    public ResponseEntity<String> saveTicket(@RequestBody TicketDTO ticketDTO) {
        // Chuyển đổi từ TicketDTO thành Ticket entity
        // Lưu dữ liệu vé vào cơ sở dữ liệu
        ticketService.saveTicket(ticketDTO);
        // Trả về thông báo thành công
        return ResponseEntity.ok("Dữ liệu vé đã được lưu thành công");
    }

    @PostMapping("/ticket")
    public ModelAndView handleTicketRequest(@RequestParam("movie") String movie, @RequestParam("startdate") String startDate, @RequestParam("starttime") String startTime, @RequestParam("price") String price) {
        ModelAndView mav = new ModelAndView("client/ticket");
        mav.addObject("movie", movie);
        mav.addObject("startdate", startDate);
        mav.addObject("starttime", startTime);
        mav.addObject("price", price);

        List<Ticket> tickets = ticketService.getAll();
        List<String> seatingList = new ArrayList<>();

        for (Ticket ticket : tickets) {
            String seating = ticket.getSeating();
            String ticketMovie = ticket.getMovieName(); // Lấy tên phim của vé

            if (ticketMovie.equals(movie)) { // Kiểm tra tên phim của vé có giống với movieName không
                if (seating.contains(",")) {
                    String[] seatingArray = seating.split(","); // Tách chuỗi thành mảng các phần tử

                    // Thêm từng phần tử vào seatingList
                    for (String seatingElement : seatingArray) {
                        seatingList.add(seatingElement.trim()); // Xóa khoảng trắng thừa
                    }
                } else {
                    seatingList.add(seating); // Thêm seating nguyên thủy vào seatingList
                }
            }
        }

        System.out.println(seatingList.toString());
        mav.addObject("seatingList", seatingList);
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