package com.adidas.subscriptions.service;

import com.adidas.subscriptions.dto.EventDto;
import com.adidas.subscriptions.exceptions.ConstraintsViolationException;
import com.adidas.subscriptions.model.Subscription;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription) throws Throwable;

    void eventProcess(EventDto eventDto) throws ConstraintsViolationException, Throwable;
}
