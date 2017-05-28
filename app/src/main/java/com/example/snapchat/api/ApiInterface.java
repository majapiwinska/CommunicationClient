package com.example.snapchat.api;

import com.example.snapchat.database.model.Friend;
import com.example.snapchat.dto.FriendDto;
import com.example.snapchat.dto.SnapDto;
import com.example.snapchat.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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

    @GET("getsnaps")
    Call<List<SnapDto>> getSnaps(
            @Body List<SnapDto> snaps
    );

    @PUT("addfriend")
    Call<UserDto> addFriend(
            @Body FriendDto friendDto
            );

    @GET("getFriends")
    Call<List<FriendDto>> getFriends(
        @Body List<FriendDto> friends
    );

}
