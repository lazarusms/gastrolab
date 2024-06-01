package com.gastrolab.demo;

import com.gastrolab.demo.model.Reservation;
import com.gastrolab.demo.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class InsertConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
        return args -> {
            Reservation reservation = new Reservation(
                    1L, // ID da reserva
                    "João", // Nome do cliente
                    4, // Número de pessoas
                    LocalDateTime.now(), // Data e hora da reserva (atual)
                    "joao@example.com", // Informações de contato
                    "Sem restrições especiais", // Solicitações especiais
                    "Confirmada" // Status da reserva
            );
            reservationRepository.save(reservation);

        };
    }
    }
