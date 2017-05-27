package com.example.snapchat.dao;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.InputStream;

public class JsonFileStoreage {

    private final Context context;
    private final String fileName;

    public JsonFileStoreage(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public String loadJson() {
        String json = null;
        InputStream stream;
        try {
            stream = context.openFileInput(fileName);
            int size = stream.available();
            byte[] data = new byte[size];
            stream.read(data);
            stream.close();
            json = new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public void saveJson(String json) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}