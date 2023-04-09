import java.util.List;

public class FeatureFilm extends Film {
    private List<String> actors;

    public FeatureFilm(String title, String director, int releaseYear, List<String> actors) {
        super(title, director, releaseYear);
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }
}