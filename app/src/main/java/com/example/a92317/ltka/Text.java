package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class Text extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener,View.OnTouchListener,GestureDetector.OnGestureListener{

    private LinearLayout ll;
    private ListView lv;
    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ll = (LinearLayout) findViewById(R.id.layout_text);
        ll.setOnTouchListener(this);
        ll.setLongClickable(true);
        lv = (ListView) findViewById(R.id.list_view);
        lv.setOnTouchListener(this);
        lv.setLongClickable(true);
        gd = new GestureDetector((GestureDetector.OnGestureListener) this);

        //文章
        InitArticles temp = InitArticles.initArticles(this);
        List<Article> articleList = temp.getArticleList();
        ArticleAdapter articleAdapter=new ArticleAdapter(Text.this,R.layout.article_item, articleList);
        lv.setAdapter(articleAdapter);

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
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.mine:
                Intent intentM=new Intent(Text.this,Mine.class);
                startActivity(intentM);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    @Override
    public boolean onDown(MotionEvent e) {
        lv.setOnItemClickListener(this);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final int FLING_MIN_DISTANCE = 400;
        final int FLING_MIN_VELOCITY = 200;
        //左
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intentV = new Intent(Text.this, Mine.class);
            startActivity(intentV);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        // 右
        if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intentM = new Intent(Text.this, MyView.class);
            startActivity(intentM);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gd.onTouchEvent(event);
    }
}
