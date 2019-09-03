package com.reservations.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reservations.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

   @Query("SELECT r FROM Reservation r WHERE r.tableNumber = :tableNumber")
   Optional<Reservation> searchReservedTable(@Param("tableNumber") final Integer tableNumber);

   @Query("SELECT r FROM Reservation r WHERE r.tableNumber = :tableNumber "
         + "AND (r.reservationDateFrom BETWEEN :reservationDateFrom AND :reservationDateTo "
         + "OR r.reservationDateTo BETWEEN :reservationDateFrom AND :reservationDateTo)")
   Optional<Reservation> searchReservedTableInPeriodTime(@Param("tableNumber") final String string, 
         @Param("reservationDateFrom") final Date reservationDateFrom, 
         @Param("reservationDateTo") final Date reservationDateTo);

}
