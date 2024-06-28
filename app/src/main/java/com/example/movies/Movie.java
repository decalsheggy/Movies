package com.example.movies;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favourite_movies")
public class Movie implements Serializable {//реализуем интерфейс, при котором экземпляры этого класса можно
    // сериализовать(преобразовать в поток байтов, чтобы эти объекты можно было использовать, например в передаче интента)
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("year")
    private int year;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @Embedded//Возможность сохранить объект этого типа в базу данных
    @SerializedName("rating")
    private Rating rating;
    @Embedded
    @SerializedName("poster")
    private Poster poster;

    public Movie(int id, int year, String name, String description, Rating rating, Poster poster) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Rating getRating() {
        return rating;
    }

    public Poster getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", poster=" + poster +
                '}';
    }
}
