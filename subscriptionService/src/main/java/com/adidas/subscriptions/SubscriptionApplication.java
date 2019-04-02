package com.adidas.subscriptions;

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

    @JmsListener(destination = "foo")
    public void listen(String in) {
        System.out.println(in);
    }

}
