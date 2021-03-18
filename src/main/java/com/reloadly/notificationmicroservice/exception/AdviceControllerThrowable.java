package com.reloadly.notificationmicroservice.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.reloadly.notificationmicroservice.dto.response.NotificationMicroServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.SocketTimeoutException;
import java.nio.file.AccessDeniedException;

import static com.reloadly.notificationmicroservice.enums.ResponseCode.*;


@Slf4j
@ResponseBody
@ControllerAdvice(annotations = RestController.class, basePackages = "com.reloadly.accountmicroservice.controllers")
public class AdviceControllerThrowable {

    @ExceptionHandler(NullPointerException.class)
    public NotificationMicroServiceResponse noAccessException(NullPointerException e) {
        log.error("Null Pointer exception", e);
        return new NotificationMicroServiceResponse(INTERNAL_SERVER_ERROR.getCanonicalCode(), INTERNAL_SERVER_ERROR.getDescription());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public NotificationMicroServiceResponse handleLockedException(HttpClientErrorException e) {
        log.error("Error", e);
        return new NotificationMicroServiceResponse(String.valueOf(e.getRawStatusCode()), e.getStatusText() + " " + e.getResponseBodyAsString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public NotificationMicroServiceResponse handleLoginException(IllegalArgumentException e) {
        log.error("IllegalArgumentException ", e);
        return new NotificationMicroServiceResponse(BAD_REQUEST.getCanonicalCode(), BAD_REQUEST.getDescription());
    }

    @ExceptionHandler(NotificationMicroServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public NotificationMicroServiceResponse jsonException(NotificationMicroServiceException e) {
        return new NotificationMicroServiceResponse(e.getStatusCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public NotificationMicroServiceResponse noAccessException(AuthenticationException e) {
        log.error("AuthenticationException ", e);
        return new NotificationMicroServiceResponse("401", e.getMessage() + ", you do not privilege to access this resource");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public NotificationMicroServiceResponse noAccessException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException ", e);
        return new NotificationMicroServiceResponse(BAD_REQUEST.getCanonicalCode(), "Wrong message was sent or some required fields were not provided");
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public NotificationMicroServiceResponse timeOutException(SocketTimeoutException e) {
        log.error("SocketTimeoutException ", e);
        return new NotificationMicroServiceResponse("408", "Sorry...request is taking longer than expected.. please trying again later");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public NotificationMicroServiceResponse noAccessException(AccessDeniedException e) {
        log.error("AccessDeniedException ", e);
        return new NotificationMicroServiceResponse("401", e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public NotificationMicroServiceResponse noAccessException(Exception e) {
        log.error("Unknown Exception", e);
        return new NotificationMicroServiceResponse(INTERNAL_SERVER_ERROR.getCanonicalCode(), INTERNAL_SERVER_ERROR.getDescription());
    }

    @ExceptionHandler(JsonParseException.class)
    public NotificationMicroServiceResponse noAccessException(JsonParseException e) {
        log.error("Unknown Exception", e);
        return new NotificationMicroServiceResponse(UNMARSHALL_EXCEPTION.getCanonicalCode(), UNMARSHALL_EXCEPTION.getDescription());
    }
}
