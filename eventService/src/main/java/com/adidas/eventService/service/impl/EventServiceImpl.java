package com.adidas.eventService.service.impl;

import com.adidas.eventService.EventApplication;
import com.adidas.eventService.model.Event;
import com.adidas.eventService.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EventServiceImpl implements EventService {


    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private ApplicationContext context;

    @Override
    public Event createEvent(Event event) {



        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        logger.info("Sending an event");
        System.out.println("Sending an event");
        jmsTemplate.convertAndSend("events", "Newsletter 1");


        return event;
    }
}
