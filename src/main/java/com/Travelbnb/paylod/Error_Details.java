package com.Travelbnb.paylod;

import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class Error_Details {
    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getRequest() {
        return request;
    }

    private String message;
    private Date date;
    private String request;

    public Error_Details(String message, Date date, String request) {
        this.message = message;
        this.date = date;
        this.request = request;
    }
}
