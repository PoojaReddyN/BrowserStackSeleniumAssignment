package com.browserStack.utilities;

public class Article {

    private String title;
    private String content;
    private String imageUrl;

    public Article(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getImageUrl() { return imageUrl; }

    @Override
    public String toString() {
        return "Title: " + title + "\n\nContent:\n" + content;
    }
}
