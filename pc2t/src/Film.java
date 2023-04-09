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
}