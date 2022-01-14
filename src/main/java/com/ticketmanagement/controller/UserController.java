package com.ticketmanagement.controller;

import java.util.ArrayList;

import com.ticketmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.dao.TicketRepo;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	TicketRepo ticketRepo;

	@Autowired
	TicketService ticketService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) throws Exception
	{
		return ticketService.createTicket(ticket);
	}
	
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

}
