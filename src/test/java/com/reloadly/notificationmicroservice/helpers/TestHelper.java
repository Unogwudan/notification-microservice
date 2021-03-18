package com.reloadly.notificationmicroservice.helpers;

import com.reloadly.notificationmicroservice.dto.request.EmailRequest;
import com.reloadly.notificationmicroservice.dto.response.NotificationMicroServiceResponse;
import com.reloadly.notificationmicroservice.enums.ResponseCode;

import java.time.LocalDateTime;

public class TestHelper {

    public static EmailRequest buildEmailRequest() {
        return new EmailRequest("ab@yahoo.com", "Transaction Completed", "Successful");
    }

    public static NotificationMicroServiceResponse getResponse() {
        return NotificationMicroServiceResponse.builder()
                .statusCode(ResponseCode.OK.getCanonicalCode())
                .statusMessage(ResponseCode.OK.getDescription())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}
