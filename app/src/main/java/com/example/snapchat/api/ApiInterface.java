package com.example.snapchat.api;

import com.example.snapchat.dto.BasicDto;
import com.example.snapchat.dto.FriendDto;
import com.example.snapchat.dto.GetSnapDto;
import com.example.snapchat.dto.SnapDto;
import com.example.snapchat.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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

    @GET("getsnaps/{id}")
    Call<List<GetSnapDto>> getSnaps(
           @Path("id") Long id
    );

    @PUT("addfriend/{id}")
    Call<Void> addFriend(
            @Path("id") Long id,
            @Body BasicDto friendEmail
    );

    @GET("getfriends/{id}")
    Call<List<FriendDto>> getFriends(
            @Path("id") Long id
    );

}
