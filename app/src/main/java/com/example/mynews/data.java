package com.example.mynews;

public class data {

    private String author,content,date,imageUrl,title,readMoreUrl;

    public data( String title,String author, String content, String date, String imageUrl, String readMoreUrl) {
        this.author = author;
        this.content = content;
        this.date = date;
        this.imageUrl = imageUrl;
        this.title = title;
        this.readMoreUrl = readMoreUrl;
    }
}
