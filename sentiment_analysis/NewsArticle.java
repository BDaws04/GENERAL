package com.sentiment_analysis;

/**
 * A simple class that stores the articles as objects
 * Private fields, with get statments to return key data
 */

public class NewsArticle {

    private String headline;
    private String link;

    public NewsArticle (String headline, String link){
        this.headline = headline;
        this.link = link;
    }

    public String getHeadline(){
        return headline;
    }

    public String getLink(){
        return link;
    }
}
