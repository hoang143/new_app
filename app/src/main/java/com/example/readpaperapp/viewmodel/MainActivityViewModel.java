package com.example.readpaperapp.viewmodel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.readpaperapp.model.Article;
import com.example.readpaperapp.model.ArticleRepository;

import java.util.List;
public class MainActivityViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
    }

    //Live Data
    public LiveData<List<Article>> getAllArticles(){
        return articleRepository.getMutableLiveData();
    }
}
