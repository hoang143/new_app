package com.example.readpaperapp.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.readpaperapp.R;
import com.example.readpaperapp.service.ArticleDataService;
import com.example.readpaperapp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private ArrayList<Article> articles = new ArrayList<>();
    private MutableLiveData<List<Article>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    private String SOURCES = "techcrunch";

    public ArticleRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Article>> getMutableLiveData(){
        ArticleDataService articleDataService = RetrofitInstance.getService();
        Call<ArticleModel> call = articleDataService.getTopHeadlinesArticle(SOURCES, application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                ArticleModel articleModel = response.body();
                if(articleModel != null && articleModel.getArticles() != null){
                    articles = (ArrayList<Article>) articleModel.getArticles();
                    mutableLiveData.setValue(articles);
                }
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}
