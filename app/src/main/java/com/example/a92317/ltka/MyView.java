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
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyView extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    public static List<Bill> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        Button button_back=(Button)findViewById(R.id.title_back);
        Button button_home=(Button)findViewById(R.id.home);
        Button button_text=(Button)findViewById(R.id.text);
        Button button_mine=(Button)findViewById(R.id.mine);

        button_back.setOnClickListener(this);
        button_home.setOnClickListener(this);
        button_text.setOnClickListener(this);
        button_mine.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        bills = DataSupport.findAll(Bill.class);
        Toast.makeText(MyView.this,"更新数据",Toast.LENGTH_SHORT).show();
        BillAdapter billAdapter=new BillAdapter(MyView.this,R.layout.bill_item,bills);
        ListView listview = (ListView) findViewById(R.id.my_view_list_view);
        listview.setAdapter(billAdapter);
        listview.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, final int position, long id){

        Intent intent = new Intent(MyView.this,BillDetails.class);
        intent.putExtra("billPosition",position);
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
            default:
                break;
        }
    }
}
