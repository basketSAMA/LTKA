package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

public class AddOne extends BaseActivity implements View.OnClickListener {

    private int ADD = 1, SUBT = 2;

    private TextView displaySum;
    private EditText remarks;
    private String sum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one);

        displaySum = (TextView) findViewById(R.id.display_sum);
        remarks = (EditText) findViewById(R.id.add_one_remarks);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("signal", false)) {
            int position = intent.getIntExtra("billPosition", -1);
            Bill bill = MyView.bills.get(position);
            sum += Double.toString(bill.getSum());
            displaySum.setText(sum);
            remarks.setText(bill.getRemarks());
        }

        Button back = (Button) findViewById(R.id.title_back);
        Button number0 = (Button) findViewById(R.id.add_one_0);
        Button number1 = (Button) findViewById(R.id.add_one_1);
        Button number2 = (Button) findViewById(R.id.add_one_2);
        Button number3 = (Button) findViewById(R.id.add_one_3);
        Button number4 = (Button) findViewById(R.id.add_one_4);
        Button number5 = (Button) findViewById(R.id.add_one_5);
        Button number6 = (Button) findViewById(R.id.add_one_6);
        Button number7 = (Button) findViewById(R.id.add_one_7);
        Button number8 = (Button) findViewById(R.id.add_one_8);
        Button number9 = (Button) findViewById(R.id.add_one_9);
        Button dot = (Button) findViewById(R.id.add_one_dot);
        Button add = (Button) findViewById(R.id.add_one_add);
        Button subt = (Button) findViewById(R.id.add_one_subt);
        Button delete = (Button) findViewById(R.id.add_one_delete);
        Button sava = (Button) findViewById(R.id.add_one_save);

        back.setOnClickListener(this);
        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        dot.setOnClickListener(this);
        add.setOnClickListener(this);
        subt.setOnClickListener(this);
        delete.setOnClickListener(this);
        sava.setOnClickListener(this);
    }

    private double getSumByDouble(String sum) {
        if (sum.endsWith(".") || sum.endsWith("+") || sum.endsWith("-"))
            sum = sum.substring(0, sum.length() - 1);

        int base = 0;
        int state = ADD;
        double temp = 0;
        double ans = 0;
        for (int i = 0; i < sum.length(); i++) {
            if (sum.charAt(i) == '+') {
                temp = Double.parseDouble(sum.substring(i - base, i));
                base = 0;

                if (state == ADD)
                    ans += temp;
                else
                    ans -= temp;
                state = ADD;
            } else if (sum.charAt(i) == '-') {
                temp = Double.parseDouble(sum.substring(i - base, i));
                base = 0;

                if (state == ADD)
                    ans += temp;
                else
                    ans -= temp;
                state = SUBT;
            } else {
                base++;
            }
        }
        temp = Double.parseDouble(sum.substring(sum.length() - base, sum.length()));
        if (state == ADD)
            ans += temp;
        else
            ans -= temp;

        return ans;
    }

    private Boolean checkDecimalFull() {
        Boolean flagFullDecimal = false;
        if (sum.length() > 3)
            if (sum.charAt(sum.length() - 3) == '.' && sum.charAt(sum.length() - 1) != '+' && sum.charAt(sum.length() - 1) != '-')
                flagFullDecimal = true;
        return flagFullDecimal;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_one_0:
                if (!checkDecimalFull()) {
                    if (!sum.startsWith("0"))
                        sum += "0";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_1:
                if (!checkDecimalFull()) {
                    sum += "1";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_2:
                if(!checkDecimalFull()) {
                    sum += "2";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_3:
                if (!checkDecimalFull()) {
                    sum += "3";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_4:
                if (!checkDecimalFull()) {
                    sum += "4";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_5:
                if (!checkDecimalFull()) {
                    sum += "5";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_6:
                if (!checkDecimalFull()) {
                    sum += "6";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_7:
                if (!checkDecimalFull()) {
                    sum += "7";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_8:
                if (!checkDecimalFull()) {
                    sum += "8";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_9:
                if (!checkDecimalFull()) {
                    sum += "9";
                    displaySum.setText(sum);
                }
                break;
            case R.id.add_one_dot:
                if (!sum.endsWith(".")) {
                    if (sum.length() < 3) {
                        if (sum.endsWith("+") || sum.endsWith("-"))
                            sum += "0.";
                        else
                            sum += ".";
                        if (sum.startsWith("."))
                            sum = "0.";
                    } else {
                        if (sum.charAt(sum.length() - 2) != '.' && sum.charAt(sum.length() - 3) != '.') {
                            if (sum.endsWith("+") || sum.endsWith("-"))
                                sum += "0.";
                            else
                                sum += ".";
                        }
                    }
                }
                displaySum.setText(sum);
                break;
            case R.id.add_one_add:
                if (sum.endsWith("+") || sum.endsWith("-"))
                    sum = sum.substring(0, sum.length() - 1) + "+";
                else {
                    if (sum.endsWith("."))
                        sum += "0+";
                    else
                        sum += "+";
                    if (sum.startsWith("+"))
                        sum = "0+";
                }
                displaySum.setText(sum);
                break;
            case R.id.add_one_subt:
                if (sum.endsWith("+") || sum.endsWith("-"))
                    sum = sum.substring(0, sum.length() - 1) + "-";
                else {
                    if (sum.endsWith("."))
                        sum += "0-";
                    else
                        sum += "-";
                    if (sum.startsWith("-"))
                        sum = "0-";
                }
                displaySum.setText(sum);
                break;
            case R.id.add_one_delete:
                if (!sum.isEmpty()) {
                    sum = sum.substring(0, sum.length() - 1);
                }
                displaySum.setText(sum);
                break;
            case R.id.add_one_save:
                AlertDialog.Builder dialogS = new AlertDialog.Builder(AddOne.this);
                dialogS.setTitle("提示");
                dialogS.setMessage("确定保存吗");
                dialogS.setCancelable(false);
                dialogS.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!sum.isEmpty() && getSumByDouble(sum) > 0) {

                            Bill bill = new Bill();
                            bill.setTime();
                            bill.setRemarks(remarks.getText().toString());
                            bill.setSum(getSumByDouble(sum));
                            bill.save();

                            Intent intent = getIntent();
                            if (intent.getBooleanExtra("signal", false)) {
                                int billPosition = intent.getIntExtra("billPosition", 0);
                                Bill oldBill = MyView.bills.get(billPosition);
                                int oldBillId = oldBill.getId();
                                DataSupport.deleteAll(Bill.class, "id = ?", Integer.toString(oldBillId));
                            }

                            Toast.makeText(AddOne.this, "保存成功", Toast.LENGTH_SHORT).show();
                            Intent intentV = new Intent(AddOne.this, MyView.class);
                            startActivity(intentV);
                        } else {
                            Toast.makeText(AddOne.this, "保存失败", Toast.LENGTH_SHORT).show();
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
            case R.id.title_back:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddOne.this);
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
            default:
                break;
        }
    }
}

