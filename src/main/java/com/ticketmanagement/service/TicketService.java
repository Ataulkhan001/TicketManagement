package com.ticketmanagement.service;

import com.ticketmanagement.dao.TicketRepo;
import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.response.ApiResponse;
import com.ticketmanagement.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepo ticketRepo;


    public ResponseEntity<?> createTicket(Ticket ticket)
    {
        try {
            return new ResponseEntity<Ticket>(ticketRepo.save(ticket),HttpStatus.OK);
        }
        catch (Exception e)
        {
            ApiResponse res = new ApiResponse();
            res.setMessage("Ticket cannot be created, Please provide right values");
            ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.BAD_REQUEST);
            return response;
        }

    }

    public ResponseEntity<?> getTicketById(Long id)
    {
        try {
            Optional<Ticket> tkt = ticketRepo.findById(id);
            Ticket ticket = tkt.get();
            Response res = new Response();
            res.setCategory(ticket.getCategory());
            res.setDescription(ticket.getDescription());
            res.setCreatedAt(ticket.getCreatedAt());
            ResponseEntity<Response> response = new ResponseEntity<Response>(res, HttpStatus.OK);
            return response;
        }
        catch (Exception e)
        {
            ApiResponse res = new ApiResponse();
            res.setMessage("Ticket Not Found");
            ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
            return response;
        }
    }

    public ArrayList<?> getTicketsByCategory(String category) {
        ArrayList<Ticket> ticketList = ticketRepo.findByCategory(category);
        if (ticketList.size() > 0) {
            return ticketList;
        } else {
            ArrayList<ApiResponse> apiResponses = new ArrayList<>();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("No Any Ticket Found Of This Category");
            apiResponses.add(apiResponse);
            return apiResponses;
        }
    }

    public ArrayList<?> getTicketsForApproval() {
        ArrayList<Ticket> ticketList = ticketRepo.getApprovalRequest();
        if (ticketList.size() > 0) {
            return ticketList;
        } else {
            ArrayList<ApiResponse> apiResponses = new ArrayList<>();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("No Any Request Found For Ticket Approval");
            apiResponses.add(apiResponse);
            return apiResponses;
        }
    }

    public ArrayList<?> getApprovedTickets()
    {
        ArrayList<Ticket> tickets = ticketRepo.getApprovedTickets();
        if (tickets.size() > 0) {
            return tickets;
        } else {
            ArrayList<ApiResponse> apiResponses = new ArrayList<>();
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("No Any Approved Ticket Found");
            apiResponses.add(apiResponse);
            return apiResponses;
        }

    }

    public ResponseEntity<?> approveTicket(Long id)
    {
        try {
            Optional<Ticket> ticket = ticketRepo.findById(id);
            Ticket ticket1 = ticket.get();
            if(ticket1.isApproval())
            {
                ApiResponse res = new ApiResponse();
                res.setMessage("Ticket is already approved");
                ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
                return response;
            }
            else {
                ticket1.setApproval(true);
                ticket1.setStatus_changedAt(new Date(System.currentTimeMillis()));
                ResponseEntity<Ticket> response = new ResponseEntity<Ticket>(ticketRepo.save(ticket1), HttpStatus.OK);
                return response;
            }
        }
        catch (Exception e)
        {
            ApiResponse res = new ApiResponse();
            res.setMessage("Ticket Not Found");
            ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
            return response;
        }
    }

    public ResponseEntity<?> rejectTicket(Long id)
    {
        try {
            Optional<Ticket> ticket = ticketRepo.findById(id);
            Ticket ticket1 = ticket.get();
            if(!ticket1.isApproval())
            {
                ApiResponse res = new ApiResponse();
                res.setMessage("Ticket is already Rejected");
                ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
                return response;
            }
            else {
                ticket1.setApproval(false);
                ticket1.setStatus_changedAt(new Date(System.currentTimeMillis()));
                ResponseEntity<Ticket> response = new ResponseEntity<Ticket>(ticketRepo.save(ticket1), HttpStatus.OK);
                return response;
            }
        }
        catch (Exception e)
        {
            ApiResponse res = new ApiResponse();
            res.setMessage("Ticket Not Found");
            ResponseEntity<ApiResponse> response = new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
            return response;
        }
    }
}
