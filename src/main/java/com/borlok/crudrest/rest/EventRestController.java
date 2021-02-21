package com.borlok.crudrest.rest;

import com.borlok.crudrest.dto.EventDto;
import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.User;
import com.borlok.crudrest.service.EventService;
import com.borlok.crudrest.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestController {
    private EventService eventService;
    private UserService userService;
    private Logger log = LogManager.getLogger(this);

    @Autowired
    public EventRestController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('access:user')")
    public EventDto getById (@PathVariable("id") Integer id) {
        log.info("Request event with id: " + id);
        return EventDto.fromEvent(eventService.getById(id));
    }


    @GetMapping
    @PreAuthorize("hasAuthority('access:user')")
    public List<EventDto> getAll() {
        log.info("Request all events");
        return eventService.getAll().stream().map(EventDto::fromEvent).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:admin')")
    public EventDto create (@RequestBody EventDto eventDto) {
        log.info("Create event with name: " + eventDto.getName());
        Event event = eventDto.toEvent();
        User user = new User();
        if (eventDto.getUser_id() != 0)
            user = userService.getById(eventDto.getUser_id());
        user.getEvents().add(event);
        event.setUser(user);
        log.info("Event with name: " + eventDto.getName() + " was making");
        return EventDto.fromEvent(eventService.create(event));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access:admin')")
    public void deleteById (@PathVariable("id") Integer id) {
        log.info("Delete event with id: " + id);
        eventService.deleteById(id);
    }

}
