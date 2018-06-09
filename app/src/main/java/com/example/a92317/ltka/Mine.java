package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

public class Mine extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_home=(Button)findViewById(R.id.home);
        Button button_my_view=(Button)findViewById(R.id.my_view);
        Button button_text=(Button)findViewById(R.id.text);
        Button button_share=(Button)findViewById(R.id.share);
        Button button_settings=(Button)findViewById(R.id.settings);
        Button button_about_us=(Button)findViewById(R.id.about_us);

        button_back.setOnClickListener(this);
        button_home.setOnClickListener(this);
        button_my_view.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_share.setOnClickListener(this);
        button_settings.setOnClickListener(this);
        button_about_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog=new AlertDialog.Builder(Mine.this);
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
                Intent intentH=new Intent(Mine.this,MainActivity.class);
                startActivity(intentH);
                break;
            case R.id.my_view:
                Intent intentV=new Intent(Mine.this,MyView.class);
                startActivity(intentV);
                break;
            case R.id.text:
                Intent intentT=new Intent(Mine.this,Text.class);
                startActivity(intentT);
                break;
            case R.id.share:
                break;
            case R.id.settings:
                break;
            case R.id.about_us:
                Intent intentA=new Intent(Mine.this,AboutUs.class);
                startActivity(intentA);
            default:
                break;
        }
    }
}
