package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("review")
    private String review;
    @SerializedName("author")
    private String authorName;
    @SerializedName("type")
    private String type;

    public String getReview() {
        return review;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getType() {
        return type;
    }

    public Review(String review, String authorName, String type) {
        this.review = review;
        this.authorName = authorName;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                '}';
    }
}
