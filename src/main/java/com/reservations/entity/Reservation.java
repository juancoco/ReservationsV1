package com.reservations.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
   
   @Id
   @GeneratedValue
   private Integer id;
   
   private Integer tableNumber;
   
   private Integer idClient;
   
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date reservationDateFrom;
   
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date reservationDateTo;

}
