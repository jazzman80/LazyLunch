package com.example.lazylunch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class FullRecipeActivity extends AppCompatActivity {
    RepositoryInterface repository;
    RecipeModel currentRecipe;
    TextView recipeName;
    TextView recipeDescription;
    ImageView recipeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);
        Objects.requireNonNull(getSupportActionBar()).hide();

        repository = ((LazyLunchApplication) getApplication()).repository;

        recipeName = findViewById(R.id.recipeName);
        recipeDescription = findViewById(R.id.recipeText);
        recipeImageView = findViewById(R.id.fullRecipeImageView);
        currentRecipe = repository.getCurrentRecipe();

        recipeName.setText(currentRecipe.name);
        recipeDescription.setText(currentRecipe.description);
        new DownloadImage(recipeImageView).execute(currentRecipe.imageAddress);
    }
}