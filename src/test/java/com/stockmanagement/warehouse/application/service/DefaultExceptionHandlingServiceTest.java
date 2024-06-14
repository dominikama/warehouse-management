package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.application.dto.ErrorDto;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DefaultExceptionHandlingServiceTest {
   private final DefaultExceptionHandlingService defaultExceptionHandlingService = new DefaultExceptionHandlingService();

    @Test
    void createErrorResponseNoSuchElement() {
        String errorMessage = "Warehouse not found";
        ErrorDto errorDto = defaultExceptionHandlingService.createErrorResponse(new NoSuchElementException(errorMessage));
        assertEquals(404, errorDto.getResponseCode());
        assertEquals(errorMessage, errorDto.getMessage());
    }

    @Test
    void createErrorResponseRandomException() {
        ErrorDto errorDto = defaultExceptionHandlingService.createErrorResponse(new Exception("Test"));
        assertEquals(500, errorDto.getResponseCode());
    }
}