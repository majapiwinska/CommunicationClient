package com.example.snapchat.api;

import android.app.DownloadManager;

import com.example.snapchat.api.dto.SnapDto;
import com.example.snapchat.api.dto.UserDto;
import com.example.snapchat.database.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by maja on 28.04.17.
 */

public interface ApiInterface {

    @POST("login")
    Call<UserDto> login(
            @Body UserDto userDto
    );

    @POST("signup")
    Call<UserDto> signUp(
            @Body UserDto userDto
    );

    @POST("sendsnap")
    Call<Void> sendSnap(
        @Body SnapDto snapDto
    );

}
