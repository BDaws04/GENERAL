package movie_recommendation_system;
import java.util.Set;

public class Movie_Similarity_Checker {

    private Data_Loader loader;
    private double absoluteSimilarity;

    public Movie_Similarity_Checker(Data_Loader loader){
        this.loader = loader;
    }

    public double getSimilarity(User user, Reviewer reviewer){
        Set<Integer> userMovies = user.getUserTastes().keySet();
        Set<Integer> reviewerMovies = reviewer.getUserMovieRatings().keySet();


        // calculates score for movie watch history similarity
        // 0.2 is the maximum score for movie watch history similarity
        for (Integer movieID : userMovies){
            if (reviewerMovies.contains(movieID)){
                int userRating = user.getUserTastes().get(movieID);
                int reviewerRating = reviewer.getUserMovieRatings().get(movieID);
                int difference = Math.abs(userRating - reviewerRating);

                absoluteSimilarity = absoluteSimilarity + (0.2 - (difference * 0.04)); 
            }
            else {
                absoluteSimilarity = absoluteSimilarity + 0.05;
            }
        }     
        return absoluteSimilarity;
    }

}
