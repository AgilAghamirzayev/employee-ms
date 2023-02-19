package com.mastercode.employee.exception.handling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternalErrorCode {

    EMPLOYEE_NOT_FOUND(100, "unknown_employee"),
    INVALID_VALUE(101, "invalid_value");

    private final int id;
    private final String code;
}
