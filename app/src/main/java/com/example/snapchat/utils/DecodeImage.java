package com.example.snapchat.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

public class DecodeImage extends AsyncTask<Void, Void, Bitmap> {

    private String image;

    public DecodeImage(String image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        return decode();
    }

    private Bitmap decode() {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

}