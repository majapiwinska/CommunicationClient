package com.example.snapchat.api;

import android.app.DownloadManager;

import com.example.snapchat.database.model.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by maja on 28.04.17.
 */

public interface ApiInterface {

    @POST("/login")
    Call<User> login(
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("/signUp")
    Call<User> signUp(
            @Query("name") String name,
            @Query("surname") String surname,
            @Query("nick") String nick,
            @Query("email") String email,
            @Query("password") String password
    );


}
