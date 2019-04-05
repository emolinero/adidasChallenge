package com.adidas.subscriptions.controller;

import com.adidas.subscriptions.SubscriptionApplicationTest;
import com.adidas.subscriptions.dto.SubscriptionDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SubscriptionApplicationTest.class})
@ActiveProfiles({"integration-test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubscriptionControllerTest {
  @Autowired
  protected TestRestTemplate restTemplate;

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @BeforeAll
  public void cleanAllDatabases() {
    JdbcTestUtils.deleteFromTables(jdbcTemplate, "subscriptions", "newsletters", "customers");
    jdbcTemplate.execute("insert into newsletters (ID,NAME,DESCRIPTION) values (1,'Shirts','Shirts Campaign')");
    jdbcTemplate.execute("insert into customers (ID,EMAIL,FIRST_NAME,GENDER) values (1,'test@test.com','test test', 'm')");
    jdbcTemplate.execute("insert into subscriptions (ID,CONSENT,CUSTOMER_ID,NEWSLETTER_ID) values (1,TRUE,1, 1)");
  }

  @Test
  public void shouldCreateSubscription(){

    String url = "/subscriptions";

    SubscriptionDto subscriptionDto = new SubscriptionDto()
        .setEmail("second@test.com")
        .setConsent(true)
        .setDateOfBirth(new Date())
        .setFirstName("Test")
        .setGender("m")
        .setNewsletterId(1L);

    ResponseEntity<SubscriptionDto> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(subscriptionDto,null),
        SubscriptionDto.class);
    assertEquals(201, response.getStatusCode().value());
    assertEquals("second@test.com",response.getBody().getEmail());
  }

  @Test
  public void shouldDoNotCreateSubscription(){

    String url = "/subscriptions";

    SubscriptionDto subscriptionDto = new SubscriptionDto()
        .setEmail("test@test.com")
        .setConsent(true)
        .setDateOfBirth(new Date())
        .setFirstName("Test")
        .setGender("m")
        .setNewsletterId(1L);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(subscriptionDto,null),
        String.class);
    assertEquals(400, response.getStatusCode().value());
  }

}
