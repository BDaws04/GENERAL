package com.sentiment_analysis;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class performs the sentiment analysis 
 * It takes the text to be analysed as a constructor
 * Returns neutral as default 
 * NOTE: ENSURE YOU HAVE THE CORRECT STANFORD CORE NLP DOWNLOADED, ELSE THERE WILL BE AN ERROR
 * UPDATE: Now will use ChatGPT API if the sentiment analysis fails
 * Ensure you have an API key for OpenAI's GPT-3
 */

public class SentimentAnalyser {

    private String text;
    private final String PROPERTIES_KEY = "annotators";
    private final String PROPERTIES_VALUE = "tokenize, ssplit, parse, sentiment";
    private String sentimentResult = null;
    private final String API_KEY = "input your API key here";
   
    public SentimentAnalyser(String text){
        this.text = text;
    }

    private String getSentimentAnalysis(){
        return sentimentResult;
    }

    public String performSentimentAnalysisWithGPT() throws MalformedURLException, IOException{
        URL url = new URL("https://api.openai.com/v1/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String prompt = "Give a one word response, selecting either positive, negative or neutral. What is the sentiment of this news story: " + text;
        String jsonPayload = "{\"model\":\"text-davinci-002\",\"prompt\":\"" + prompt + "\",\"max_tokens\":50}";

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(jsonPayload);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
              }
        in.close();
       return response.toString();

    }

    public String performSentimentAnalysis(){

        if (text != null && text.length() > 0){
            try {
                Properties properties = new Properties();
                properties.setProperty(PROPERTIES_KEY, PROPERTIES_VALUE);
                StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

                Annotation textAnnotation = new Annotation(text);

                pipeline.annotate(textAnnotation);

                String sentiment = textAnnotation.get(CoreAnnotations.SentencesAnnotation.class).get(0).get(SentimentCoreAnnotations.SentimentClass.class);
                sentimentResult = sentiment;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return getSentimentAnalysis();
        

    }


}


