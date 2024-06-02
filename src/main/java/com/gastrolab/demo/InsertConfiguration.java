package com.gastrolab.demo;

import com.gastrolab.demo.model.Reservation;
import com.gastrolab.demo.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
        return args -> {
            Reservation reservation = new Reservation(
                    1L, // ID da reserva
                    "João", // Nome do cliente
                    4, // Número de pessoas
                    "16/04/2024", // Data da reserva (atual)
                    "16:30", // Hora da reserva (atual)
                    "joao@example.com", // Informações de contato
                    "PENDENTE" // Status da reserva
            );
            reservationRepository.save(reservation);
        };
    }
}
