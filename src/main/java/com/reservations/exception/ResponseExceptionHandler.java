package com.reservations.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
   
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
      ExceptionStructure exceptionStructure = new ExceptionStructure(new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(exceptionStructure, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(TableUnavailableException.class)
   public final ResponseEntity<Object> handleUserNotFoundExceptions(TableUnavailableException ex, WebRequest request){
      ExceptionStructure exceptionStructure = new ExceptionStructure(new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(exceptionStructure, HttpStatus.NOT_FOUND);
   }
   

}
