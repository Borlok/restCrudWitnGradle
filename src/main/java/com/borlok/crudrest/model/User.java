package com.borlok.crudrest.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<File> files;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Event> events;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    public User() {
        id = 0;
        files = new ArrayList<>();
        events = new ArrayList<>();
        account = new Account();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", files=" + files +
                ", events=" + events +
                ", account=" + account +
                '}';
    }
}
