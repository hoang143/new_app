package com.example.readpaperapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readpaperapp.R;
import com.example.readpaperapp.databinding.ArticleListItemBinding;
import com.example.readpaperapp.model.Article;
import com.example.readpaperapp.view.ArticleActivity;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private Context context;
    private ArrayList<Article> articleArrayList;

    public ArticleAdapter(Context context, ArrayList<Article> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticleListItemBinding articleListItemBinding  = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()),
                        R.layout.article_list_item,
                        parent,
                        false);

        return new ArticleViewHolder(articleListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleArrayList.get(position);
        holder.articleListItemBinding.setArticle(article);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }


    // View Holder Class
    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        private ArticleListItemBinding articleListItemBinding;

        public ArticleViewHolder(ArticleListItemBinding articleListItemBinding) {
            super(articleListItemBinding.getRoot());
            this.articleListItemBinding = articleListItemBinding;

            articleListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        Article selectedArticle = articleArrayList.get(position);
                        Intent i = new Intent(context, ArticleActivity.class);
                        i.putExtra("article", selectedArticle);
                        context.startActivity(i);

                    }


                }
            });

        }
    }
}
