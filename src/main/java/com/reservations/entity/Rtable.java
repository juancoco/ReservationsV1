package com.reservations.entity;


import javax.persistence.Entity;
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
   private Integer tableNumber;
   
   private Integer numberOfSeats;
}
