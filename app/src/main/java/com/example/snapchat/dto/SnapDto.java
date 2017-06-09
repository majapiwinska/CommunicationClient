package com.example.snapchat.dto;

import java.util.List;

/**
 * Created by maja on 02.05.17.
 */
public class SnapDto {

    private String senderEmail;
    private String image;
    private List<String> receiversEmails;
    private int seconds;
    private boolean opened;

    public SnapDto(String senderEmail, String image, List<String> receiversEmails) {
        this.senderEmail = senderEmail;
        this.image = image;
        this.receiversEmails = receiversEmails;
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

    public void setImage(String image){
        this.image = image;
    };

    public List<String> getReceiversEmails() {
        return receiversEmails;
    }

    public void setReceiversEmails(List<String> receiversEmails) {
        this.receiversEmails = receiversEmails;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
