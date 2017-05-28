package com.example.snapchat.storeage.dao;

import android.content.Context;

import com.example.snapchat.storeage.model.LoggedUser;
import com.example.snapchat.storeage.JsonFileStoreage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserDAO {

    private final String FILE_NAME = "user.json";
    private Gson gson;
    private Type type;
    private JsonFileStoreage storeage;

    {
        gson = new Gson();
        type = new TypeToken<LoggedUser>() {
        }.getType();
    }

    public UserDAO(Context context) {
        this.storeage = new JsonFileStoreage(context, FILE_NAME);
    }

    public LoggedUser getUser() {
        String json = storeage.loadJson();
        LoggedUser loggedUser = gson.fromJson(json, type);
        return loggedUser;
    }

    public void saveUser(LoggedUser loggedUser) {
        String json = gson.toJson(loggedUser, type);
        storeage.saveJson(json);
    }

//    TODO: delete user for logout

}