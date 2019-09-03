package com.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class TableAlreadyCreatedException extends RuntimeException {
   
   public TableAlreadyCreatedException(String arg0) {
      super(arg0);
   }

}
