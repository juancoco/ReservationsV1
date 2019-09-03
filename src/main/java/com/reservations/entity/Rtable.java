package com.reservations.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rtable {
   
   @Id
   @GeneratedValue
   private Integer id;
   
   private String tableNumber;
   
   private Integer numberOfSeats;
}
