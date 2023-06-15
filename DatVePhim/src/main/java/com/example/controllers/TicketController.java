package com.example.controllers;


import com.example.models.Ticket;
import com.example.dto.TicketDTO;
import com.example.services.PaypalService;
import com.example.services.TicketService;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {


    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/pay")
    public String pay(HttpSession session , @RequestBody TicketDTO ticket) throws PayPalRESTException {
        String cancelURL = "http://localhost:8080" + "/" + "pay/cancel";
        String successURL = "http://localhost:8080" + "/" + "saveTicket";

        //Tạo thanh toán từ dữ liệu của hóa đơn
        Payment payment = paypalService.createPayment(ticket.getTotal(), "USD",
                "payemnt description", cancelURL, successURL);

        for (Links links : payment.getLinks()) {
            //Sau khi tạo thanh toán nếu không xảy lỗi, tự động chuyển hướng sang giao diện paypal
            if (links.getRel().equals("approval_url")) {
                session.setAttribute("ticketData", ticket);
                return links.getHref();
            }
        }

        return "redirect:/";
    }

    @GetMapping("/saveTicket")
    public ModelAndView saveTicket(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession session) throws PayPalRESTException {
        Payment payment = paypalService.executePayment(paymentId, payerId);
        if (payment.getState().equals("approved")) {
            // Lưu dữ liệu vé vào cơ sở dữ liệu
            ticketService.saveTicket((TicketDTO) session.getAttribute("ticketData"));

            //Gửi thông tin vé tới người dùng
            ModelAndView success = new ModelAndView("client/pay_success");
            success.addObject("ticketInfo" ,session.getAttribute("ticketData"));
            session.removeAttribute("ticketData");

            return success;
        }
        return new ModelAndView("redirect:/");
    }


    @GetMapping("/pay/cancel")
    public ModelAndView cancelPayment() {
        return new ModelAndView("redirect:/");
    }


    @PostMapping("/ticket")
    public ModelAndView handleTicketRequest(@RequestParam("movie") String movie, @RequestParam("startdate") String startDate, @RequestParam("starttime") String startTime, @RequestParam("branch") String branchName, @RequestParam("room") String roomName, @RequestParam("price") String price) {
        ModelAndView mav = new ModelAndView("client/ticket");
        mav.addObject("movie", movie);
        mav.addObject("startdate", startDate);
        mav.addObject("starttime", startTime);
        mav.addObject("branch", branchName);
        mav.addObject("room", roomName);
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
                                            @RequestParam("starttime") String starttime,
                                            @RequestParam("branch") String branchName,
                                            @RequestParam("room") String roomName

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
        mav.addObject("branch", branchName);
        mav.addObject("room", roomName);

        return mav;
    }


    @PutMapping("/api/updateTicket/{id}")
    public String updateTicket(@PathVariable("id") int id, @RequestBody TicketDTO ticketDTO) {
        Boolean up = ticketService.updateTicket(ticketDTO, id);
        return "success";
    }


    @GetMapping("/api/deleteTicket/{id}")
    public ModelAndView deleteTicket(@PathVariable("id") int id) {
        Boolean del = ticketService.deleteTicket(id);
        return new ModelAndView("redirect:/api/manage_ticket");
    }

}