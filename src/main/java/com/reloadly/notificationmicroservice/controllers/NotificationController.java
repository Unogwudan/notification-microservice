package com.reloadly.notificationmicroservice.controllers;

import com.reloadly.notificationmicroservice.constants.CommonConstants;
import com.reloadly.notificationmicroservice.dto.request.EmailRequest;
import com.reloadly.notificationmicroservice.dto.response.NotificationMicroServiceResponse;
import com.reloadly.notificationmicroservice.services.MailerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CommonConstants.API_VERSION + "notifications")
public class NotificationController {

    private final MailerService mailerService;

    public NotificationController(MailerService mailerService) {
        this.mailerService = mailerService;
    }

    @PostMapping(path = "/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<NotificationMicroServiceResponse>> sendEmailNotification(@RequestBody EmailRequest request) {
        return mailerService.sendEmail(request)
                .map(response -> ResponseEntity.ok(response));
    }

}
