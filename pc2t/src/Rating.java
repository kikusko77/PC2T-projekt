import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

public class Rating {
    private int stars;
    private String comment = null;
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

    public boolean tryAddRating(Film film, int stars) {
        if (film instanceof FeatureFilm) {
            try {
                setStarsFeaturedFilm(stars);
            }catch (Exception e){
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
                return false;
            }

            System.out.println("Do you want to add  an comment? y/n");
            if (sc.next().charAt(0) == 'y') {
                sc.nextLine();
                this.comment = sc.nextLine();
            }
            return true;
        } else if (film == null){
            System.out.println("Film not found!");
            return false;
        } else
            return false;
    }

    @Override
    public String toString(){
        if(comment == null){
            return "Rated: " + stars + " stars.";
        }else {
            return "Rated: " + stars + " stars,  comment: " + comment;
        }
    }
}
