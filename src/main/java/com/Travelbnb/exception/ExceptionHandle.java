package com.Travelbnb.exception;


import com.Travelbnb.paylod.Error_Details;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(ExceptionNotFound.class)
    public ResponseEntity<Error_Details> getErrorDetails(ExceptionNotFound ex, WebRequest webRequest) {
        Error_Details err = new Error_Details(
                ex.getMessage(), new Date(), webRequest.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(java.lang.Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }

}
