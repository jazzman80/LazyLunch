package com.example.lazylunch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class FavoritesActivity extends AppCompatActivity implements FavoritesViewAdapter.ItemClickListener {

    FavoritesViewAdapter adapter;
    RepositoryInterface repository;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Objects.requireNonNull(getSupportActionBar()).hide();

        repository = ((LazyLunchApplication) getApplication()).repository;

        recyclerView = findViewById(R.id.favoritesView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FavoritesViewAdapter(this, repository.getFavorites());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        repository.setCurrentFromFavorites(position);
        Intent intent = new Intent(this, FullRecipeActivity.class);
        startActivity(intent);
    }
}