package com.example.snapchat.dao.repository;

import android.content.Context;

import com.example.snapchat.dao.model.User;
import com.example.snapchat.dao.JsonFileStoreage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserRepository {

    private final String FILE_NAME = "user.json";
    private Gson gson;
    private Type type;
    private JsonFileStoreage storeage;

    {
        gson = new Gson();
        type = new TypeToken<User>() {
        }.getType();
    }

    public UserRepository(Context context) {
        this.storeage = new JsonFileStoreage(context, FILE_NAME);
    }

    public User getUser() {
        String json = storeage.loadJson();
        User user = gson.fromJson(json, type);
        return user;
    }

    public void saveUser(User user) {
        String json = gson.toJson(user, type);
        storeage.saveJson(json);
    }

//    TODO: delete user for logout

}