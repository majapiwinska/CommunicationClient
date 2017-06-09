package com.example.snapchat.dto;

/**
 * Created by maja on 14.05.17.
 */

public class FriendDto {

    private Long id;

    private String nick;

    private String email;

    public FriendDto() {
    }

    public FriendDto(Long id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "FriendDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
