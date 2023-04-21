package com.example.readpaperapp.service;

import com.example.readpaperapp.model.ArticleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleDataService {
    //retrofit interface
    //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=02f89eb2c3264861b8dadf46828b4a2e

    @GET("top-headlines")
    Call<ArticleModel> getTopHeadlinesArticle(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey
    );
}
