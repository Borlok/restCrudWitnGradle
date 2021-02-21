package com.borlok.crudrest.dto;

import com.borlok.crudrest.model.Event;
import com.borlok.crudrest.model.File;
import com.borlok.crudrest.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private int id;
    private int account_id;
    private List<Integer> file_ids;
    private List<Integer> event_ids;


    public UserDto() {
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.account_id = user.getAccount().getId();
        userDto.event_ids = user.getEvents().stream().map(Event::getId).collect(Collectors.toList());
        userDto.file_ids = user.getFiles().stream().map(File::getId).collect(Collectors.toList());
        return userDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public List<Integer> getFile_ids() {
        return file_ids;
    }

    public void setFile_ids(List<Integer> file_ids) {
        this.file_ids = file_ids;
    }

    public List<Integer> getEvent_ids() {
        return event_ids;
    }

    public void setEvent_ids(List<Integer> event_ids) {
        this.event_ids = event_ids;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", file_ids=" + file_ids +
                ", event_ids=" + event_ids +
                '}';
    }
}
