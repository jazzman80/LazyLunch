package com.example.lazylunch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class FullRecipeActivity extends AppCompatActivity {
    RepositoryInterface repository;
    RecipeModel currentRecipe;
    TextView recipeName;
    TextView recipeDescription;
    ImageView recipeImageView;
    CheckBox favoriteCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);
        Objects.requireNonNull(getSupportActionBar()).hide();

        repository = ((LazyLunchApplication) getApplication()).repository;

        recipeName = findViewById(R.id.recipeName);
        recipeDescription = findViewById(R.id.recipeText);
        recipeImageView = findViewById(R.id.fullRecipeImageView);
        favoriteCheckBox = findViewById(R.id.checkBox);
        currentRecipe = repository.getCurrentRecipe();

        favoriteCheckBox.setChecked(repository.isCurrentFavorite());

        recipeName.setText(currentRecipe.name);
        recipeDescription.setText(currentRecipe.description);
        recipeImageView.setImageBitmap(currentRecipe.image);

    }

    @Override
    protected void onPause() {
        super.onPause();
        repository.saveFavorites();
    }

    public void onFavoriteChecked(View view) {
        if (repository.isCurrentFavorite()) repository.removeFromFavorites();
        else repository.addToFavorites();
    }
}