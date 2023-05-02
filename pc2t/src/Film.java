import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
public abstract class Film {
    private String title;
    private String director;
    private int releaseYear;
    private List<Rating> ratings;
    private List<String> actorsOrAnimators;

    public Film(String title, String director, int releaseYear) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.ratings = new ArrayList<>();
        this.actorsOrAnimators = new ArrayList<>();
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

    public List<String> getActorsOrAnimators() {
        return actorsOrAnimators;
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

    public void filmInfo(){
        System.out.println(this.toString());
        System.out.println();

        Collections.sort(ratings, new Comparator<Rating>() {
            @Override
            public int compare(Rating r1, Rating r2) {
                if(r1.getStars() > r2.getStars())
                    return -1;
                else if(r1.getStars() < r2.getStars())
                    return 1;
                else
                    return 0;
            }
        });
        for (Rating r : ratings) {
            System.out.println(r.toString());
        }
    }



    public abstract boolean searchForName(String name);

}