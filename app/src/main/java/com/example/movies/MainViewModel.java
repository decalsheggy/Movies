package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private int page = 1;
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LiveData<List<Movie>> getAllMovies() {//чтобы снаружи из активити нельзя было установить новые данные
        return movies;
    }
    public LiveData<Boolean> getIsLoading() {//делаем неизменяемым
        return isLoading;
    }
    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public void loadMovies() {//Метод, который загружает данные в movies
        Boolean loading = isLoading.getValue();
        if (loading != null & loading) {
            return;
        }
        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isLoading.setValue(true);
                    }
                })
                .doAfterTerminate(new Action() {//после завершения
                    @Override
                    public void run() throws Throwable {
                        isLoading.setValue(false);
                    }
                })
                .subscribe(new Consumer<AllMovies>() {
                               @Override
                               public void accept(AllMovies allMovies) throws Throwable {
                                   List<Movie> loadedMovies = movies.getValue();
                                   if (loadedMovies != null) {
                                       loadedMovies.addAll(allMovies.getAllMovies());//???
                                       movies.setValue(loadedMovies);
                                   } else movies.setValue(allMovies.getAllMovies());//???
                                   page++;
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d("MainViewModel", throwable.toString());
                            }
                        });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
