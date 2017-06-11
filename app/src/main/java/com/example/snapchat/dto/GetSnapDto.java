package com.example.snapchat.dto;

/**
 * Created by maja on 11.06.17.
 */

public class GetSnapDto {

    private String senderEmail;
    private String image;

    public GetSnapDto() {
    }

    public GetSnapDto(String senderEmail, String image) {
        this.senderEmail = senderEmail;
        this.image = image;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
