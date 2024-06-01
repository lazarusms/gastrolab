package com.gastrolab.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@SequenceGenerator(name = "appointment_sequence", sequenceName = "SQ_APPOINT", allocationSize = 1)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    private Long id;
    @NotBlank(message = "Customer name is required")
    private String customerName;
    private int numberOfPeople;
    @NotBlank(message = "Date and time is required")
    private LocalDateTime dateTime;
    @NotBlank(message = "Contact information is required")
    private String contactInfo;
    private String specialRequests;
    private String status;

    public Reservation() {
    }
    public Reservation(Long id, String customerName, int numberOfPeople, LocalDateTime dateTime, String contactInfo, String specialRequests, String status) {
        this.id = id;
        this.customerName = customerName;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
        this.contactInfo = contactInfo;
        this.specialRequests = specialRequests;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public String getSpecialRequests() {
        return specialRequests;
    }

    public String getDateTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
