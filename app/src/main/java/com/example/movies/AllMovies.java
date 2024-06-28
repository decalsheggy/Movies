package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMovies {
    @SerializedName("docs")//Retrofit должен понять, что по ключу docs нужно положить значение в переменную movies
    private final List<Movie> movies;

    public AllMovies(List<Movie> allMovies) {
        this.movies = allMovies;
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "AllMovies{" +
                "movies=" + movies +
                '}';
    }
}
