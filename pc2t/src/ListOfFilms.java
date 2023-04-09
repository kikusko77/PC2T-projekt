import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfFilms {
    private Map<String, Film> filmMap;

    public  ListOfFilms() {

        filmMap = new HashMap<>();
    }

    public void addFilm(){

        Scanner input = new Scanner(System.in);

        System.out.println("Enter movie type (1 for live-action, 2 for animated): ");
        int movieType = input.nextInt();
        input.nextLine();

        System.out.println("Enter movie title: ");
        String title = input.nextLine();

        System.out.println("Enter movie director: ");
        String director = input.nextLine();

        System.out.println("Enter movie release year: ");
        int releaseYear = input.nextInt();
        input.nextLine();

        ArrayList<String> actorsOrAnimators = new ArrayList<String>();
        if (movieType == 1) {
            System.out.println("Enter list of actors (separated by commas): ");
            String actors = input.nextLine();
            String[] actorsArray = actors.split(",");
            for (String actor : actorsArray) {
                actorsOrAnimators.add(actor.trim());
            }
        } else if (movieType == 2) {
            System.out.println("Enter recommended age for the viewer: ");
            int recommendedAge = input.nextInt();
            input.nextLine();
            System.out.println("Enter list of animators (separated by commas): ");
            String animators = input.nextLine();
            String[] animatorsArray = animators.split(",");
            for (String animator : animatorsArray) {
                actorsOrAnimators.add(animator.trim());
            }
            filmMap.put(title ,new AnimatedFilm(title, director, releaseYear, actorsOrAnimators, recommendedAge));
            return;
        }

        filmMap.put(title, new FeatureFilm(title, director, releaseYear, actorsOrAnimators));
    }
    public  void  editFilm() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter movie title to edit: ");
    }

    public  void  deleteFilm(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter movie title to delete: ");
        filmMap.remove(input.nextLine());


        System.out.println("Movie not found.");
    }

    public  void displayFilms(){
        System.out.println("Movies: ");
        for (Film film : filmMap.values()) {
            System.out.println(film.toString());
        }
    }




}

