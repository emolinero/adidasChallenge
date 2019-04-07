package com.adidas.mailService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @JmsListener(destination = "emails")
    public void listen(String in) {
        System.out.println(in);
        logger.info(in);
    }
}