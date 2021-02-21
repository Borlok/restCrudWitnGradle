package com.borlok.crudrest.service;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTests {
    @Spy
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private Event event;

    @Mock
    private List<Event> events;



    @Before
    public void setUp() {
        int id = 1;
        eventService = new EventService(eventRepository);

        when(event.getName()).thenReturn("name");
        when(eventRepository.getOne(id)).thenReturn(event);
        when(eventRepository.save(event)).thenReturn(event);
        when(eventRepository.findAll()).thenReturn(events);

    }

    @Test
    public void notNullTest() {
        assertNotNull(eventRepository);
        assertNotNull(eventService);
        assertNotNull(event);
    }

    @Test
    public void getEventById() {
        assertNotNull(eventService.getById(1));
        assertEquals("name",eventService.getById(1).getName());
    }

    @Test
    public void getAll () {
        assertNotNull(eventService.getAll());
        assertEquals(events, eventService.getAll());
    }

    @Test
    public void create() {
        assertNotNull(eventService.create(event));
        assertEquals(event, eventService.create(event));
    }

}
