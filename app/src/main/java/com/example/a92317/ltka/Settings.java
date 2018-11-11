package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Settings extends BaseActivity implements View.OnClickListener {

    private int uImage;
    private String uName;

    private ImageView userImage;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userName = (EditText)findViewById(R.id.display_user_name);
        userImage = (ImageView)findViewById(R.id.display_user_image);
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        uName = preferences.getString("uName","user");
        uImage = preferences.getInt("uImage",R.drawable.d1);
        userName.setText(uName);
        userName.setSelection(uName.length());
        Drawable d = this.getResources().getDrawable(uImage);
        userImage.setImageDrawable(d);

        Button save = (Button)findViewById(R.id.settings_save);
        Button back = (Button)findViewById(R.id.title_back);
        ImageButton d1 = (ImageButton) findViewById(R.id.image_d1);
        ImageButton d2 = (ImageButton) findViewById(R.id.image_d2);
        ImageButton d3 = (ImageButton) findViewById(R.id.image_d3);
        ImageButton d4 = (ImageButton) findViewById(R.id.image_d4);
        ImageButton d5 = (ImageButton) findViewById(R.id.image_d5);
        ImageButton d6 = (ImageButton) findViewById(R.id.image_d6);
        ImageButton d7 = (ImageButton) findViewById(R.id.image_d7);
        ImageButton d8 = (ImageButton) findViewById(R.id.image_d8);
        ImageButton d9 = (ImageButton) findViewById(R.id.image_d9);
        ImageButton d10 = (ImageButton) findViewById(R.id.image_d10);
        ImageButton d11 = (ImageButton) findViewById(R.id.image_d11);
        ImageButton d12 = (ImageButton) findViewById(R.id.image_d12);
        ImageButton d13 = (ImageButton) findViewById(R.id.image_d13);
        ImageButton d14 = (ImageButton) findViewById(R.id.image_d14);
        ImageButton d15 = (ImageButton) findViewById(R.id.image_d15);
        ImageButton d16 = (ImageButton) findViewById(R.id.image_d16);
        ImageButton d17 = (ImageButton) findViewById(R.id.image_d17);
        ImageButton d18 = (ImageButton) findViewById(R.id.image_d18);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        d9.setOnClickListener(this);
        d10.setOnClickListener(this);
        d11.setOnClickListener(this);
        d12.setOnClickListener(this);
        d13.setOnClickListener(this);
        d14.setOnClickListener(this);
        d15.setOnClickListener(this);
        d16.setOnClickListener(this);
        d17.setOnClickListener(this);
        d18.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Settings.this);
                dialog.setTitle("提示");
                dialog.setMessage("确定放弃编辑吗");
                dialog.setCancelable(false);
                dialog.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.settings_save:
                AlertDialog.Builder dialogS = new AlertDialog.Builder(Settings.this);
                dialogS.setTitle("提示");
                dialogS.setMessage("确定保存吗");
                dialogS.setCancelable(false);
                dialogS.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        uName = userName.getText().toString();
                        if(uName.isEmpty()){
                            Toast.makeText(Settings.this,"保存失败",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(Settings.this,"保存成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("uImage",uImage);
                            intent.putExtra("uName",uName);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }
                });
                dialogS.setNegativeButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogS.show();
                break;
            case R.id.image_d1:
                uImage = R.drawable.d1;
                show();
                break;
            case R.id.image_d2:
                uImage = R.drawable.d2;
                show();
                break;
            case R.id.image_d3:
                uImage = R.drawable.d3;
                show();
                break;
            case R.id.image_d4:
                uImage = R.drawable.d4;
                show();
                break;
            case R.id.image_d5:
                uImage = R.drawable.d5;
                show();
                break;
            case R.id.image_d6:
                uImage = R.drawable.d6;
                show();
                break;
            case R.id.image_d7:
                uImage = R.drawable.d7;
                show();
                break;
            case R.id.image_d8:
                uImage = R.drawable.d8;
                show();
                break;
            case R.id.image_d9:
                uImage = R.drawable.d9;
                show();
                break;
            case R.id.image_d10:
                uImage = R.drawable.d10;
                show();
                break;
            case R.id.image_d11:
                uImage = R.drawable.d11;
                show();
                break;
            case R.id.image_d12:
                uImage = R.drawable.d12;
                show();
                break;
            case R.id.image_d13:
                uImage = R.drawable.d13;
                show();
                break;
            case R.id.image_d14:
                uImage = R.drawable.d14;
                show();
                break;
            case R.id.image_d15:
                uImage = R.drawable.d15;
                show();
                break;
            case R.id.image_d16:
                uImage = R.drawable.d16;
                show();
                break;
            case R.id.image_d17:
                uImage = R.drawable.d17;
                show();
                break;
            case R.id.image_d18:
                uImage = R.drawable.d18;
                show();
                break;
        }
    }

    private void show(){
        Drawable d = this.getResources().getDrawable(uImage);
        userImage.setImageDrawable(d);
    }

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(Settings.this);
            dialog.setTitle("提示");
            dialog.setMessage("确定放弃编辑吗");
            dialog.setCancelable(false);
            dialog.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        }
        return false;
    }
}
