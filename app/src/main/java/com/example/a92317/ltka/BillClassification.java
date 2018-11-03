package com.example.a92317.ltka;

public class BillClassification {
    private String classificationName;
    private int classificationColor;

    public BillClassification(String name, int color){
        classificationName = name;
        classificationColor = color;
    }

    public String getClassificationName(){
        return classificationName;
    }

    public int getClassificationColor(){
        return classificationColor;
    }

    public void setClassificationColor(int classificationColor) {
        this.classificationColor = classificationColor;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }
}
