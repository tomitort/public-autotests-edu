package com.tcs.vetclinic.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundError extends RuntimeException {
    public PersonNotFoundError(String message) {
        super(message);
    }

}
