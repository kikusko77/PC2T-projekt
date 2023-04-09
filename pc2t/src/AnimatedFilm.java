import java.util.List;

public class AnimatedFilm extends Film {
    private List<String> animators;
    private int recommendedAge;

    public AnimatedFilm(String title, String director, int releaseYear, List<String> animators, int recommendedAge) {
        super(title, director, releaseYear);
        this.animators = animators;
        this.recommendedAge = recommendedAge;
    }

    public List<String> getAnimators() {
        return animators;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void  setRecommendedAge(int recommendedAge){
        this.recommendedAge= this.recommendedAge;
    }

}