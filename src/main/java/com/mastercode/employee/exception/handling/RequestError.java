package com.mastercode.employee.exception.handling;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RequestError {

    private int errorId;
    private LocalDateTime timestamp;
    private String errorCode;
    private String errorMessage;

    public RequestError(InternalErrorCode errorCode, String errorMessage) {
        this(errorCode, errorMessage, LocalDateTime.now());
    }

    public RequestError(InternalErrorCode errorCode, String errorMessage, LocalDateTime timestamp) {
        this.errorId = errorCode.getId();
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }
}
