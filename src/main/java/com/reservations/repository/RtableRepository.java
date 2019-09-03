package com.reservations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reservations.entity.Rtable;

@Repository
public interface RtableRepository extends JpaRepository<Rtable, Integer> {
   
   @Query("SELECT t FROM Rtable t WHERE t.tableNumber = :tableNumber")
   Optional<Rtable> verifyTableExists(@Param("tableNumber") final String tableNumber);
   
   @Query("DELETE Rtable t WHERE t.tableNumber = :tableNumber")
   Optional<Rtable> deleteByTableNumber(@Param("tableNumber") final String tableNumber);

}
