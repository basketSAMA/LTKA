package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Text extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        //文章
        InitArticles temp = InitArticles.initArticles(this);
        List<Article> articleList = temp.getArticleList();
        ArticleAdapter articleAdapter=new ArticleAdapter(Text.this,R.layout.article_item, articleList);
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
        Intent intent = new Intent(Text.this, ArticleDetails.class);
        intent.putExtra("articlePosition",position);
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

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
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
        }
        return false;
    }
}
