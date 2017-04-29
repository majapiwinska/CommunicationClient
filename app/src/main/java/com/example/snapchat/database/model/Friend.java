package com.example.snapchat.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by maja on 09.04.17.
 */

@DatabaseTable(tableName = "friend")
public class Friend {

    public static final String COLUMN_FRIEND_ID = "friend_id";
    public static final String COLUMN_FRIEND_NICK = "friend_nick";

    @DatabaseField(columnName = COLUMN_FRIEND_ID, canBeNull = false)
    private Long id;

    @DatabaseField(columnName = COLUMN_FRIEND_NICK, canBeNull = false)
    private String nick;

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
}
