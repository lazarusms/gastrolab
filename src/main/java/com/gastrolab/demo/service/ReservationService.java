package com.gastrolab.demo.service;

import com.gastrolab.demo.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation create(Reservation appointment);
    List<Reservation> getAllReservations();
    Optional<Reservation> getAllReservationsById(Long id);
    Reservation clientUpdateReservation(Long appointmentId, Reservation updatedAppointment);
    void deleteReservationById(Long id);
    List<Reservation> findAllClientReservationByDate(LocalDateTime startDate, LocalDateTime endDate);
    boolean isDateTimeAvailable(LocalDateTime dateTime);


}
