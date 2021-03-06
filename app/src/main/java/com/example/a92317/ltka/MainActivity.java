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

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener,View.OnTouchListener,GestureDetector.OnGestureListener {

    private LinearLayout ll;
    private ListView lv;
    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.layout_main);
        ll.setOnTouchListener(this);
        ll.setLongClickable(true);
        lv = (ListView) findViewById(R.id.list_view);
        lv.setOnTouchListener(this);
        lv.setLongClickable(true);
        gd = new GestureDetector((GestureDetector.OnGestureListener) this);

        LitePal.getDatabase();

        //文章
        InitArticles temp = InitArticles.initArticles(this);
        List<Article> articleList = temp.getArticleList();
        ArticleAdapter articleAdapter = new ArticleAdapter(MainActivity.this, R.layout.article_item, articleList);
        lv.setAdapter(articleAdapter);

        //按钮事件
        Button button_back = (Button) findViewById(R.id.title_back);
        Button button_my_view = (Button) findViewById(R.id.my_view);
        Button button_text = (Button) findViewById(R.id.text);
        Button button_mine = (Button) findViewById(R.id.mine);
        Button button_keep_accounts = (Button) findViewById(R.id.keep_accounts);

        button_back.setOnClickListener(this);
        button_my_view.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_mine.setOnClickListener(this);
        button_keep_accounts.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ArticleDetails.class);
        intent.putExtra("articlePosition", position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
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
                });
                ;
                dialog.show();
                break;
            case R.id.my_view:
                Intent intentV = new Intent(MainActivity.this, MyView.class);
                startActivity(intentV);
                break;
            case R.id.text:
                Intent intentT = new Intent(MainActivity.this, Text.class);
                startActivity(intentT);
                break;
            case R.id.mine:
                Intent intentM = new Intent(MainActivity.this, Mine.class);
                startActivity(intentM);
                break;
            case R.id.keep_accounts:
                Intent intentK = new Intent(MainActivity.this, AddOne.class);
                startActivity(intentK);
            default:
                break;
        }
    }

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
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
            });
            ;
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
            Intent intentV = new Intent(MainActivity.this, MyView.class);
            startActivity(intentV);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        // 右
        if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intentM = new Intent(MainActivity.this, Mine.class);
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
