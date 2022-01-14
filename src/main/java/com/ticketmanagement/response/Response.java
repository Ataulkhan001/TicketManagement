package com.ticketmanagement.response;

import java.util.Date;

public class Response {

    String description;
    String category;
    Date createdAt;


    public Response() {
    }

    public Response(String description, String category, Date createdAt) {
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
