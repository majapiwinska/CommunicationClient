package com.example.snapchat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Preferences;
import com.example.maja.snapchat.R;
import com.example.snapchat.api.Api;
import com.example.snapchat.api.dto.SnapDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private Button btnCamera;
    private Button btnSendSnap;
    private Button btnEditSnap;
    private ImageView capturedImage;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = Preferences.getInstance(this);

        setContentView(R.layout.activity_main);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnSendSnap = (Button) findViewById(R.id.btnSendSnap);
        btnEditSnap = (Button) findViewById(R.id.btnEditSnap);
        capturedImage= (ImageView) findViewById(R.id.capturedImage);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        btnSendSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSnap();

              }
        });
        openCamera();
    }

    private void sendSnap() {
        new UploadSnap(capturedImage, preferences.getEmail()).execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    private String encodeImage(ImageView capturedImage) {
        capturedImage.buildDrawingCache();
        Bitmap bitmap = capturedImage.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] b = stream.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);
        }
    }

    private class UploadSnap extends AsyncTask<Void, Void, Void> {
        private ImageView imageView;
        private String email;
        private String response = "fail";

        public UploadSnap(ImageView imageView, String email) {
            this.imageView = imageView;
            this.email = email;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String image = encodeImage(imageView);
            SnapDto dto = new SnapDto(email, image);
            Call<Void> call = Api.getInstance().sendSnap(dto);

            try{
                Response<Void> r = call.execute();
                response = r.message();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "dostalam snapa body: " + response,Toast.LENGTH_LONG ).show();

            // Toast.makeText(MainActivity.this, "Koniec async-task" ,Toast.LENGTH_LONG ).show();
        }
    }

}
