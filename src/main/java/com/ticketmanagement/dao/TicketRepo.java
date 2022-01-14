package com.ticketmanagement.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketmanagement.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {
	
	@Query(value="Select t from Ticket t where t.approval=0") 
	ArrayList<Ticket> getApprovalRequest();
	
	@Query(value="Select t from Ticket t where t.approval=1") 
	ArrayList<Ticket> getApprovedTickets();

	@Query(value="Select t from Ticket t where t.category= :cat")
	ArrayList<Ticket> findByCategory(@Param("cat") String category);

}
