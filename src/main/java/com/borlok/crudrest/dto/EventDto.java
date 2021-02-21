package com.borlok.crudrest.dto;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.User;

public class EventDto {
    private int id;
    private int user_id;
    private String name;

    public EventDto() {
    }

    public Event toEvent() {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

    public static EventDto fromEvent(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.id = event.getId();
        if (event.getUser() == null)
            event.setUser(new User());
        eventDto.user_id = event.getUser().getId();
        eventDto.name = event.getName();
        return eventDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
