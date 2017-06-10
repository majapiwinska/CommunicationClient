package com.example.snapchat.dto;

/**
 * Created by maja on 10.06.17.
 */

public class BasicDto {

    private String object;

    public BasicDto(){};

    public BasicDto(String object) {
        this.object = object;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
