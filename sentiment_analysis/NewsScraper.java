package com.sentiment_analysis;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This is the scraper, currently works for BBC News
 * Identifies top 10 news stories and gathers the url to them
 * Finds the top 10 by classname 
 * NOTE: have not tested whether BBC change the classNames regularly, working as on 21/3/24
 */

public class NewsScraper {

    private String url;
    private ArrayList<NewsArticle> articles = new ArrayList<>();

    public NewsScraper(String url){
        this.url = url;
    }

    public ArrayList<NewsArticle> getNewsArticles(){
        try {
           Document doc = Jsoup.connect(url).get();
           Elements articleHeadlines = doc.getElementsByClass("ssrcss-19qt899-Headline e1qjxmbq4");

           for (Element a : articleHeadlines) {
               String headline = a.text();
               String link = "https://bbc.co.uk" +  a.attr("href");

               NewsArticle article = new NewsArticle(headline, link);
               articles.add(article);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articles;

    }
}
    

   



   




