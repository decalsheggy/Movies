package com.example.movies;

import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {//объявляем все методы по работе с интернетом

    //тип запроса в документации(указываем end-point)
    //@GET("movie?token=RXGN63V-J9HMTE6-J7J2MX3-S8ZGM66&rating.kp=5-9&rating.imdb=5.5-10&votes.kp=1300-60000&votes.ageRating=12-18&limit=20&type=anime")
    @GET("movie?rating.kp=5-9&rating.imdb=5.5-10&votes.kp=1300-60000&votes.ageRating=12-18&limit=20&type=anime")
    @Headers("X-API-KEY:" + "RXGN63V-J9HMTE6-J7J2MX3-S8ZGM66")
    //запрос, который возвращает список фильмов
    Single<AllMovies> loadMovies(@Query("page") int page);//можем передавать запросы динамически
    @GET("movie/{id}")//6636?token=RXGN63V-J9HMTE6-J7J2MX3-S8ZGM66"
    @Headers("X-API-KEY:" + "RXGN63V-J9HMTE6-J7J2MX3-S8ZGM66")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);
    @GET("review")
    @Headers("X-API-KEY:" + "RXGN63V-J9HMTE6-J7J2MX3-S8ZGM66")
    Single<ReviewList> loadReviews(@Query("movieId") int movieId);
}
