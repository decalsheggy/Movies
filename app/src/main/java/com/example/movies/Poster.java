package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Poster implements Serializable {
    @SerializedName("url")
    private String urlPoster;

    public Poster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "urlPoster='" + urlPoster + '\'' +
                '}';
    }
}
