package com.gastrolab.demo.service.impl;

import com.gastrolab.demo.model.Reservation;
import com.gastrolab.demo.repository.ReservationRepository;
import com.gastrolab.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;


    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getAllReservationsById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation clientUpdateReservation(Long appointmentId, Reservation updatedAppointment) {
        return null;
    }

    @Override
    public void deleteReservationById(Long id) {

    }

    @Override
    public List<Reservation> findAllClientReservationByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    public List<Reservation> findAllByClient(Long clientId) {
        List<Reservation> appointments = reservationRepository.findAll();
        return appointments.stream()
                .filter(existing -> existing.getId().equals(clientId)).collect(Collectors.toList());

    }

    @Override
    public boolean isDateTimeAvailable(String date, String time) {
        return reservationRepository.findAll().stream().noneMatch(reservation -> reservation.getDate().equals(date) && reservation.getTime().equals(time));
    }
}
