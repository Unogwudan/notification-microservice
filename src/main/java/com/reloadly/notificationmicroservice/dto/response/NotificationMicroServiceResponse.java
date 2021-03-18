package com.reloadly.notificationmicroservice.dto.response;

import lombok.*;

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
}
