package com.example.socialmediaez.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private Context context;
    private static final String PREFERENCE_NAME = "preference_workshop";
    private static final String PREFERENCE_TOKEN = "token";

    public AppPreference(Context context) {
        this.context = context;
    }

    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    public Boolean isLoggedIn(){
        return sharedPreferences.getString(PREFERENCE_TOKEN, null) != null;
    }

    public void saveToken(String token){
        editor.putString(PREFERENCE_TOKEN, token);
    }

    public String getToken() {
        return sharedPreferences.getString(PREFERENCE_TOKEN, null);
    }

    public void clear(){
        editor.clear();
        editor.apply();
    }
}
