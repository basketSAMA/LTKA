package com.example.a92317.ltka;

public class Article {

    private String name;

    private String author;

    public Article(String name,String author){
        this.name=name;
        this.author=author;
    }
    public String getName(){
        return name;
    }

    public String getAuthor() {
        return "作者："+author;
    }
}
