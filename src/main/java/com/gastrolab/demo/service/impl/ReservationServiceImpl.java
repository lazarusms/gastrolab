package com.gastrolab.demo.service.impl;

import com.gastrolab.demo.model.Reservation;
import com.gastrolab.demo.repository.ReservationRepository;
import com.gastrolab.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    public Reservation clientUpdateReservation(Long reservationId, Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalStateException("O agendamento com o id " + reservationId + " não existe"));
        updateStatus(reservation, updatedReservation.getStatus());
        reservationRepository.save(reservation);
        return reservation;
    }
    public void updateStatus(Reservation reservation, String newStatus) {
        if (newStatus != null && !Objects.equals(reservation.getStatus(), newStatus)) {
            reservation.setStatus(newStatus);
        }
    }

    //Não implementado - Sem necessidade para o projeto (Usar STATUS = CANCELADO)
    @Override
    public void deleteReservationById(Long id) {

    }
    //Não implementado - Sem necessidade para o projeto
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
