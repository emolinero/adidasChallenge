package com.adidas.eventService.service;

import com.adidas.eventService.model.Event;

public interface EventService {
    Event createEvent(Event event) throws Exception;
}
