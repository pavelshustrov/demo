package com.example.demo;


import com.example.demo.exceptions.IllegalHeaderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class BadInputControllerAdvice {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IllegalHeaderException.class})
    public void handleException(Exception e) {
        log.error("Spoiled message received", e.getMessage());
    }
}