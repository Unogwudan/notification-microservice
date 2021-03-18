package com.reloadly.notificationmicroservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMicroServiceResponse {
    private String statusCode;
    private String statusMessage;
    private String timestamp;

    public NotificationMicroServiceResponse(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.timestamp = LocalDateTime.now().toString();
    }
}
