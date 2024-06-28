package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";
    private ImageView imMainPoster;
    private TextView tvMainTitle;
    private TextView tvMainYear;
    private TextView tvMainDescription;
    private ImageView star;
    private MovieDetailViewModel movieDetailViewModel;
    private RecyclerView rcViewTrailers;
    private TrailersAdapter trailersAdapter;
    private RecyclerView rcViewReviews;
    private ReviewsAdapter reviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();

        reviewsAdapter = new ReviewsAdapter();
        rcViewReviews.setAdapter(reviewsAdapter);

        trailersAdapter = new TrailersAdapter();
        rcViewTrailers.setAdapter(trailersAdapter);
        trailersAdapter.setOnClickListenerTrailer(new TrailersAdapter.OnClickListenerTrailer() {
            @Override
            public void setOnClickTrailer(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });
        //получаем объект Movie
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrlPoster())
                .into(imMainPoster);

        tvMainTitle.setText(movie.getName());
        tvMainYear.setText(String.valueOf(movie.getYear()));
        tvMainDescription.setText(movie.getDescription());

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        movieDetailViewModel.loadTrailers(movie.getId());
        movieDetailViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        movieDetailViewModel.loadReviews(movie.getId());
        movieDetailViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewsAdapter.setReviews(reviews);
            }
        });
        Drawable starOff = ContextCompat.getDrawable(MovieDetailActivity.this, android.R.drawable.star_off);
        Drawable starOn = ContextCompat.getDrawable(MovieDetailActivity.this, android.R.drawable.star_on);
        movieDetailViewModel.getFavouriteMovies(movie.getId())
                .observe(MovieDetailActivity.this, new Observer<Movie>() {
                    @Override
                    public void onChanged(Movie favouriteMovie) {
                        if (favouriteMovie == null) {
                            star.setImageDrawable(starOff);
                            star.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    movieDetailViewModel.insertMovie(movie);
                                }
                            });
                        } else {
                            star.setImageDrawable(starOn);
                            star.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    movieDetailViewModel.removeMovie(movie.getId());
                                }
                            });
                        }
                    }
                });
    }

    //фабричный метод(передаем объект Movie)
    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {
        rcViewTrailers = findViewById(R.id.rcViewTrailers);
        imMainPoster = findViewById(R.id.imMainPoster);
        tvMainTitle = findViewById(R.id.tvMainTitle);
        tvMainYear = findViewById(R.id.tvMainYear);
        tvMainDescription = findViewById(R.id.tvMainDescription);
        rcViewReviews = findViewById(R.id.rcViewReviews);
        star = findViewById(R.id.imStar);
    }
}