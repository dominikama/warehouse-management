package com.stockmanagement.warehouse.adapter.controller;

import com.stockmanagement.warehouse.application.dto.ErrorDto;
import com.stockmanagement.warehouse.application.service.ExceptionHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ExceptionHandlingService exceptionHandlingService;

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ExceptionHandlingService exceptionHandlingService) {
        this.exceptionHandlingService = exceptionHandlingService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleAllExceptions(Exception ex, WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        ErrorDto errorDto = exceptionHandlingService.createErrorResponse(ex);
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getResponseCode()));
    }
}

