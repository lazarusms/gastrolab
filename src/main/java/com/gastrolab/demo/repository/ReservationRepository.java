package com.gastrolab.demo.repository;

import com.gastrolab.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Query para buscar todas as reservas de um cliente por data
    @Query("SELECT a FROM Reservation a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Reservation> findAllClientReservationByDate(
         @Param("startDate") String startDate,
         @Param("endDate") String endDate
      );

     @Query("SELECT a FROM Reservation a WHERE a.date = :dateTime")
    Optional<Reservation> findReservationByDateTime(@Param("dateTime") String dateTime);




}
