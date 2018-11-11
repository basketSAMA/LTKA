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

    private double dd;
    private String timeIntervalByString;

    private List<BillPieData> bpd;

    private double result[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    private int low = 0;
    private int high = 0;
    private int midle = 0;

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
        timeIntervalByString = df.format(dd);

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
        list.add(new BillAnalysisData("今天是使用爱记账的第 " + timeIntervalByString + " 天喵~",
                "_(:з)∠)_",
                "于 " + firstInstallTimeByString + " 初次见面",
                "不知不觉已经到了 " + currTimeByString +" 以后也请多多关照！"));
        for(BillPieData billPieData:bpd)
        {
            list.add(new BillAnalysisData(billPieData.getValue(),
                    billPieData.getLabel(),
                    billPieData.getColor(),
                    dd,
                    getAnal(billPieData.getLabel(),billPieData.getValue()/dd)));
        }
        String[] finalAnal = getFinalAnal();
        list.add(new BillAnalysisData(finalAnal[0],
                finalAnal[1],
                finalAnal[2],
                finalAnal[3]));

        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(myPagerAdapter);
    }

    //随便自定义了标准...
    private String getAnal(String label,double aver)
    {
        String anal = "";
        double standard;
        double judge;
        switch (label)
        {
            case "饮食":standard = 36;
                judge =(aver - standard)/standard;
                result[0] = judge;
                if(judge < -0.5)
                {
                    anal = "哇，你是喝花露水的仙子嘛Σ(っ °Д °;)っ";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "吃的有点少哦，长身体的年龄多吃点吧喵";
                }
                else if(judge < 0.3)
                {
                    anal = "饮食正常哦ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "说，是不是悄悄吃宵夜了";
                }
                else
                {
                    anal = "别藏了，你已经暴露吃货的本质了！";
                    high++;
                }
                break;
            case "学习":standard = 4;
                judge =(aver - standard)/standard;
                result[1] = judge;
                if(judge < -0.5)
                {
                    anal = "喵？喵喵？？";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "看来平常不爱买书和文具哦";
                }
                else if(judge < 0.3)
                {
                    anal = "正常的消费哈ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "原来你还挺爱学习的嘿嘿";
                }
                else
                {
                    anal = "哇，学霸！";
                    high++;
                }
            case "娱乐":standard = 4;
                judge =(aver - standard)/standard;
                result[2] = judge;
                if(judge < -0.5)
                {
                    anal = "“呵，凡人！”";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "清心寡欲喵";
                }
                else if(judge < 0.3)
                {
                    anal = "放松放松心情ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "“我就是爱玩，别叫我停下来~”";
                }
                else
                {
                    anal = "放飞自我ing";
                    high++;
                }
                break;
            case "交通":standard = 0.5;
                judge =(aver - standard)/standard;
                result[3] = judge;
                if(judge < -0.5)
                {
                    anal = "大门不出，二门不迈";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "“呜哇，还是家里舒服~”";
                }
                else if(judge < 0.3)
                {
                    anal = "“emmmm今天还是扫个小黄/蓝/红/绿吧ww”";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "每天都在考虑周末的行程安排喵";
                }
                else
                {
                    anal = "今天又要去哪呢~";
                    high++;
                }
                break;
            case "健康":standard = 1;
                judge =(aver - standard)/standard;
                result[4] = judge;
                if(judge < -0.5)
                {
                    anal = "免疫力贼高~";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "健健康康身体棒~";
                }
                else if(judge < 0.3)
                {
                    anal = "天气渐冷，注意保暖哦ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "“哼哼，我才不会感……阿嚏！";
                }
                else
                {
                    anal = "保护好自己啊！记得多喝热水！";
                    high++;
                }
                break;
            case "居住":standard = 3.5;
                judge =(aver - standard)/standard;
                result[5] = judge;
                if(judge < -0.5)
                {
                    anal = "我对你这个从不忘记关灯的人很满意哼！";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "节约能源一把手~";
                }
                else if(judge < 0.3)
                {
                    anal = "哎，原来在学校要交水电费哦ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "“该交……”“听不见听不见！”";
                }
                else
                {
                    anal = "水费电费网费……呜哇，怎么这么多！";
                    high++;
                }
                break;
            case "人情":standard = 0.5;
                judge =(aver - standard)/standard;
                result[6] = judge;
                if(judge < -0.5)
                {
                    anal = "最讨厌借钱了喵！";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "借你一点，多了休想！";
                }
                else if(judge < 0.3)
                {
                    anal = "还是借吧，不借不太好呢ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "借钱？没问题！";
                }
                else
                {
                    anal = "侠肝义胆如你，看好你哦喵~";
                    high++;
                }
                break;
            case "其它":standard = 0.5;
                judge =(aver - standard)/standard;
                result[7] = judge;
                if(judge < -0.5)
                {
                    anal = "额外开支巨少，就是这么舒服~";
                    low++;
                }
                else if(judge < -0.3) //-50％到-30％
                {
                    anal = "偶尔弄点其它玩意儿嘿嘿";
                }
                else if(judge < 0.3)
                {
                    anal = "嗯嗯，这点钱花在其它上正常的很ww";
                    midle++;
                }
                else if(judge < 0.5)
                {
                    anal = "额外的计划也要重视喵";
                }
                else
                {
                    anal = "不听不听不听，就喜欢其它消费！";
                    high++;
                }
                break;
            default:
                break;
        }
        return anal;
    }

    private String[] getFinalAnal()
    {
        String[] ans = {"","","",""};
        ans[0] = "显著低于标准的类型有 " + low +" 个";
        ans[1] = "显著高于标准的类型有 " + high +" 个";
        ans[2] = "处于标准区间的类型有 " + midle +" 个";
        double bias = 0.0;
        for(int i = 0; i < 8; i++)
        {
            bias += result[i];
        }
        DecimalFormat df = new DecimalFormat("#.##");
        ans[3] = "偏差为 " + df.format(bias * 100) + " ％";
        return ans;
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
