package com.mastercode.employee.exception.handling;

import com.mastercode.employee.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<RequestError> uspChallengeNotFoundExceptionHandle(EmployeeNotFoundException e) {
        LOGGER.error("Employee not found: {}", e.getMessage());
        return new ResponseEntity<>(new RequestError(InternalErrorCode.EMPLOYEE_NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

}