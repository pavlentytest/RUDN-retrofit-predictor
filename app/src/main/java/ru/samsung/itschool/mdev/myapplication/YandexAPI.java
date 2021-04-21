package ru.samsung.itschool.mdev.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


// https://predictor.yandex.net/
// api/v1/predict.json/complete
// key=
// &q=Music%20of
// &lang=en
public interface YandexAPI {

    @GET("api/v1/predict.json/complete")
    Call<Model> predictor(@Query("key") String key,
                          @Query("q") String q,
                          @Query("lang") String lang);
}
