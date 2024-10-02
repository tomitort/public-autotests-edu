package com.tcs.vetclinic.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PersonNotExistError extends RuntimeException {
    public PersonNotExistError(String message) {
        super(message);
    }

}
