import java.util.ArrayList;

public class AnimatedFilm extends Film {
    private ArrayList<String> animators;
    private int recommendedAge;

    public AnimatedFilm(String title, String director, int releaseYear, ArrayList<String> animators, int recommendedAge) {
        super(title, director, releaseYear);
        this.animators = animators;
        this.recommendedAge = recommendedAge;
    }

    public ArrayList<String> getAnimators() {
        return animators;
    }

    public void setAnimators(ArrayList<String> animators) {
        this.animators = animators;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    @Override
    public String toString() {
        return "AnimatedFilm{" + "title='" + getTitle() + '\'' + ", director='" + getDirector() + '\'' + ", releaseYear=" + getReleaseYear() +
                ", animators=" + animators +
                ", recommendedAge=" + recommendedAge +
                '}'+"Pouzivatel hodnotil "+ getRatings()+" hviezdami";
    }
}
