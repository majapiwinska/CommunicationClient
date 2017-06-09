package com.example.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.Preferences;
import com.example.maja.snapchat.R;

/**
 * Created by maja on 14.05.17.
 */

public class ChatActivity extends AppCompatActivity {

    private Preferences preferences;
    private ChatActivity thisInstance;
    private GestureDetectorCompat gestureObject;

    private Button refreshChatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;
        gestureObject = new GestureDetectorCompat(this, new ChatActivity.LearnGesture());

        setContentView(R.layout.activity_chat);
        refreshChatBtn = (Button) findViewById(R.id.refresh_chat_button);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    };

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
