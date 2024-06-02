package com.gastrolab.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
@SequenceGenerator(name = "reservation_sequence", sequenceName = "SQ_APPOINT", allocationSize = 1)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    private Long id;
    @NotBlank(message = "Customer name is required")
    private String customerName;
    private int numberOfPeople;
    private String date;
    private String time;
    @NotBlank(message = "Contact information is required")
    private String contactInfo;
    private String status;

    public Reservation() {
    }

    public Reservation(Long id, String customerName, int numberOfPeople, String date, String time, String contactInfo, String status) {
        this.id = id;
        this.customerName = customerName;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.time = time;
        this.contactInfo = contactInfo;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
