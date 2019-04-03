package com.adidas.eventService.controller;

import com.adidas.eventService.dto.EventDto;
import com.adidas.eventService.model.Event;
import com.adidas.eventService.service.EventService;
import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event")
@Api(tags = "Event")
public class EventController {

  @Autowired
  EventService service;

  @Autowired
  MapperFacade mapper;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public EventDto create(@RequestBody @Valid EventDto eventDto) throws Throwable {
    Event event = mapper.map(eventDto, Event.class);
    event = service.createEvent(event);

    return mapper.map(event, EventDto.class);
  }
}
