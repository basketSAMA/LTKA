package com.example.a92317.ltka;

import java.text.DecimalFormat;

public class BillAnalysisData {
    private float value;
    private String label;
    private int color;
    private String sum;
    private String aver;
    private String anal;

    public BillAnalysisData(float value,String label,int color,double dd,String anal)
    {
        this.value = value;
        this.label = label;
        this.color = color;
        this.sum = "在" + label + "上总共花费了" + value + "元";
        DecimalFormat df = new DecimalFormat("#.##");
        this.aver = "平均" + df.format(value/dd) + "元/天";
        this.anal = anal;
    }

    public BillAnalysisData(String label)
    {
        this.value = 0;
        this.label = label;
        this.color = R.color.colorBlack;
        this.sum = "";
        this.aver = "";
        this.anal = "";
    }

    public float getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public int getColor() {
        return color;
    }

    public String getSum() {
        return sum;
    }

    public String getAver() {
        return aver;
    }

    public String getAnal() {
        return anal;
    }
}
