package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Text extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{


    private List<Article> articleList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        //文章
        initArticles();
        ArticleAdapter articleAdapter=new ArticleAdapter(Text.this,R.layout.article_item,articleList);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(articleAdapter);
        listview.setOnItemClickListener(this);

        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_home=(Button)findViewById(R.id.home);
        Button button_my_view=(Button)findViewById(R.id.my_view);
        Button button_mine=(Button)findViewById(R.id.mine);

        button_back.setOnClickListener(this);
        button_home.setOnClickListener(this);
        button_my_view.setOnClickListener(this);
        button_mine.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        Intent intent = new Intent(Text.this, MainActivity.demos[position].demoClass);
        startActivity(intent);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog=new AlertDialog.Builder(Text.this);
                dialog.setTitle("提示");
                dialog.setMessage("确定退出吗");
                dialog.setCancelable(false);
                dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCollector.finishAll();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });;
                dialog.show();
                break;
            case R.id.home:
                Intent intentH=new Intent(Text.this,MainActivity.class);
                startActivity(intentH);
                break;
            case R.id.my_view:
                Intent intentV=new Intent(Text.this,MyView.class);
                startActivity(intentV);
                break;
            case R.id.mine:
                Intent intentM=new Intent(Text.this,Mine.class);
                startActivity(intentM);
                break;
            default:
                break;
        }
    }

    private void initArticles(){
        Article article1=new Article((String) this.getResources().getText(R.string.article1_name),
                (String) this.getResources().getText(R.string.article1_author));
        Article article2=new Article((String) this.getResources().getText(R.string.article2_name),
                (String) this.getResources().getText(R.string.article2_author));
        Article article3=new Article((String) this.getResources().getText(R.string.article3_name),
                (String) this.getResources().getText(R.string.article3_author));
        Article article4=new Article((String) this.getResources().getText(R.string.article4_name),
                (String) this.getResources().getText(R.string.article4_author));
        Article article5=new Article((String) this.getResources().getText(R.string.article5_name),
                (String) this.getResources().getText(R.string.article5_author));
        Article article6=new Article((String) this.getResources().getText(R.string.article6_name),
                (String) this.getResources().getText(R.string.article6_author));
        Article article7=new Article((String) this.getResources().getText(R.string.article7_name),
                (String) this.getResources().getText(R.string.article7_author));
        Article article8=new Article((String) this.getResources().getText(R.string.article8_name),
                (String) this.getResources().getText(R.string.article8_author));
        Article article9=new Article((String) this.getResources().getText(R.string.article9_name),
                (String) this.getResources().getText(R.string.article9_author) );

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
