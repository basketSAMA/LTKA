package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        userImage = (ImageView)findViewById(R.id.display_user_image);
        userName = (EditText)findViewById(R.id.display_user_name);
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        int uImage = preferences.getInt("uImage",R.drawable.d1);
        String uName = preferences.getString("uName","user");
        Drawable d = this.getResources().getDrawable(uImage);
        userImage.setImageDrawable(d);
        userName.setText(uName);

        Button save = (Button)findViewById(R.id.settings_save);
        Button back = (Button)findViewById(R.id.title_back);
        ImageButton d1 = (ImageButton) findViewById(R.id.image_d1);
        ImageButton d2 = (ImageButton) findViewById(R.id.image_d2);
        ImageButton d3 = (ImageButton) findViewById(R.id.image_d3);
        ImageButton d10 = (ImageButton) findViewById(R.id.image_d10);
        ImageButton d11 = (ImageButton) findViewById(R.id.image_d11);
        ImageButton d12 = (ImageButton) findViewById(R.id.image_d12);
        ImageButton d7 = (ImageButton) findViewById(R.id.image_d7);
        ImageButton d8 = (ImageButton) findViewById(R.id.image_d8);
        ImageButton d18 = (ImageButton) findViewById(R.id.image_d18);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);
        d10.setOnClickListener(this);
        d11.setOnClickListener(this);
        d12.setOnClickListener(this);
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
            case R.id.image_d7:
                uImage = R.drawable.d7;
                show();
                break;
            case R.id.image_d8:
                uImage = R.drawable.d8;
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
}
