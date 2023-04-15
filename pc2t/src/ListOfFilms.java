import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfFilms {
    private Map<String, Film> filmMap;

    public  ListOfFilms() {

        filmMap = new HashMap<>();
    }
    public Map<String, Film> getFilmMap() {
        return filmMap;
    }

    public Film getFilm(String filmName){
        return filmMap.get(filmName);
    }

    public void addRating(String filmName, Rating rating){
        filmMap.get(filmName).getRatings().add(rating);
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
        System.out.println("Enter film title to edit: ");
        String titleToEdit = input.nextLine();

        if (filmMap.containsKey(titleToEdit)) {
            Film filmToEdit = filmMap.get(titleToEdit);
            System.out.println("Choose what to edit: ");
            System.out.println("1. Director");
            System.out.println("2. Release year");
            System.out.println("3. Actors/Animators");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new director: ");
                    String newDirector = input.nextLine();
                    filmToEdit.setDirector(newDirector);
                    System.out.println("Director updated successfully.");
                    break;
                case 2:
                    System.out.println("Enter new release year: ");
                    int newReleaseYear = input.nextInt();
                    input.nextLine();
                    filmToEdit.setReleaseYear(newReleaseYear);
                    System.out.println("Release year updated successfully.");
                    break;
                case 3:
                    ArrayList<String> newActorsOrAnimators = new ArrayList<String>();
                    if (filmToEdit instanceof AnimatedFilm) {
                        System.out.println("Enter recommended age for the viewer: ");
                        int recommendedAge = input.nextInt();
                        input.nextLine();
                        ((AnimatedFilm) filmToEdit).setRecommendedAge(recommendedAge);
                        System.out.println("Recommended age updated successfully.");
                    }

                    System.out.println("Enter new list of actors/animators (separated by commas): ");
                    String actorsOrAnimators = input.nextLine();
                    String[] actorsOrAnimatorsArray = actorsOrAnimators.split(",");
                    for (String actorOrAnimator : actorsOrAnimatorsArray) {
                        newActorsOrAnimators.add(actorOrAnimator.trim());
                    }
                    filmToEdit.setActorsOrAnimators(newActorsOrAnimators);
                    System.out.println("Actors/Animators updated successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Movie not found.");
        }
    }


    public void deleteFilm() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter movie title to delete: ");
        String titleToDelete = input.nextLine();

        if (filmMap.containsKey(titleToDelete)) {
            filmMap.remove(titleToDelete);
            System.out.println("Movie deleted.");
        } else {
            System.out.println("Movie not found.");
        }
    }


    public  void displayFilms(){
        System.out.println("Films: ");
        for (Film film : filmMap.values()) {
            System.out.println(film.toString());
        }
    }
    public void listPeopleWithMultipleFilms() {
        ArrayList<String> animators = new ArrayList<>();
        HashMap<String, ArrayList<String>> actorFilmsMap = new HashMap<>();
        ArrayList<String> processed = new ArrayList<>();

        for (Film film : filmMap.values()) {
            if (film instanceof AnimatedFilm) {
                AnimatedFilm animatedFilm = (AnimatedFilm) film;
                for (String animator : animatedFilm.getAnimators()) {
                    if (!processed.contains(animator)) {
                        boolean found = false;
                        for (Film otherFilm : filmMap.values()) {
                            if (otherFilm instanceof AnimatedFilm && !film.equals(otherFilm)) {
                                AnimatedFilm otherAnimatedFilm = (AnimatedFilm) otherFilm;
                                if (otherAnimatedFilm.getAnimators().contains(animator) && !film.getTitle().equals(otherFilm.getTitle())) {
                                    found = true;
                                    break;
                                }
                            } else if (otherFilm instanceof FeatureFilm && !film.equals(otherFilm)) {
                                FeatureFilm featureFilm = (FeatureFilm) otherFilm;
                                if (featureFilm.getActors().contains(animator) && !film.getTitle().equals(otherFilm.getTitle())) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (found) {
                            animators.add(animator);
                        }
                        processed.add(animator);
                    }
                }
            } else if (film instanceof FeatureFilm) {
                FeatureFilm featureFilm = (FeatureFilm) film;
                for (String actor : featureFilm.getActors()) {
                    if (!processed.contains(actor)) {
                        boolean found = false;
                        ArrayList<String> films = new ArrayList<>();
                        for (Film otherFilm : filmMap.values()) {
                            if (otherFilm instanceof FeatureFilm && !film.equals(otherFilm)) {
                                FeatureFilm otherFeatureFilm = (FeatureFilm) otherFilm;
                                if (otherFeatureFilm.getActors().contains(actor) && !film.getTitle().equals(otherFilm.getTitle())) {
                                    found = true;
                                    films.add(otherFeatureFilm.getTitle());
                                }
                            } else if (otherFilm instanceof AnimatedFilm && !film.equals(otherFilm)) {
                                AnimatedFilm animatedFilm = (AnimatedFilm) otherFilm;
                                if (animatedFilm.getAnimators().contains(actor) && !film.getTitle().equals(otherFilm.getTitle())) {
                                    found = true;
                                    films.add(otherFilm.getTitle());
                                }
                            }
                        }
                        if (found) {
                            actorFilmsMap.put(actor, films);
                        }
                        processed.add(actor);
                    }
                }
            }
        }

        if (animators.isEmpty() && actorFilmsMap.isEmpty()) {
            System.out.println("No animators or actors worked on multiple films.");
        } else {
            if (!animators.isEmpty()) {
                System.out.println("Animators who worked on multiple films:");
                for (String animator : animators) {
                    System.out.println(animator);
                }
            }
            if (!actorFilmsMap.isEmpty()) {
                System.out.println("Actors who worked on multiple films:");
                for (Map.Entry<String, ArrayList<String>> entry : actorFilmsMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        }
    }


    public void filmSerach(String name){
        filmMap.get(name).filmInfo();
    }



}

