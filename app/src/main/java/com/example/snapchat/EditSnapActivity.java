package com.example.snapchat;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.Preferences;
import com.example.maja.snapchat.R;

/**
 * Created by maja on 10.06.17.
 */

public class EditSnapActivity extends AppCompatActivity {

    private ImageView capturedImage;
    private ImageButton timerButton;
    private NumberPicker time;
    private TextView textView;
    private Button dialogButton;

    private Preferences preferences;
    private EditSnapActivity thisInstance;
    private GestureDetectorCompat gestureObject;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        capturedImage = (ImageView) findViewById(R.id.capturedImage);
        timerButton = (ImageButton) findViewById(R.id.timer_button);


        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(thisInstance);
                dialog.setContentView(R.layout.dialog_set_time);
                time = (NumberPicker) dialog.findViewById(R.id.numberPicker);
                textView = (TextView) dialog.findViewById(R.id.set_second_text_view);
                dialogButton = (Button) dialog.findViewById(R.id.set_seconds_dialog_btn);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seconds = time.getValue();
                    }
                });

            }
        });
    }

    private void openTimeDialog(){

    }

}
