package com.ticketmanagement.controller;


import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/approver")
public class ApproverController {

    @Autowired
    TicketRepo ticketRepo;

    @GetMapping("/get")
    public Optional<Ticket> findTicketById(@RequestParam Long id)
    {
        return ticketRepo.findById(id);
    }

    @GetMapping("/getByCat")
    public ArrayList<Ticket> findTicktesByCategory(@RequestParam String category)
    {
        return ticketRepo.findByCategory(category);
    }

    @GetMapping("/get/ticketsForApproval")
    public ArrayList<Ticket> getTicketsForApproval()
    {
        return ticketRepo.getApprovalRequest();
    }

    @GetMapping("/get/ApprovedTickets")
    public ArrayList<Ticket> getApprovedTickets()
    {
        return ticketRepo.getApprovedTickets();
    }


    @PutMapping("/ticket/approve")
    public Ticket approveTicket(@RequestParam Long id)
    {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        Ticket tkt = ticket.get();
        tkt.setApproval(true);
        return ticketRepo.save(tkt);
    }

    @PutMapping("/ticket/reject")
    public Ticket rejectTicket(@RequestParam Long id)
    {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        Ticket tkt = ticket.get();
        tkt.setApproval(false);
        return ticketRepo.save(tkt);
    }

}
