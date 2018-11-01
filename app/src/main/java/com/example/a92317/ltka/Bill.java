package com.example.a92317.ltka;

import org.litepal.crud.DataSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill extends DataSupport{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double sum;
    private String remarks;
    private Date time;

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String str = formatter.format(time);
        return str;
    }

    public void setTime(){
        time =  new Date(System.currentTimeMillis());
    }
}
