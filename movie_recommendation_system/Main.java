package movie_recommendation_system;

public class Main {
    public static void main(String[] args) {
        Data_Loader loader = new Data_Loader();
        loader.loadMovies();
        loader.loadReviews();

        System.out.println(loader.getMovies().size() + " movies loaded");

        User user = new User(loader);

        for (int i = 0; i < 5; i++){
            user.generateTastes();
        }

        Movie_Similarity_Checker checker = new Movie_Similarity_Checker(loader);

        for (Reviewer reviewer : loader.getReviewers()){
            double similarity = checker.getSimilarity(user, reviewer);
            user.addSimilaritty(reviewer, similarity);
        }

        Reviewer highestReviewer = user.getHighestReviewer();
        String recommendedMovie = loader.getMovieTitle(highestReviewer.getFavouriteMovie());



        System.out.println("Thank you for rating 5 movies! Your recommended movie is: " + recommendedMovie);
     

    }
}