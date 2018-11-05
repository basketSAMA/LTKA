package com.example.a92317.ltka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.iflytek.cloud.SpeechConstant.APPID;

public class AddOne extends BaseActivity implements View.OnClickListener {

    private int ADD = 1, SUBT = 2;

    private TextView displaySum;
    private EditText remarks;
    private Button speech;
    private TextView displayClassification;
    private View displayClassificationColor;

//    // 听写结果字符串（多个Json的列表字符串）
//    private String dictationResultStr = "[";

    private String sum = "";
    private BillClassification classification;

    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one);
//        // 语音配置对象初始化
//        SpeechUtility.createUtility(AddOne.this, APPID
//                + "=5bdfa742");

        displaySum = (TextView) findViewById(R.id.display_sum);
        remarks = (EditText) findViewById(R.id.add_one_remarks);
        speech = (Button) findViewById(R.id.add_one_speech);
        displayClassification = (TextView)findViewById(R.id.add_one_classification);
        displayClassificationColor = (View) findViewById(R.id.add_one_color);

        speech.setOnClickListener(this);

        Resources res = this.getResources();
        classification = new BillClassification(res.getString(R.string.classification_diet), res.getColor(R.color.colorDiet));

        final List<BillClassification> billClassificationList;
        billClassificationList = new ArrayList<>();
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_diet),res.getColor(R.color.colorDiet)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_study),res.getColor(R.color.colorStudy)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_entertainment),res.getColor(R.color.colorEntertainment)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_traffic),res.getColor(R.color.colorTraffic)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_health),res.getColor(R.color.colorHealth)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_house),res.getColor(R.color.colorHouse)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_favor),res.getColor(R.color.colorFavor)));
        billClassificationList.add(new BillClassification(res.getString(R.string.classification_else),res.getColor(R.color.colorElse)));

        bmb = (BoomMenuButton) findViewById(R.id.add_one_bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalText(billClassificationList.get(i).getClassificationName())
                    .normalTextColorRes(R.color.colorWhite)
                    .textSize(15)
                    .textGravity(Gravity.CENTER)
                    .normalColor(billClassificationList.get(i).getClassificationColor())
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            classification.setClassificationName(billClassificationList.get(index).getClassificationName());
                            classification.setClassificationColor(billClassificationList.get(index).getClassificationColor());
                            displayClassification.setText(classification.getClassificationName());
                            displayClassificationColor.setBackgroundColor(classification.getClassificationColor());
                        }
                    });
            bmb.addBuilder(builder);
        }
        bmb.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("signal", false)) {
            int position = intent.getIntExtra("billPosition", -1);
            Bill bill = MyView.bills.get(position);
            sum += Double.toString(bill.getSum());
            displaySum.setText(sum);
            remarks.setText(bill.getRemarks());
            remarks.setSelection(bill.getRemarks().length());
            displayClassification.setText(bill.getClassificationName());
            displayClassification.setTextColor(res.getColor(R.color.colorWhite));
            displayClassificationColor.setBackgroundColor(bill.getSetClassificationColor());
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
                            bill.setClassificationName(classification.getClassificationName());
                            bill.setSetClassificationColor(classification.getClassificationColor());
                            bill.save();

                            Intent intent = getIntent();
                            if (intent.getBooleanExtra("signal", false)) {
                                int billPosition = intent.getIntExtra("billPosition", 0);
                                Bill oldBill = MyView.bills.get(billPosition);
                                int oldBillId = oldBill.getId();
                                LitePal.deleteAll(Bill.class, "id = ?", Integer.toString(oldBillId));
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
            case R.id.add_one_bmb:
                bmb.boom();
                break;
            case R.id.add_one_speech:
//                dictationResultStr = "[";
//
//                // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
//                SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(AddOne.this, null);
//
//                // 交互动画
//                RecognizerDialog iatDialog = new RecognizerDialog(AddOne.this, null);
//
//                // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
//                mIat.setParameter(SpeechConstant.DOMAIN, "iat"); // domain:域名
//                mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//                mIat.setParameter(SpeechConstant.ACCENT, "mandarin"); // mandarin:普通话
//
//                //3.开始听写
//                iatDialog.setListener(new RecognizerDialogListener() {
//                    @Override
//                    public void onResult(RecognizerResult results, boolean isLast) {
//                        // TODO 自动生成的方法存根
//                        // Log.d("Result", results.getResultString());
//                        // contentTv.setText(results.getResultString());
//                        if (!isLast) {
//                            dictationResultStr += results.getResultString() + ",";
//                        } else {
//                            dictationResultStr += results.getResultString() + "]";
//                        }
//                        if (isLast) {
//                            // 解析Json列表字符串
//                            Gson gson = new Gson();
//                            List<DictationResult> dictationResultList = gson
//                                    .fromJson(dictationResultStr,
//                                            new TypeToken<List<DictationResult>>() {
//                                            }.getType());
//                            String finalResult = "";
//                            for (int i = 0; i < dictationResultList.size() - 1; i++) {
//                                finalResult += dictationResultList.get(i)
//                                        .toString();
//                            }
//                            remarks.setText(finalResult);
//
//                            //获取焦点
//                            remarks.requestFocus();
//
//                            //将光标定位到文字最后，以便修改
//                            remarks.setSelection(finalResult.length());
//
//                            Log.d("From reall phone", finalResult);
//                        }
//                    }
//                    @Override
//                    public void onError(SpeechError error) {
//                        // TODO 自动生成的方法存根
//                        error.getPlainDescription(true);
//                    }
//                });
//
//                // 开始听写
//                iatDialog.show();
                break;
            default:
                break;
        }
    }

    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
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
        }
        return false;
    }
}

