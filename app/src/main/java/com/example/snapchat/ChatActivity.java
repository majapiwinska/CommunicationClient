package com.example.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.api.Api;
import com.example.snapchat.dto.FriendDto;
import com.example.snapchat.dto.GetSnapDto;
import com.example.snapchat.dto.SnapDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maja on 14.05.17.
 */

public class ChatActivity extends AppCompatActivity {

    private Preferences preferences;
    private ChatActivity thisInstance;
    private GestureDetectorCompat gestureObject;

    private Button refreshChatBtn;
    private ListView snapsListView;

    private List<GetSnapDto> snaps;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;
        gestureObject = new GestureDetectorCompat(this, new ChatActivity.LearnGesture());
        snapsListView = (ListView) findViewById(R.id.snaps_list_view);

        setContentView(R.layout.activity_chat);
        refreshChatBtn = (Button) findViewById(R.id.get_snaps_button);
        refreshChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSnaps(preferences.getUserId());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void getSnaps(Long id){
        Api.getInstance().getSnaps(id)
                .enqueue(new Callback<List<GetSnapDto>>() {
                    @Override
                    public void onResponse(Call<List<GetSnapDto>> call, Response<List<GetSnapDto>> response) {
                        try {
                            if (response.code()  < 200 || response.code() >= 300) {
                                Toast.makeText(thisInstance, "Nie udalo sie wyswietlic uzytkownikow!", Toast.LENGTH_SHORT).show();
                                this.onFailure(call, new Throwable("Niepoprawne dane logowania"));
                            } else {
                                snaps = response.body();
                                adapter = new ArrayAdapter<>(thisInstance, R.layout.chat_text_view, snaps);
                                snapsListView.setAdapter(adapter);
                                Toast.makeText(thisInstance, "yay,widzisz uzytkownikow!", Toast.LENGTH_SHORT).show();
                                this.onFailure(call, new Throwable("nie wiem jaki bald"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<GetSnapDto>> call, Throwable t) {
                        Log.d(FriendsActivity.class.getSimpleName(), "Error in displaying friends(): " + t.getLocalizedMessage());
                    }
                });
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){

         if(e1.getX() > e2.getX()){
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
            return true;

        };

    }

}
