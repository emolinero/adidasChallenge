package com.adidas.eventService.service.impl;

import com.adidas.eventService.dto.EventDto;
import com.adidas.eventService.model.Event;
import com.adidas.eventService.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.io.IOException;
import java.io.OutputStream;
=======
import java.util.Random;
>>>>>>> 5f4429e6b5870423438f76a6a4181e0773bec053


@Service
@Transactional
public class EventServiceImpl implements EventService {


  private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

  @Autowired
  private ApplicationContext context;

  @Override
  public Event createEvent(Event event) throws Exception {
    JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
<<<<<<< HEAD
    logger.info("Sending an event: {}", event.toString());

=======
    event.setId(new Random().nextLong());
    logger.info("Sending an event");
>>>>>>> 5f4429e6b5870423438f76a6a4181e0773bec053
    jmsTemplate.convertAndSend("events", event);
    return event;
  }
}
