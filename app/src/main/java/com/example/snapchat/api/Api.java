package com.example.snapchat.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maja on 28.04.17.
 */

public class Api {

    private static ApiInterface INSTANCE;

    public static ApiInterface getInstance(){
        if(INSTANCE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.131.26:8080/")  //server url
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            INSTANCE = retrofit.create(ApiInterface.class);
        }
        return INSTANCE;
    }
}