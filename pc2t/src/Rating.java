import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

public class Rating {
    private int stars;
    private String comment;
    Scanner sc = new Scanner(System.in);

    public int getStars() {
        return stars;
    }

    public void setStarsFeaturedFilm(int stars) throws Exception {
        if (stars >= 1 && stars <= 5)
            this.stars = stars;
        else
            throw new Exception("Film can be rated only with 1 to 5 stars!");
    }

    public void setStarsAnimatedFilm(int stars) throws Exception {
        if (stars >= 1 && stars <= 10)
            this.stars = stars;
        else
            throw new Exception("Film can be rated only with 1 to 10 stars!");
    }

    public boolean addRating(Film film, int stars) {
        if (film instanceof FeatureFilm) {
            try {
                setStarsFeaturedFilm(stars);
            }catch (Exception e){
                e.getMessage();
                return false;
            }

            System.out.println("Do you want to add  an comment? y/n");
            if (sc.next().charAt(0) == 'y') {
                this.comment = sc.nextLine();
            }
            return true;
        } else if (film instanceof AnimatedFilm) {
            try {
                setStarsAnimatedFilm(stars);
            }catch (Exception e){
                e.getMessage();
                return false;
            }

            System.out.println("Do you want to add  an comment? y/n");
            if (sc.next().charAt(0) == 'y') {
                this.comment = sc.nextLine();
            }
            return true;
        } else
            return false;
    }
}
