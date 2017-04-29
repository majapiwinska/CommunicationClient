package com.example;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maja on 28.04.17.
 */

public class Preferences {

    private static final String EMAIL_KEY = "email_key";
    private static final String PASSWORD_KEY = "password_kye";
    private static final String LAST_SYNC_TIME_KEY = "last_sync_time_key";

    private static Preferences INSTANCE;

    private final SharedPreferences sharedPreferences;

    public static Preferences getInstance(Context context){
      if(INSTANCE == null){
          INSTANCE = new Preferences(context);
      }
        return INSTANCE;
    };

    private Preferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences("com.example", Context.MODE_PRIVATE);
    }


    public boolean isLogged() {
        return false;
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(EMAIL_KEY, email).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL_KEY, null);
    }

    public void setPassword(String password){
        sharedPreferences.edit().putString(PASSWORD_KEY, password).apply();
    }

    public String getPassword(){
        return  sharedPreferences.getString(PASSWORD_KEY, null);
    }

    public void setLastSyncTime(String syncTime){
        sharedPreferences.edit().putString(LAST_SYNC_TIME_KEY, syncTime).apply();
    }

    public String getLastSyncTime(){
        return sharedPreferences.getString(LAST_SYNC_TIME_KEY, null);
    }
}
