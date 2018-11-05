package com.example.a92317.ltka;

public class BillPieData {
    private float value;
    private String label;
    private int color;

    public BillPieData(float v,String l,int c)
    {
        value = v;
        label = l;
        color = c;
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
}
