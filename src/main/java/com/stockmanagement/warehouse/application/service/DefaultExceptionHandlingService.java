package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.application.dto.ErrorDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class DefaultExceptionHandlingService implements ExceptionHandlingService {

    Map<Class<? extends Exception>, Integer> errorAndResponseCode = Map.of(NoSuchElementException.class, 404);
    @Override
    public ErrorDto createErrorResponse(Exception e) {
        int errorCode = errorAndResponseCode.getOrDefault(e.getClass(), 500);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setResponseCode(errorCode);
        return errorDto;
    }
}
