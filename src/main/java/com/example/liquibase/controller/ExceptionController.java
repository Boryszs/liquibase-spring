package com.example.liquibase.controller;

import com.example.liquibase.dto.MessageDto;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {NotFoundException.class, Exception.class})
    public ResponseEntity<MessageDto> resourceNotFoundException(NotFoundException notFoundException, Exception exception) {
        return new ResponseEntity<MessageDto>(new MessageDto(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
