package movie_recommendation_system;
import java.util.HashSet;
import java.util.Set;

public class Movie {

    private int movieID;
    private String title;
    private Set<String> genres = new HashSet<>();

    public Movie (int movieID, String title){
        this.movieID = movieID;
        this.title = title;
    }

    public int getMovieID(){
        return movieID;
    }

    public String getTitle(){
        return title;
    }

    public void addGenre(String genre){
        genres.add(genre);
    }

    public Set<String> getGenres(){
        return genres;
    }

}
