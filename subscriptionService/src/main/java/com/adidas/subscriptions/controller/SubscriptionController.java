package com.adidas.subscriptions.controller;


import com.adidas.subscriptions.dto.SubscriptionDto;
import com.adidas.subscriptions.model.Subscription;
import com.adidas.subscriptions.service.SubscriptionService;
import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
@Api(tags = "Subscription")
public class SubscriptionController {

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
}
