package com.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TableUnavailableException extends RuntimeException {

   public TableUnavailableException(String arg0) {
      super(arg0);
   }

}
