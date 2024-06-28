package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReviewList implements Serializable {
    @SerializedName("docs")
    private final List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public ReviewList(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ReviewList{" +
                "reviews=" + reviews +
                '}';
    }
}
