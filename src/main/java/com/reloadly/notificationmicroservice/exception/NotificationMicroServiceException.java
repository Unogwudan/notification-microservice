package com.reloadly.notificationmicroservice.exception;

import lombok.Data;


@Data
public class NotificationMicroServiceException extends RuntimeException {
    private final Integer httpCode;
    private String statusCode;

    public NotificationMicroServiceException(Integer httpCode, String message, String statusCode) {
        super(message);
        this.httpCode = httpCode;
        this.statusCode = statusCode;
    }
}