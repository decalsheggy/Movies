package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavoriteMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        RecyclerView rcViewFavourites = findViewById(R.id.rcViewFavourites);

        MoviesAdapter moviesAdapter = new MoviesAdapter();//используем уже созданный адаптер
        rcViewFavourites.setAdapter(moviesAdapter);
        rcViewFavourites.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter.setOnClick(new MoviesAdapter.OnClickMovie() {
            @Override
            public void setOnClickMovie(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(FavoriteMovies.this, movie);
                startActivity(intent);
            }
        });

        FavouriteMoviesViewModel viewModel = new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
    }
    public static Intent newIntent(Context context){
        return new Intent(context, FavoriteMovies.class);
    }
}