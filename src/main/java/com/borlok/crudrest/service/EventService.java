package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventService() {
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event getById(int id) {
        return eventRepository.getOne(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
