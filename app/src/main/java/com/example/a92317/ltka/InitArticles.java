package com.example.a92317.ltka;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class InitArticles {

    private List<Article> articleList;

    static private InitArticles singletonInstance = null;

    static public InitArticles initArticles(Context context) {

        if (singletonInstance == null) {
            singletonInstance = new InitArticles(context);
        }

        return singletonInstance;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    private InitArticles(Context context){

        Resources res = context.getResources();

        Article article1=new Article(res.getString(R.string.article1_name),
                res.getString(R.string.article1_author),
                res.getString(R.string.article1_contents));
        Article article2=new Article(res.getString(R.string.article2_name),
                res.getString(R.string.article2_author),
                res.getString(R.string.article2_contents));
        Article article3=new Article(res.getString(R.string.article3_name),
                res.getString(R.string.article3_author),
                res.getString(R.string.article3_contents));
        Article article4=new Article(res.getString(R.string.article4_name),
                res.getString(R.string.article4_author),
                res.getString(R.string.article4_contents));
        Article article5=new Article(res.getString(R.string.article5_name),
                res.getString(R.string.article5_author),
                res.getString(R.string.article5_contents));
        Article article6=new Article(res.getString(R.string.article6_name),
                res.getString(R.string.article6_author),
                res.getString(R.string.article6_contents));
        Article article7=new Article(res.getString(R.string.article7_name),
                res.getString(R.string.article7_author),
                res.getString(R.string.article7_contents));
        Article article8=new Article(res.getString(R.string.article8_name),
                res.getString(R.string.article8_author),
                res.getString(R.string.article8_contents));
        Article article9=new Article(res.getString(R.string.article9_name),
                res.getString(R.string.article9_author),
                res.getString(R.string.article9_contents));

        articleList = new ArrayList<>();

        articleList.add(article1);
        articleList.add(article2);
        articleList.add(article3);
        articleList.add(article4);
        articleList.add(article5);
        articleList.add(article6);
        articleList.add(article7);
        articleList.add(article8);
        articleList.add(article9);
    }
}
