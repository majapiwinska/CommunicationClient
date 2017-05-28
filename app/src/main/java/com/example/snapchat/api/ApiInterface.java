package com.example.snapchat.api;

import com.example.snapchat.dto.FriendDto;
import com.example.snapchat.dto.SnapDto;
import com.example.snapchat.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    // TODO zapytanie get musi iść na ścieżkę z parametrem usera
    @GET("getsnaps")
    Call<List<SnapDto>> getSnaps(
            @Body List<SnapDto> snaps
    );

    @PUT("addfriend")
    Call<UserDto> addFriend(
            @Body FriendDto friendDto
    );

    // TODO zapytanie get musi iść na ścieżkę z parametrem usera
    @GET("getfriends")
    Call<List<UserDto>> getFriends(
            @Body List<UserDto> friends
    );

}
