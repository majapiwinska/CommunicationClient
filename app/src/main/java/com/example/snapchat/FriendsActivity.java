package com.example.snapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.database.model.Friend;

/**
 * Created by maja on 14.05.17.
 */

public class FriendsActivity extends AppCompatActivity {

    private Preferences preferences;
    private FriendsActivity thisInstance;
    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;

        setContentView(R.layout.activity_friends_list);
        gestureObject = new GestureDetectorCompat(this, new FriendsActivity.LearnGesture());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    };

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){

            if(e2.getX() > e1.getX()){

                Intent intent = new Intent(FriendsActivity.this, MainActivity.class);
                startActivity(intent);
            }
            return true;

        };

    }


}
