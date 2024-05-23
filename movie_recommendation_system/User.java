package movie_recommendation_system;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class User {

    Data_Loader loader;

    public User(Data_Loader loader){
        this.loader = loader;
    }

    public Reviewer getHighestReviewer(){
        Reviewer highestReviewer = null;
        double highestSimilarity = 0;
        for (Map.Entry<Reviewer, Double> entry : reviewerSimilarity.entrySet()){
            if (entry.getValue() > highestSimilarity){
                highestSimilarity = entry.getValue();
                highestReviewer = entry.getKey();
            }
        }
        return highestReviewer;
    }

    private Map <Reviewer, Double> reviewerSimilarity = new HashMap<>();
    private Map<Integer, Integer> userTastes = new HashMap<>();

    public void generateTastes(){

        System.out.println("Hello user! What is a recent film you have watched?");
        
        try {
            Scanner scanner = new Scanner(System.in);
            String movie = scanner.nextLine();

            while (!loader.movieExists(movie)){
                if (!loader.movieExists(movie)){
                     System.out.println("Sorry, we do not have that film. Please try again.");
                     movie = scanner.nextLine();
                }
            }

            int movieID = loader.getMovieID(movie);
            int rating = 0;

            do {
                System.out.println("How would you rate this film out of 5?");
                try {
                    rating = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Please enter a number between 0 and 5");
                }
            } while (rating < 0 || rating > 5);

            addTaste(movieID, rating);
            System.out.println("You have rated " + movie + " a " + rating + " out of 5");
            
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }   
    }


    private void addTaste(int movieID, int rating){
        userTastes.put(movieID, rating);
    }

    public Map<Integer, Integer> getUserTastes() {
        return userTastes;
    }

    private boolean alreadyReviewed(int movieID){
        return userTastes.containsKey(movieID);
    }

    public void addSimilaritty(Reviewer reviewer, double similarity){
        reviewerSimilarity.put(reviewer, similarity);
    }
}
