package com.reloadly.notificationmicroservice.services;

import com.reloadly.notificationmicroservice.dto.request.EmailRequest;
import com.reloadly.notificationmicroservice.dto.response.NotificationMicroServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

import static com.reloadly.notificationmicroservice.enums.ResponseCode.OK;

@Slf4j
@Service
public class MailerService {

    @Value("${spring.sendgrid.api-key}")
    private String SG_API_KEY;

    @Autowired
    private JavaMailSender javaMailSender;

    public Mono<NotificationMicroServiceResponse> sendEmail(EmailRequest request) {
        Mono.just(request)
                .subscribeOn(Schedulers.elastic())
                .subscribe(res -> {
                    try {
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(request.getTo());
                        mail.setSubject(request.getSubject());
                        mail.setText(request.getMessage());
                        javaMailSender.send(mail);
                    } catch (MailException e) {
                        log.info("An exception occurred while sending email notification {}", e.getMessage());
                    }
                });
        return Mono.just(NotificationMicroServiceResponse.builder()
                .statusCode(OK.getCanonicalCode())
                .statusMessage(OK.getDescription())
                .timestamp(LocalDateTime.now().toString())
                .build());
    }

}
