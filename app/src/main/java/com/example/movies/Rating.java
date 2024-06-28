package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private Double kpRating;
    @SerializedName("imdb")
    private Double imdbRating;
    public Rating(Double kpRating, Double imdbRating) {
        this.kpRating = kpRating;
        this.imdbRating = imdbRating;
    }

    public Double getKpRating() {
        return kpRating;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kpRating=" + kpRating +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
