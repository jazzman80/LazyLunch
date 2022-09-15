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

        recipeName.setText(currentRecipe.name);
        recipeDescription.setText(currentRecipe.description);
        //new DownloadImage(recipeImageView).execute(currentRecipe.imageAddress);
        recipeImageView.setImageBitmap(currentRecipe.image);

    }

    public void onFavoriteChecked(View view){
        if (currentRecipe.isFavorite) repository.removeFromFavorites();
        else repository.addToFavorites();
    }
}