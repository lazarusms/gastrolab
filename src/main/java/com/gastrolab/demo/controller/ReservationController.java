package com.gastrolab.demo.controller;

import com.gastrolab.demo.model.Reservation;
import com.gastrolab.demo.service.ReservationService;
import com.gastrolab.demo.service.impl.ReservationServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/confirm/{reservationId}")
    public ResponseEntity<String> confirmReservation(@PathVariable("reservationId") Long reservationId) {
        try {
            Reservation reservation = reservationService.getAllReservationsById(reservationId)
                    .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado " + reservationId));

            if ("CONFIRMADO".equals(reservation.getStatus())) {
                return ResponseEntity.badRequest().body("Este agendamento já está confirmado.");
            }

            reservationService.clientUpdateReservation(reservationId, reservation);
            return ResponseEntity.ok("Agendamento confirmado com sucesso.");

        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerReservation(@RequestBody Reservation reservation) {

        boolean isAvailable = reservationService.isDateTimeAvailable(reservation.getDate(), reservation.getTime());
        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data e hora indisponíveis");
        } else {
            reservationService.create(reservation);
          //  logger.info("teste -> "  + reservation.getDateTime());
          //  logger.info("teste -> "  + dateTime);
            return ResponseEntity.status(HttpStatus.OK).body("Agendamento criado!");
        }
    }


    @GetMapping("/info/{reservationId}")
    public ResponseEntity<List<Reservation>> findAllClientReservationById(@PathVariable("reservationId") Long reservationId) {
        List<Reservation> clientReservation = reservationService.findAllByClient(reservationId);
        logger.info("Resposta com todos os agendamentos do usuário");
        return ResponseEntity.ok(clientReservation);
    }

    @GetMapping("/info")
    public ResponseEntity<List<Reservation>> findAllClientReservation() {
        List<Reservation> clientAppointments = reservationService.getAllReservations();
        return ResponseEntity.ok(clientAppointments);
    }

    @DeleteMapping(path = "/delete/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long reservationId) {
        logger.info("Deletando agendamento pelo id do client");
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.ok("Você deleteu o agendamento de id: " + reservationId.toString());
    }

    @PutMapping(path = "/update/{reservationId}")
    public ResponseEntity<String> updatedReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestBody Reservation updatedReservation) {
        try {
                reservationService.clientUpdateReservation(reservationId, updatedReservation);
                logger.info("Agendamento atualizado");
                return ResponseEntity.ok("Agendamento atualizado com sucesso!");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O agendamento com o ID " + reservationId + " não existe.");
        }
    }

}


