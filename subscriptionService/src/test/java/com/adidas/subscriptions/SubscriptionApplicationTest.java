package com.adidas.subscriptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.adidas"})
@ComponentScan("com.adidas")
public class SubscriptionApplicationTest {
  public static void main(String[] args) {
    SpringApplication.run(SubscriptionApplicationTest.class, args);
  }
}
