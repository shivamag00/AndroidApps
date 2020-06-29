package com.example.pokedex;

import android.content.Context;
import android.content.SharedPreferences;

public class Catcher {

    private static String NAME_STATE = "";
    private SharedPreferences preferences;

    Catcher(Context context,String name) {
        preferences = context.getSharedPreferences("pokedex-catcher", Context.MODE_PRIVATE);
        this.NAME_STATE=name;

    }

    public boolean isCaught() {
        return preferences.getBoolean(NAME_STATE, false);
    }

    public void setCaught(boolean loggedIn) {
        preferences.edit().putBoolean(NAME_STATE, loggedIn).apply();
    }
}