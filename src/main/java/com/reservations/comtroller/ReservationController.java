package com.reservations.comtroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.entity.Reservation;
import com.reservations.exception.TableUnavailableException;
import com.reservations.repository.ReservationRepository;
import com.reservations.repository.RtableRepository;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class ReservationController {
   
   private static final String TABLE_UNAVAILABLE = "Table do not exist or is already reserved - ";
   private static final String TABLE_NUMBER = "Table number: ";

   @Autowired
   private ReservationRepository reservationRepository;

   @Autowired
   private RtableRepository tableRepository;

   @GetMapping("/reservations")
   private List<Reservation> retrieveAllReservations() {
      return reservationRepository.findAll();
   }

   @PostMapping("/reservations")
   private ResponseEntity<Object> saveReservationMultipleTables(@RequestBody Reservation reservation) {
      //Multiple table reservation
      if(reservation.getTableNumber().contains(",")) {
         String[] tablesToReserve = reservation.getTableNumber().split(",");
         for (String tableToReserve : tablesToReserve) {
            if (isTableUnavailable(tableToReserve, reservation.getReservationDateFrom(), reservation.getReservationDateTo())) {
               log.error(TABLE_UNAVAILABLE + reservation.getTableNumber());
               throw new TableUnavailableException(TABLE_NUMBER + reservation.getTableNumber());
            }
         }
      }else {//Single table reservation
         if (isTableUnavailable(reservation.getTableNumber(), reservation.getReservationDateFrom(), reservation.getReservationDateTo())) {
            log.error(TABLE_UNAVAILABLE + reservation.getTableNumber());
            throw new TableUnavailableException(TABLE_NUMBER + reservation.getTableNumber());
         }
      }

      reservationRepository.save(reservation);
      return ResponseEntity.ok().build();
   }

   private boolean isTableUnavailable(String tableNumber, Date reservationDateFrom, Date reservationDateTo) {
      if (!tableRepository.verifyTableExists(tableNumber).isPresent()
            || reservationRepository.searchReservedTableInPeriodTime(tableNumber,
                  reservationDateFrom, reservationDateTo).isPresent()) {
         return true;
      } else {
         return false;
      }
   }

}
