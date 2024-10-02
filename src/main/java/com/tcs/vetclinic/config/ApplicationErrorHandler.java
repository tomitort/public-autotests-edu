package com.tcs.vetclinic.config;

import com.tcs.vetclinic.service.PersonNotExistError;
import com.tcs.vetclinic.service.PersonNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationErrorHandler {

    @ExceptionHandler()
    public ResponseEntity handlePersonNotFound(PersonNotFoundError e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler()
    public ResponseEntity handleException(PersonNotExistError e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
