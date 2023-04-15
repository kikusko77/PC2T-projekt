import java.util.ArrayList;
import java.util.List;

public abstract class Film {
    private String title;
    private String director;
    private int releaseYear;
    private List<Rating> ratings;

    public Film(String title, String director, int releaseYear) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setActorsOrAnimators(ArrayList<String> newActorsOrAnimators) {
    }

    public<T> void filmInfo(T film){

    }
}