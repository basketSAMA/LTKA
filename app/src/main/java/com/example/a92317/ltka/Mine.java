package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mine extends BaseActivity implements View.OnClickListener{

    private ImageView userImage;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        userImage = (ImageView)findViewById(R.id.user_image);
        userName = (TextView)findViewById(R.id.user_name);
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        int uImage = preferences.getInt("uImage",R.drawable.d1);
        String uName = preferences.getString("uName","user");
        Drawable d = this.getResources().getDrawable(uImage);
        userImage.setImageDrawable(d);
        userName.setText(uName);

        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_home=(Button)findViewById(R.id.home);
        Button button_my_view=(Button)findViewById(R.id.my_view);
        Button button_text=(Button)findViewById(R.id.text);
        Button button_settings=(Button)findViewById(R.id.settings);
        Button button_about_us=(Button)findViewById(R.id.about_us);

        button_back.setOnClickListener(this);
        button_home.setOnClickListener(this);
        button_my_view.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_settings.setOnClickListener(this);
        button_about_us.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    int uImage;
                    String uName;
                    uImage = data.getIntExtra("uImage",R.drawable.d1);
                    uName = data.getStringExtra("uName");

                    userImage = (ImageView)findViewById(R.id.user_image);
                    userName = (TextView)findViewById(R.id.user_name);
                    Drawable d = this.getResources().getDrawable(uImage);
                    userImage.setImageDrawable(d);
                    userName.setText(uName);

                    SharedPreferences.Editor editor;
                    editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();

                    editor.putInt("uImage",uImage);
                    editor.putString("uName",uName);
                    editor.apply();
                }
                break;
        }
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
            case R.id.settings:
                Intent intentS=new Intent(Mine.this,Settings.class);
                startActivityForResult(intentS,1);
                break;
            case R.id.about_us:
                Intent intentA=new Intent(Mine.this,AboutUs.class);
                startActivity(intentA);
            default:
                break;
        }
    }
}
