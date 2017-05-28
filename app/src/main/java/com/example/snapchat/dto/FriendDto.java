package com.example.snapchat.dto;

/**
 * Created by maja on 14.05.17.
 */

public class FriendDto {

    private String userEmail;
    private String friendEmail;

    public FriendDto() {
    }

    public FriendDto(String userEmail, String friendEmail) {
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }
}
