package com.example.snapchat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.api.Api;
import com.example.snapchat.dto.GetSnapDto;
import com.example.snapchat.utils.DecodeImage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    private List<GetSnapDto> snaps = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);
        thisInstance = this;
        gestureObject = new GestureDetectorCompat(this, new ChatActivity.LearnGesture());

        setContentView(R.layout.activity_chat);
        refreshChatBtn = (Button) findViewById(R.id.get_snaps_button);
        refreshChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSnaps(preferences.getUserId());
            }
        });

        snapsListView = (ListView) findViewById(R.id.snaps_list_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void getSnaps(Long id) {
        Api.getInstance().getSnaps(id)
                .enqueue(new Callback<List<GetSnapDto>>() {
                    @Override
                    public void onResponse(Call<List<GetSnapDto>> call, Response<List<GetSnapDto>> response) {
                        try {
                            if (response.code() < 200 || response.code() >= 300) {
                                Toast.makeText(thisInstance, "Upsi.. Blad w ladowaniu snapow!", Toast.LENGTH_SHORT).show();
                                this.onFailure(call, new Throwable("Blad HTTP!"));
                            } else {
                                snaps.addAll(response.body());
                                if (snaps.isEmpty()) {
                                    Toast.makeText(thisInstance, "Nie masz nowych snapow. Smuteczek :(", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(thisInstance, "Foteczki czekaja, obczajamy? :)", Toast.LENGTH_SHORT).show();
                                showSnapList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetSnapDto>> call, Throwable t) {
                        Log.d(ChatActivity.class.getSimpleName(), "Error in displaying snaps(): " + t.getLocalizedMessage());
                    }
                });
    }

    private void showSnapList() {
        adapter = new ArrayAdapter(thisInstance, R.layout.friend_text_view, snaps);
        snapsListView.setAdapter(adapter);
        snapsListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetSnapDto item = snaps.get(position);
                Bitmap bitmap = null;
                try {
                    bitmap = new DecodeImage(item.getPhoto()).execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (bitmap == null) {
                    Toast.makeText(thisInstance, "Upsi.. Blad w dekodowaniu obrazka!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Dialog dialog = new Dialog(thisInstance);
                dialog.setContentView(R.layout.dialog_show_snap);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.show_snap_image_view);
                imageView.setImageBitmap(bitmap);
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

                snaps.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }


    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() > e2.getX()) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
            return true;

        }
    }

}
