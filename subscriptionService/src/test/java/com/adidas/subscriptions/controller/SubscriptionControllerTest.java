package com.adidas.subscriptions.controller;

import com.adidas.subscriptions.SubscriptionApplicationTest;
import com.adidas.subscriptions.dto.SubscriptionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SubscriptionApplicationTest.class})
public class SubscriptionControllerTest {
  @Autowired
  protected TestRestTemplate restTemplate;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Test
  public void shouldCreateSubscription(){

    String url = "/subscriptions";

    SubscriptionDto subscriptionDto = new SubscriptionDto()
        .setEmail("tesst@test.com")
        .setConsent(true)
        .setDateOfBirth(new Date())
        .setFirstName("Test")
        .setGender("m")
        .setNewsletterId(1L);

    ResponseEntity<SubscriptionDto> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(subscriptionDto,null),
        SubscriptionDto.class);
    assertEquals(201, response.getStatusCode().value());
    assertEquals("tesst@test.com",response.getBody().getEmail());
  }

}
