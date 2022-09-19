package com.example.lazylunch;

import android.content.SharedPreferences;

public class LocalDataSource implements LocalDataSourceInterface {
    public static final String DATA = "data";

    SharedPreferences settings;

    LocalDataSource(SharedPreferences settings) {
        this.settings = settings;
    }

    @Override
    public void save(String toSave) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(DATA, toSave);
        editor.apply();
    }

    @Override
    public String load() {
        return settings.getString(DATA, "");
    }
}
