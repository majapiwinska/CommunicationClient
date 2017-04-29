package com.example.snapchat.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by maja on 31.03.17.
 */

@DatabaseTable(tableName = "users")
public class User implements Serializable {

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NICK = "nick";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    @DatabaseField(generatedId = true, columnName = COLUMN_USER_ID, unique = true)
    private Long id;

    @DatabaseField(columnName = COLUMN_NAME)
    private String name;

    @DatabaseField(columnName = COLUMN_SURNAME)
    private String surname;
    @DatabaseField(columnName = COLUMN_NICK)
    private String nick;

    @DatabaseField(columnName = COLUMN_EMAIL)
    private String email;

    @DatabaseField(columnName = COLUMN_PASSWORD)
    private String password;

    public User() {};

    public User(String name, String surname, String nick, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}