package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private List<Article> articleList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏原标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //文章
        initArticles();
        ArticleAdapter articleAdapter=new ArticleAdapter(MainActivity.this,R.layout.article_item,articleList);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(articleAdapter);
        listview.setOnItemClickListener(this);

        //按钮事件
        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_my_view=(Button)findViewById(R.id.my_view);
        Button button_text=(Button)findViewById(R.id.text);
        Button button_mine=(Button)findViewById(R.id.mine);
        Button button_keep_accounts=(Button)findViewById(R.id.keep_accounts);

        button_back.setOnClickListener(this);
        button_my_view.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_mine.setOnClickListener(this);
        button_keep_accounts.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        Intent intent = new Intent(MainActivity.this, demos[position].demoClass);
        startActivity(intent);
    }
    public static class DemoInfo {
        public final Class<?> demoClass;

        public DemoInfo(Class<?> demoClass) {
            this.demoClass = demoClass;
        }
    }
    public static final DemoInfo[] demos = {
            new DemoInfo(Article1.class),
            new DemoInfo(Article2.class),
            new DemoInfo(Article3.class),
            new DemoInfo(Article4.class),
            new DemoInfo(Article5.class),
            new DemoInfo(Article6.class),
            new DemoInfo(Article7.class),
            new DemoInfo(Article8.class),
            new DemoInfo(Article9.class)
    };

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
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
            case R.id.my_view:
                Intent intentV=new Intent(MainActivity.this,MyView.class);
                startActivity(intentV);
                break;
            case R.id.text:
                Intent intentT=new Intent(MainActivity.this,Text.class);
                startActivity(intentT);
                break;
            case R.id.mine:
                Intent intentM=new Intent(MainActivity.this,Mine.class);
                startActivity(intentM);
                break;
            case R.id.keep_accounts:
                Intent intentK=new Intent(MainActivity.this,AddOne.class);
                startActivity(intentK);
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
