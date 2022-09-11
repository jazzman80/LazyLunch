package com.example.lazylunch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FullRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);
        getSupportActionBar().hide();
    }
}