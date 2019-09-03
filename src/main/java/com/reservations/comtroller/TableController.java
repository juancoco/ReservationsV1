package com.reservations.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.entity.Rtable;
import com.reservations.exception.TableAlreadyCreatedException;
import com.reservations.repository.RtableRepository;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class TableController {
   
   private static final String TABLE_CREATED = "Table created: ";
   
   @Autowired
   private RtableRepository tableRepository;
   
   @GetMapping(path="/tables")
   private List<Rtable> retrieveAllTables() {
      return tableRepository.findAll();
   }
   
   @PostMapping(path="/tables")
   private ResponseEntity<Object> saveTable(@RequestBody Rtable table) {
      
      if(tableRepository.verifyTableExists(table.getTableNumber()).isPresent())
         throw new TableAlreadyCreatedException("Table number already created: "+ table.getTableNumber());
      
      tableRepository.save(table);
      log.info(TABLE_CREATED + table.getTableNumber());
      return ResponseEntity.ok().build();
   }
   
   @DeleteMapping(path="/tables/{tableNumber}")
   private void deleteTableByTableNumber(@PathVariable String tableNumber) {
      tableRepository.deleteByTableNumber(tableNumber);
   }
   
   @PutMapping(path="/tables/{id}")
   private void updateTableById(@RequestBody Rtable table) {
      tableRepository.save(table);
   }

}
