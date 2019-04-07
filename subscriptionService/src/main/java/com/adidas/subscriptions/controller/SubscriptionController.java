package com.adidas.subscriptions.controller;


import com.adidas.subscriptions.dto.EventDto;
import com.adidas.subscriptions.dto.SubscriptionDto;
import com.adidas.subscriptions.exceptions.ConstraintsViolationException;
import com.adidas.subscriptions.model.Subscription;
import com.adidas.subscriptions.service.SubscriptionService;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/subscriptions")
@Api(tags = "Subscription")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    SubscriptionService service;

    @Autowired MapperFacade mapper;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionDto create(@RequestBody @Valid SubscriptionDto subscriptionDto) throws Throwable {
        Subscription subscription = mapper.map(subscriptionDto, Subscription.class);

        subscription = service.createSubscription(subscription);

        return mapper.map(subscription, SubscriptionDto.class);
    }

    @JmsListener(destination = "events")
    public void listen(String in) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            EventDto eventDto = mapper.readValue(in, EventDto.class);

            logger.error("[INFO] New event to process - EventId: {}", eventDto.getId());

            service.eventProcess(eventDto);
        } catch (IOException e) {
            logger.error("[ERROR] Event Message can't read");
        } catch (ConstraintsViolationException e) {
            logger.error("[ERROR] Event Message can't read");
        } catch (Throwable throwable) {
            logger.error("[ERROR] Event Message can't read");
        }
    }


}
