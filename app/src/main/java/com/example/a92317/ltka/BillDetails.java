package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

public class BillDetails extends BaseActivity {

    private TextView billSum;
    private TextView billRemarks;
    private TextView billTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        billSum = (TextView) findViewById(R.id.bill_sum);
        billRemarks = (TextView) findViewById(R.id.bill_remarks);
        billTime = (TextView) findViewById(R.id.bill_time);

        Intent intent = getIntent();
        final int billPosition = intent.getIntExtra("billPosition",0);

        Bill bill = MyView.bills.get(billPosition);
        billSum.setText(Double.toString(bill.getSum()));
        billRemarks.setText(bill.getRemarks());
        billTime.setText(bill.getTime());

        Button billRemove = (Button)findViewById(R.id.bill_remove);
        billRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder dialog=new AlertDialog.Builder(BillDetails.this);
                dialog.setTitle("提示");
                dialog.setMessage("确定删除吗");
                dialog.setCancelable(false);
                dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bill bill = MyView.bills.get(billPosition);
                        int billId = bill.getId();
                        DataSupport.deleteAll(Bill.class,"id = ?",Integer.toString(billId));
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
        });

        Button billUpdate = (Button)findViewById(R.id.bill_update);
        billUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BillDetails.this,AddOne.class);
                intent.putExtra("signal",true);
                intent.putExtra("billPosition",billPosition);
                startActivity(intent);
            }
        });

        Button back = (Button)findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
