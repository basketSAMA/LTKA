package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MyView extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener,View.OnTouchListener,GestureDetector.OnGestureListener{

    public static List<Bill> bills;

    private LinearLayout ll;
    private ListView lv;
    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);

        ll = (LinearLayout) findViewById(R.id.layout_my_view);
        ll.setOnTouchListener(this);
        ll.setLongClickable(true);
        lv=(ListView)findViewById(R.id.my_view_list_view);
        lv.setOnTouchListener(this);
        lv.setLongClickable(true);
        gd = new GestureDetector((GestureDetector.OnGestureListener) this);

        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_home=(Button)findViewById(R.id.home);
        Button button_text=(Button)findViewById(R.id.text);
        Button button_mine=(Button)findViewById(R.id.mine);

        button_back.setOnClickListener(this);
        button_home.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_mine.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        bills = DataSupport.findAll(Bill.class);
        List<Bill> reverseBills = new ArrayList<>();
        for(int i = bills.size() - 1; i >= 0; i--)
        {
            reverseBills.add(bills.get(i));
        }
        BillAdapter billAdapter=new BillAdapter(MyView.this,R.layout.bill_item,reverseBills);
        ListView listview = (ListView) findViewById(R.id.my_view_list_view);
        listview.setAdapter(billAdapter);
        listview.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, final int position, long id){

        Intent intent = new Intent(MyView.this,BillDetails.class);
        intent.putExtra("billPosition",MyView.bills.size() - 1 - position);
        startActivity(intent);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MyView.this);
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
                dialog.show();
                break;
            case R.id.home:
                Intent intentH=new Intent(MyView.this,MainActivity.class);
                startActivity(intentH);
                break;
            case R.id.text:
                Intent intentT=new Intent(MyView.this,Text.class);
                startActivity(intentT);
                break;
            case R.id.mine:
                Intent intentM=new Intent(MyView.this,Mine.class);
                startActivity(intentM);
                break;
            case R.id.fab:
                Intent intentA=new Intent(MyView.this,AddOne.class);
                startActivity(intentA);
                break;
            default:
                break;
        }
    }

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog=new AlertDialog.Builder(MyView.this);
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
        final int FLING_MIN_DISTANCE = 100;
        final int FLING_MIN_VELOCITY = 200;
        //左
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intentV = new Intent(MyView.this, Text.class);
            startActivity(intentV);
        }
        // 右
        if (e1.getX() - e2.getX() < FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intentM = new Intent(MyView.this, MainActivity.class);
            startActivity(intentM);
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gd.onTouchEvent(event);
    }
}
