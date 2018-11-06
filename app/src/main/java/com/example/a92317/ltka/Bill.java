package com.example.a92317.ltka;

import org.litepal.crud.LitePalSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill extends LitePalSupport {
    private int id;
    private double sum;
    private String remarks;
    private Date time;

    private String classificationName;
    private int setClassificationColor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String str = formatter.format(time);
        return str;
    }

    public void setTime(){
        time =  new Date(System.currentTimeMillis());
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public int getSetClassificationColor() {
        return setClassificationColor;
    }

    public void setSetClassificationColor(int setClassificationColor) {
        this.setClassificationColor = setClassificationColor;
    }
}
