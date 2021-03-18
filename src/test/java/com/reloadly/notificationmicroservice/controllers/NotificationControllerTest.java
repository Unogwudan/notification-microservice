package com.reloadly.notificationmicroservice.controllers;

import com.reloadly.notificationmicroservice.NotificationMicroServiceApplication;
import com.reloadly.notificationmicroservice.constants.CommonConstants;
import com.reloadly.notificationmicroservice.dto.response.NotificationMicroServiceResponse;
import com.reloadly.notificationmicroservice.enums.ResponseCode;
import com.reloadly.notificationmicroservice.helpers.TestHelper;
import com.reloadly.notificationmicroservice.services.MailerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NotificationMicroServiceApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@PropertySource("classpath:/test.properties")
public class NotificationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MailerService mailerService;

    @Test
    public void sendEmailNotification() {
        doReturn(Mono.just(TestHelper.getResponse())).when(mailerService).sendEmail(any());
        webTestClient.post()
                .uri(CommonConstants.API_VERSION + "notifications/email")
                .body(BodyInserters.fromValue(TestHelper.buildEmailRequest()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(NotificationMicroServiceResponse.class)
                .value(response -> response.getStatusCode().equals(ResponseCode.OK.getCanonicalCode()));
    }
}