import java.util.ArrayList;
import java.util.Scanner;

public class ListOfFilms {
    private ArrayList<Film> listOfFilms;

    public  ListOfFilms() {

        this.listOfFilms = new ArrayList<>();
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
            listOfFilms.add(new AnimatedFilm(title, director, releaseYear, recommendedAge, actorsOrAnimators));
            return;
        }

        listOfFilms.add(new FeatureFilm(title, director, releaseYear, actorsOrAnimators));
    }
    public  void  editFilm(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter movie title to edit: ");
        String titleToEdit = input.nextLine();

    }





    }

