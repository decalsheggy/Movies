package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrailerResponse implements Serializable {
    @SerializedName("videos")
    private TrailerList trailerList;

    public TrailerList getTrailerList() {
        return trailerList;
    }

    public TrailerResponse(TrailerList trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailerList=" + trailerList +
                '}';
    }
}
