package com.adidas.subscriptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
@ComponentScan("com.adidas")
public class SubscriptionApplication {



    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApplication.class, args);
    }



}
