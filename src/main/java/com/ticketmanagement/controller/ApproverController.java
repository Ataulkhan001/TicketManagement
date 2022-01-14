package com.ticketmanagement.controller;


import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.dao.TicketRepo;
import com.ticketmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/approver")
public class ApproverController {


    @Autowired
    TicketService ticketService;

    @GetMapping("/get")
    public ResponseEntity<?> findTicketById(@RequestParam Long id) throws Exception {

        ResponseEntity<?> response = ticketService.getTicketById(id);
        return response;
    }

    @GetMapping("/getByCat")
    public ArrayList<?> findTicketsByCategory(@RequestParam String category)
    {
        ArrayList<?> tickList = ticketService.getTicketsByCategory(category);
        return tickList;

    }

    @GetMapping("/get/ticketsForApproval")
    public ArrayList<?> getTicketsForApproval()
    {
        return ticketService.getTicketsForApproval();
    }

    @GetMapping("/get/ApprovedTickets")
    public ArrayList<?> getApprovedTickets()
    {
        return ticketService.getApprovedTickets();
    }


    @PutMapping("/ticket/approve")
    public ResponseEntity<?> approveTicket(@RequestParam Long id)
    {
        return ticketService.approveTicket(id);
    }

    @PutMapping("/ticket/reject")
    public ResponseEntity<?> rejectTicket(@RequestParam Long id)
    {
        return ticketService.rejectTicket(id);
    }

}
