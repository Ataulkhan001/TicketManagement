package com.ticketmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String description;
	
	private String category;
	
	private boolean approval=false;

	private Date createdAt = new Date(System.currentTimeMillis());

	private Date status_changedAt;

	public Ticket() {
		
	}
	
	public Ticket(Long id, String description, String category) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
	}
	
	public Ticket(String description, String category) {
		super();
		this.description = description;
		this.category = category;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getStatus_changedAt() {
		return status_changedAt;
	}

	public void setStatus_changedAt(Date status_changedAt) {
		this.status_changedAt = status_changedAt;
	}
}
