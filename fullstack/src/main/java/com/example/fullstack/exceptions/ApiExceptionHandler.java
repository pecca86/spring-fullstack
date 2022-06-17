package com.example.fullstack.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice // Makes available for all modules
public class ApiExceptionHandler {

    // For bad requests
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handeApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    // When student resource not found
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handeApiRequestException(StudentNotFoundException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()

        );

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
