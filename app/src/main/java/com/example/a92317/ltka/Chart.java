package com.example.a92317.ltka;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Chart extends BaseActivity {

    private PieChart pieChart;
    private List<BillPieData> bpd;
    private List<PieEntry> pieEntries;
    private List<Integer> colors;
    private PieDataSet pieDataSet;
    private PieData pieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Button button_back=(Button)findViewById(R.id.title_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        showPieChart();
    }

    private void showPieChart()
    {
        pieChart = (PieChart)findViewById(R.id.pie_chart);
        bpd = new ArrayList<>();
        pieEntries = new ArrayList<>();
        colors = new ArrayList<>();

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

        for(BillPieData billPieData:bpd)
        {
            pieEntries.add(new PieEntry(billPieData.getValue(),billPieData.getLabel()));
            colors.add(billPieData.getColor());
        }

        pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setColors(colors);

        pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        initPieChart();
        pieChart.invalidate();
    }

    //初始化
    private void initPieChart() {
        //  是否显示中间的洞
        pieChart.setDrawHoleEnabled(true);
        //pieChart.setHoleRadius(40f);//设置中间洞的大小

//        // 半透明圈
//        pieChart.setTransparentCircleRadius(30f);
//        pieChart.setTransparentCircleColor(Color.WHITE); //设置半透明圆圈的颜色
//        pieChart.setTransparentCircleAlpha(125); //设置半透明圆圈的透明度

        //饼状图中间可以添加文字
        pieChart.setDrawCenterText(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setCenterText("爱记账"); //设置中间文字
        pieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //中间问题的颜色
        pieChart.setCenterTextSizePixels(36);  //中间文字的大小px
        pieChart.setCenterTextRadiusPercent(1f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT); //中间文字的样式
        pieChart.setCenterTextOffset(0, 0); //中间文字的偏移量


        pieChart.setRotationAngle(0);// 初始旋转角度
        pieChart.setRotationEnabled(true);// 可以手动旋转
        pieChart.setUsePercentValues(true);//显示成百分比

        pieChart.getDescription().setEnabled(false); //右下角描述

        //是否显示每个部分的文字描述
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.WHITE); //描述文字的颜色
        pieChart.setEntryLabelTextSize(14);//描述文字的大小
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //描述文字的样式

        //图相对于上下左右的偏移
        pieChart.setExtraOffsets(0, 0, 0, 25);
        //图标的背景色
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        //设置pieChart图表转动阻力摩擦系数[0,1]
        pieChart.setDragDecelerationFrictionCoef(0.75f);

        pieChart.setAlpha(0.8f);

        //获取图例
        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);  //设置图例水平显示
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //顶部
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT); //左右对其

        legend.setXEntrySpace(7f);//x轴的间距
        legend.setYEntrySpace(10f); //y轴的间距
        legend.setYOffset(10f);  //图例的y偏移量
        legend.setXOffset(10f);  //图例x的偏移量
        legend.setTextColor(Color.parseColor("#a1a1a1")); //图例文字的颜色
        legend.setTextSize(15);  //图例文字的大小
    }
}
