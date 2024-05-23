package com.sentiment_analysis;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This is the main class
 * Creates all the objects and outputs the news articles to the terminal
 */

public class Main {

    private HashMap <NewsArticle, String> ratedArticles = new HashMap<>();
    public static void main(String[] args) {
      Main main = new Main();
      NewsScraper scraper = new NewsScraper("https://www.bbc.co.uk/news");
      main.loadHashMap(scraper.getNewsArticles());
      main.printNewsArticles();
    }

    private void loadHashMap(ArrayList<NewsArticle> articles){
        for (int i = 0; i < articles.size(); i++){
            String title = articles.get(i).getHeadline();
            SentimentAnalyser analyser = new SentimentAnalyser(title);
            String analysis = analyser.performSentimentAnalysis();

            if (analysis == null){
                try {
                    analysis = analyser.performSentimentAnalysisWithGPT();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            ratedArticles.put(articles.get(i),analysis);
        }
    }

    private void printNewsArticles(){
       
        for (NewsArticle key : ratedArticles.keySet()){
            System.out.println(key.getHeadline());
            System.out.println(key.getLink());
            System.out.println("This news was rated: " + ratedArticles.get(key) + " by the sentiment analysis");
            System.out.println("------------");
        }

    }
}