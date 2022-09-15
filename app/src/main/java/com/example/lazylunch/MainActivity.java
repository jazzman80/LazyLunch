package com.example.lazylunch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RepositoryInterface repository;
    RecipeModel currentRecipe;
    TextView recipeNameTextView;
    ImageView recipeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        repository = ((LazyLunchApplication) getApplication()).repository;

        recipeNameTextView = findViewById(R.id.recipeNameTextView);
        recipeImageView = findViewById(R.id.recipeImageView);
        updateUI();
    }

    private void updateUI(){
        currentRecipe = repository.getCurrentRecipe();
        recipeNameTextView.setText(currentRecipe.name);
        //new DownloadImage(recipeImageView).execute(currentRecipe.imageAddress);
        recipeImageView.setImageBitmap(currentRecipe.image);
    }


    public void onThisRecipeButtonClick(View view){
        Intent intent = new Intent(this, FullRecipeActivity.class);
        startActivity(intent);
    }

    public void onNextRecipeButtonClick(View view){
        repository.loadNextRecipe();
        updateUI();
    }
}