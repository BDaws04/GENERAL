package movie_recommendation_system;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * This class loads the reviews of all the movies
 * Stores this into a hashmap
 * Key of the hashmap is the UserID
 * The value is a set; first value is movie ID, second value is rating
 * 
 * Also stores all of the movies into an arraylist
 * Type is Movie
 * 
 * Contains methods to see users and movies, if a movie exists, and to get the movie ID
 */

public class Data_Loader {

    private final String CSV_REVIEW_URL = "src\\main\\java\\movie_recommendation_system\\ratings.csv";
    private final String CSV_MOVIES_URL = "src\\main\\java\\movie_recommendation_system\\movies.csv";
    private ArrayList<Reviewer> reviewers = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    public void loadMovies(){
        try (CSVReader reader = new CSVReader(new FileReader(CSV_MOVIES_URL))) {
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++){
                String[] row = records.get(i);
                int movieID = Integer.parseInt(row[0]);
                String unsortedTitle = row[1];
                String unSortedGenres = row[2];

                String title = removeYear(unsortedTitle);
               
                Movie movie = new Movie(movieID, title);

                Set<String> genres = new HashSet<>();

                if (unSortedGenres != null &&!unSortedGenres.isEmpty()){
                    setGenres(unSortedGenres, genres);

                    for (String g : genres){
                        if (g != null && !g.isEmpty()){
                        movie.addGenre(g); }
                    }
                }
                else {
                    movie.addGenre("Unknown");
                }
                movies.add(movie);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public void loadReviews(){
        try (CSVReader reader = new CSVReader(new FileReader(CSV_REVIEW_URL))){
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++){
                String[] row = records.get(i);
                int userID = Integer.parseInt(row[0]);
                int movieID = Integer.parseInt(row[1]);
                int rating = Integer.parseInt(row[2]);

                for (int k = 0; k < reviewers.size(); k++){
                    if (reviewers.get(k).getUserID() == userID){
                        reviewers.get(k).addRating(movieID, rating);
                    }
                    else {
                        Reviewer user = new Reviewer(userID);
                        user.addRating(movieID, rating);
                        reviewers.add(user);
                    }

                }

            }
            
        } catch ( IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Reviewer> getReviewers(){
        return reviewers;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public boolean movieExists(String title){
        String input = title.trim();
        for (int i = 0; i < movies.size(); i++){
            String movieTitle = movies.get(i).getTitle().trim();
            if (movieTitle.equalsIgnoreCase(input)){
                return true;
            }
        }
        return false;
    }

    private void setGenres(String s, Set<String> genres){
        if (s.contains("|")){
            int index = s.indexOf("|");
            genres.add(s.substring(0, index));
            StringBuilder sb = new StringBuilder(s);
            sb.delete(0, index + 1);
            String d = sb.toString();
            setGenres(d, genres);
            
        }
        else {
            genres.add(s);
        }
    }

    public int getMovieID(String title){
        for (Movie m : movies){
            if (m.getTitle().equals(title)){
                return m.getMovieID();
            }
        }
        return -1;
    }

    public String getMovieTitle(int movieID){
        for (Movie m : movies){
            if (m.getMovieID() == movieID){
                return m.getTitle();
            }
        }
        return null;
    }

    private String removeYear(String title){
        if (title.contains("(")){
            int index = title.indexOf("(");
            title = title.substring(0, index);
            return title;
        }
        else{
            return title;
        }
    }

}

