package com.example.a92317.ltka;

public class Article {

    private String name;

    private String author;

    private String contents;

    public Article(String name,String author, String contents){
        this.name=name;
        this.author=author;
        this.contents=contents;
    }
    public String getName(){
        return name;
    }

    public String getAuthor() {
        return "作者："+author;
    }

    public String getContents() { return contents; }
}
