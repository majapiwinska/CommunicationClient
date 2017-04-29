package com.example.snapchat.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * Created by maja on 31.03.17.
 */

@DatabaseTable(tableName = "snaps")
public class Snap implements Serializable {

    public static final String COLUMN_SENDER_ID = "sender_id";
    public static final String COLUMN_SNAP_ID = "snap_id";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_SECONDS = "seconds";
    public static final String COLUMN_OPENED = "opened";

    @DatabaseField(generatedId = true, columnName = COLUMN_SNAP_ID, unique = true)
    private Long id;

    @DatabaseField(columnName = COLUMN_SECONDS)
    private int seconds;

    @DatabaseField(columnName = COLUMN_SENDER_ID)
    private Long senderId;

    @DatabaseField(columnName = COLUMN_PHOTO)
    private Blob photo;

    @DatabaseField(columnName = COLUMN_OPENED)
    private boolean opened;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
