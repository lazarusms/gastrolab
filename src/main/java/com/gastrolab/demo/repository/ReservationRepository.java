package com.gastrolab.demo.repository;

import com.gastrolab.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
   // @Query("SELECT a FROM Reservation a WHERE a.dateTime BETWEEN :startDate AND :endDate")
   // List<Reservation> findAllClientReservationByDate(
   ////        @Param("startDate") LocalDateTime startDate,
    //          @Param("endDate") LocalDateTime endDate
    //   );

    //   @Query("SELECT a FROM Reservation a WHERE a.dateTime = :dateTime")
    //  Optional<Reservation> findReservationByDateTime(@Param("dateTime") LocalDateTime dateTime);




}
