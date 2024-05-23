package movie_recommendation_system;

import java.util.HashMap;
import java.util.Map;

public class Reviewer {

    private int userID;
    private Map<Integer, Integer> userMovieRatings = new HashMap<>();


    public Reviewer (int userID){
        this.userID = userID;
    }

    public void addRating(int movieID, int rating){
        userMovieRatings.put(movieID, rating);
    }

    public int getUserID(){
        return userID;
    }

    public int getFavouriteMovie(){
        int favouriteMovie = 0;
        int highestRating = 0;

        for (Map.Entry<Integer, Integer> entry : userMovieRatings.entrySet()){
            if (entry.getValue() > highestRating){
                highestRating = entry.getValue();
                favouriteMovie = entry.getKey();
            }
        }
        return favouriteMovie;
    }

    public Map<Integer, Integer> getUserMovieRatings(){
        return userMovieRatings;
    }

    public boolean hasRatedMovie(int movieID){
        return userMovieRatings.containsKey(movieID);
    }

    public int getRating(int movieID){
        return userMovieRatings.get(movieID);
    }



    
}
