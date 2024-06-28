package com.example.movies;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {//2.затем наследуем Adapter
    private List<Movie> movies = new ArrayList<>();//4.передаем в адаптер коллекцию фильмов
    private OnReachEndListener onReachEndListener;
    private OnClickMovie onClickMovie;

    public void setOnClick(OnClickMovie onClickListener) {
        this.onClickMovie = onClickListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {//5.создаем сеттер, чтобы добавлять в коллекцию фильмы
        this.movies = movies;
        notifyDataSetChanged();//6.устанавливаем изменения в коллекции(обновляем данные в адаптере)
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {//1.сначала наследуем ViewHolder
        private final ImageView imPoster;
        private final TextView tvKpRating;
        private final TextView tvImdbRating;

        public MovieViewHolder(@NonNull View itemView) {//в конструкторе холдела все вьюшки инициализируются
            super(itemView);
            imPoster = itemView.findViewById(R.id.imPoster);
            tvKpRating = itemView.findViewById(R.id.tvKp);
            tvImdbRating = itemView.findViewById(R.id.tvImdb);
        }
    }
    //3.переопределяем методы адаптера

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //7.Из макета создаем view
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(view);//создаем MovieViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //8.Созданный MovieViewHolder
        Movie movie = movies.get(position);
        if (movie.getPoster() != null && movie.getPoster().getUrlPoster() != null) {
            Glide.with(holder.itemView)
                    .load(movie.getPoster().getUrlPoster()) // передаем адрес картинки
                    .into(holder.imPoster); // куда устанавливаем изображение
        } else {
            Glide.with(holder.itemView)
                    .load(R.drawable.circle_green)
                    .into(holder.imPoster); // устанавливаем изображение по умолчанию
        }

        Double ratingKp = movie.getRating().getKpRating();

        if (ratingKp != null) {
            int backgroundId;
            if (ratingKp >= 8) backgroundId = R.drawable.circle_green;
            else if (ratingKp >= 5 & ratingKp < 8) backgroundId = R.drawable.circle_orange;
            else backgroundId = R.drawable.circle_red;
            holder.tvKpRating.setBackgroundResource(backgroundId);
            holder.tvKpRating.setText(String.valueOf(ratingKp));
        } else {
            holder.tvKpRating.setBackgroundResource(R.drawable.circle_red);
            holder.tvKpRating.setText("");
        }

        Double ratingImdb = movie.getRating() != null ? movie.getRating().getImdbRating() : null;

        if (ratingImdb != null) {
            int backgroundId2;
            if (ratingImdb >= 8) backgroundId2 = R.drawable.circle_green;
            else if (ratingImdb >= 5 & ratingImdb < 8) backgroundId2 = R.drawable.circle_orange;
            else backgroundId2 = R.drawable.circle_red;

            holder.tvImdbRating.setBackgroundResource(backgroundId2);
            holder.tvImdbRating.setText(String.valueOf(ratingImdb));
        } else {
            holder.tvImdbRating.setBackgroundResource(R.drawable.circle_red);
            holder.tvImdbRating.setText("");
        }

        if (position >= movies.size() - 6 & onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMovie.setOnClickMovie(movie);
            }
        });
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface OnReachEndListener {
        void onReachEnd();
    }

    interface OnClickMovie {
        void setOnClickMovie(Movie movie);//?
    }
}
/* extends RecyclerView.Adapter */