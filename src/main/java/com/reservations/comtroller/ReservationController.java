package com.reservations.comtroller;

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

@RestController
public class ReservationController {

   @Autowired
   private ReservationRepository reservationRepository;

   @Autowired
   private RtableRepository tableRepository;

   @GetMapping("/reservations")
   private List<Reservation> retrieveAllReservations() {
      return reservationRepository.findAll();
   }

   @PostMapping("/reservations")
   private ResponseEntity<Object> saveReservation(@RequestBody Reservation reservation) {
      if (isTableUnavailable(reservation))
         throw new TableUnavailableException("Table number: " + reservation.getTableNumber());

      reservationRepository.save(reservation);
      return ResponseEntity.ok().build();
   }

   @PostMapping("/reservations/multiple")
   private ResponseEntity<Object> saveReservationMultipleTables(@RequestBody List<Reservation> reservations) {
      for (Reservation reservation : reservations) {
         if (isTableUnavailable(reservation))
            throw new TableUnavailableException("Table number: " + reservation.getTableNumber());
      }

      reservationRepository.saveAll(reservations);
      return ResponseEntity.ok().build();
   }

   private boolean isTableUnavailable(Reservation reservation) {
      if (!tableRepository.verifyTableExists(reservation.getTableNumber()).isPresent()
            || reservationRepository.searchReservedTableInPeriodTime(reservation.getTableNumber(),
                  reservation.getReservationDateFrom(), reservation.getReservationDateTo()).isPresent()) {
         return true;
      } else {
         return false;
      }
   }

}
