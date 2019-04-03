package com.adidas.mailService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.jms.annotation.JmsListener;


public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    public static void main(String[] args) {
        SpringApplication.run(EmailController.class, args);
    }

    @JmsListener(destination = "emails")
    public void listen(String in) {
        System.out.println(in);
        logger.info(in);
    }
}