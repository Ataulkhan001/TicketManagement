package com.ticketmanagement.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ticketmanagement.model.Ticket;
import com.ticketmanagement.repository.TicketRepo;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	TicketRepo ticketRepo;
	
	@PostMapping("/create")
	public Ticket createTicket(@RequestBody Ticket ticket)
	{
		return ticketRepo.save(ticket);
		
	}
	
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

}
