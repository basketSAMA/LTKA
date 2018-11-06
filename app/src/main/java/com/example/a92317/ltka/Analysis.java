package com.example.a92317.ltka;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Analysis extends BaseActivity {

    private long firstInstallTimeByLong;
    private String firstInstallTimeByString;
    private long currTimeByLong;
    private String currTimeByString;

    double dd;
    private String timeIntervalByString;

    private List<BillPieData> bpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        initBillPieData();

        getFirstInstallTime();
        getCurrTime();

        long timeIntervalByLong = currTimeByLong - firstInstallTimeByLong;
        DecimalFormat df = new DecimalFormat("#.##");
        long t = timeIntervalByLong/3600000;  //小时取整
        dd = (double)t / 24;
        timeIntervalByString = df.format(dd)+ "天";

        setVp();

        Button button_back=(Button)findViewById(R.id.title_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    private void initBillPieData()
    {
        bpd = new ArrayList<>();

        String labels[] = {getString(R.string.classification_diet),
                getString(R.string.classification_study),
                getString(R.string.classification_entertainment),
                getString(R.string.classification_traffic),
                getString(R.string.classification_health),
                getString(R.string.classification_house),
                getString(R.string.classification_favor),
                getString(R.string.classification_else)};

        for(String label:labels)
        {
            List<Bill> someBills = LitePal.where("classificationName = ?",label).find(Bill.class);
            double sums = 0;
            int color = this.getResources().getColor(R.color.colorDiet);
            for(Bill bill:someBills)
            {
                sums += bill.getSum();
                color = bill.getSetClassificationColor();
            }
            if(sums != 0)
            {
                bpd.add(new BillPieData((float)sums,label,color));
            }
        }
    }

    private void setVp() {
        List<BillAnalysisData> list = new ArrayList<>();
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this,list);
        list.add(new BillAnalysisData("今天是使用爱记账的第" + timeIntervalByString + "喵~"));
        for(BillPieData billPieData:bpd)
        {
            list.add(new BillAnalysisData(billPieData.getValue(),
                    billPieData.getLabel(),
                    billPieData.getColor(),
                    dd,
                    "哇，好棒哦_(:з)∠)_"));
        }
        list.add(new BillAnalysisData("最终类型："));

        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(myPagerAdapter);
    }

    private void getFirstInstallTime()
    {
        PackageManager packageManager=this.getPackageManager();
        try {
            PackageInfo packageInfo=packageManager.getPackageInfo(this.getPackageName(), 0);
            firstInstallTimeByLong = packageInfo.firstInstallTime;//获取时间
            firstInstallTimeByString = getTimeByString(firstInstallTimeByLong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCurrTime()
    {
        currTimeByLong = System.currentTimeMillis();
        currTimeByString = getTimeByString(currTimeByLong);
    }

    private String getTimeByString(long time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
        return formatter.format(new Date(time));
    }
}
