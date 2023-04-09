import java.util.ArrayList;
import java.util.List;

public class FeatureFilm extends Film {
    private ArrayList<String> actors;

    public FeatureFilm(String title, String director, int releaseYear, ArrayList<String> actors) {
        super(title, director, releaseYear);
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Feature film: " + getTitle() + " (" + getReleaseYear() + "), directed by " + getDirector() + ", starring " + String.join(", ", actors)
                +"Marek hodnotil "+ getRatings().get(getTitle())+" hviezdami lebo to bolo napicu";
    }
}