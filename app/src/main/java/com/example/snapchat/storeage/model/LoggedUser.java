package com.example.snapchat.storeage.model;

public class LoggedUser {

    private final long id;
    private final String email;

    public LoggedUser(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}