package com.productcatalogservice.controllers;

import com.productcatalogservice.dtos.ExceptionResponseDto;
import com.productcatalogservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleException(Exception exception) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }
}
