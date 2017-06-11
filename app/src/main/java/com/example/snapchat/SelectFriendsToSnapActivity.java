package com.example.snapchat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.adapter.CheckboxAdapter;
import com.example.snapchat.api.Api;
import com.example.snapchat.dto.FriendDto;
import com.example.snapchat.dto.SnapDto;
import com.example.snapchat.utils.ImageHolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maja on 01.06.17.
 */

public class SelectFriendsToSnapActivity extends FragmentActivity {

    private Preferences preferences;
    private SelectFriendsToSnapActivity thisInstance;
    private GestureDetectorCompat gestureObject;

    private ListView friendsListView;
    private ImageButton sendSnapButton;
    private ArrayList<String> friends;
    private List<String> selectedFriends;
    private CheckboxAdapter dataAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;

        setContentView(R.layout.activity_send_snap_to_friends);
        sendSnapButton = (ImageButton) findViewById(R.id.btnSend);
        friendsListView = (ListView) findViewById(R.id.friends_list_view);

        Long userId = preferences.getUserId();


        Api.getInstance().getFriends(userId)
                .enqueue(new Callback<List<FriendDto>>() {
                    @Override
                    public void onResponse(Call<List<FriendDto>> call, Response<List<FriendDto>> response) {
                        friends = new ArrayList<>();
                        try {

                            if (response.code() < 200 || response.code() >= 300) {
                                Toast.makeText(thisInstance, "Nie udalo sie wyswietlic uzytkownikow!", Toast.LENGTH_SHORT).show();
                                this.onFailure(call, new Throwable("HTTP error code"));
                            } else {

                                for (FriendDto friend : response.body()) {
                                    friends.add(friend.getEmail());
                                }

                                dataAdapter = new CheckboxAdapter(thisInstance,R.layout.friend_checkbox_view,friends);
                                friendsListView.setAdapter(dataAdapter);
                                friendsListView.setItemsCanFocus(false);
                                friendsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FriendDto>> call, Throwable t) {
                        Log.d(FriendsActivity.class.getSimpleName(), "Error in displaying friends(): " + t.getLocalizedMessage());
                    }
                });

        sendSnapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFriends = dataAdapter.getSelectedString();
                sendSnap(ImageHolder.getImage(), selectedFriends);
                clearCacheAndGoToMain();
            }
        });

    }

    private void clearCacheAndGoToMain() {
        ImageHolder.setImage("");
        Intent intent = new Intent(SelectFriendsToSnapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void sendSnap(String image, List<String> receivers) {
        new UploadSnap(image, preferences.getEmail(), receivers).execute();
    }

    private class UploadSnap extends AsyncTask<Void, Void, Void> {
        private String image;
        private String senderEmail;
        private List<String> receiversEmails;
        private Response<Void> response;

        public UploadSnap(String image, String senderEmail, List<String> receiversEmails) {
            this.image = image;
            this.senderEmail = senderEmail;
            this.receiversEmails = receiversEmails;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SnapDto dto = new SnapDto(senderEmail, image, receiversEmails);
            Call<Void> call = Api.getInstance().sendSnap(dto);

            try {
                response = call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (response.code() < 200 || response.code() >= 300) {
                Toast.makeText(thisInstance, "Ojoj, blad! Nie udalo Ci sie wyslac snapa!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(thisInstance, "Snap juz wyslany, szybciutko co?", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
