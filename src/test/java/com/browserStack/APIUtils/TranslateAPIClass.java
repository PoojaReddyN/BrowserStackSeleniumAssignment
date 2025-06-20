package com.browserStack.APIUtils;

public class TranslateAPIClass {
    private String from;
    private String to;
    private String text;

    @Override
    public String toString() {
        return "{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public TranslateAPIClass(String from, String to, String text){
        this.from=from;
        this.to=to;
        this.text=text;
    }
}
