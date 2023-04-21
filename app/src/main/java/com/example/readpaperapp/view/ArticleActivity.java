package com.example.readpaperapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.readpaperapp.R;
import com.example.readpaperapp.databinding.ActivityMainBinding;
import com.example.readpaperapp.model.Article;

public class ArticleActivity extends AppCompatActivity {
    WebView webView;

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent i = getIntent();
        if (i != null) {
            article = i.getParcelableExtra("article");
            getSupportActionBar().setTitle(article.getTitle());
        }
        webView = findViewById(R.id.web_view);
        webView.loadUrl(article.getUrl());
    }
}