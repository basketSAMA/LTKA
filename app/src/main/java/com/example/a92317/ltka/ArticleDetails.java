package com.example.a92317.ltka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ArticleDetails extends BaseActivity{

    private TextView articleName;
    private TextView articleAuthor;
    private TextView articleContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_item_details);

        articleName=(TextView)findViewById(R.id.article_name);
        articleAuthor=(TextView)findViewById(R.id.article_author);
        articleContents=(TextView)findViewById(R.id.article_contents);

        Intent intent = getIntent();
        final int articlePosition = intent.getIntExtra("articlePosition",0);

        InitArticles temp = InitArticles.initArticles(this);
        List<Article> articleList = temp.getArticleList();
        Article article = articleList.get(articlePosition);

        articleName.setText(article.getName());
        articleAuthor.setText(article.getAuthor());
        articleContents.setText(article.getContents());

        Button button_back=(Button)findViewById(R.id.title_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
