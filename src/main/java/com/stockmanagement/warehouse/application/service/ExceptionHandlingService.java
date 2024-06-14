package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.application.dto.ErrorDto;

public interface ExceptionHandlingService {

    ErrorDto createErrorResponse(Exception e);
}
