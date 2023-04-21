package com.example.readpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.readpaperapp.adapter.ArticleAdapter;
import com.example.readpaperapp.databinding.ActivityMainBinding;
import com.example.readpaperapp.model.Article;
import com.example.readpaperapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Article> articles;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        getTopHeadlineArticles();
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getTopHeadlineArticles();
        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(R.color.purple_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTopHeadlineArticles();
            }
        });
    }

    private void getTopHeadlineArticles() {
//        Toast.makeText(MainActivity.this, "222: ", Toast.LENGTH_SHORT).show();
        mainActivityViewModel.getAllArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articlesFromLiveData) {
                articles = (ArrayList<Article>) articlesFromLiveData;
//                for (Article a: articles) {
//                    Toast.makeText(MainActivity.this, "222: " + a.getAuthor(), Toast.LENGTH_SHORT).show();
//                }
                ShowOnRecyclerView();
            }
        });

    }

    private void ShowOnRecyclerView() {
        recyclerView = activityMainBinding.rvArticles;
        articleAdapter = new ArticleAdapter(this, articles);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(articleAdapter);
        articleAdapter.notifyDataSetChanged();
    }
}