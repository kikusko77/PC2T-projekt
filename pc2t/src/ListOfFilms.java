import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
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

    public void addRating(String filmName, Rating rating) {
        filmMap.get(filmName).getRatings().add(rating);

        String sql;
        if (rating.getComment() == null){
            sql = "INSERT INTO ratings (title, stars, comment) " +
                    "VALUES ('" + filmName + "', " + rating.getStars() + ", NULL)";
        } else {
            sql = "INSERT INTO ratings (title, stars, comment) " +
                    "VALUES ('" + filmName + "', " + rating.getStars() + ", '" + rating.getComment() + "')";
        }
        DbContext.sqlQuaries.add(sql);
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
            DbContext.sqlQuaries.add("INSERT INTO basicinfo VALUES ('" + title + "', '" + director + "', '" + releaseYear + "', NULL, '" + movieType + "')");
            for (String actor : actorsArray) {
                actorsOrAnimators.add(actor.trim());
                DbContext.sqlQuaries.add("INSERT INTO actors VALUES ('" + actor.trim() +"')");
                DbContext.sqlQuaries.add("INSERT INTO filmactors VALUES ('"+ title + "', '" + actor.trim() +"')");
            }
        } else if (movieType == 2) {
            System.out.println("Enter recommended age for the viewer: ");
            int recommendedAge = input.nextInt();
            input.nextLine();
            System.out.println("Enter list of animators (separated by commas): ");
            String animators = input.nextLine();
            String[] animatorsArray = animators.split(",");
            DbContext.sqlQuaries.add("INSERT INTO basicinfo VALUES ('" + title + "', '" + director + "', '" + releaseYear + "', '" + recommendedAge + "', '" + movieType + "')");
            for (String animator : animatorsArray) {
                actorsOrAnimators.add(animator.trim());
                DbContext.sqlQuaries.add("INSERT INTO aimators VALUES ('" + animator.trim() +"')");
                DbContext.sqlQuaries.add("INSERT INTO filmanimators VALUES ('"+ title + "', '" + animator.trim() +"')");
            }
            filmMap.put(title ,new AnimatedFilm(title, director, releaseYear, actorsOrAnimators, recommendedAge));
            return;
        }

        filmMap.put(title, new FeatureFilm(title, director, releaseYear, actorsOrAnimators));
    }

    public void addFilmFromDb(int movieType, String title, String director, int releaseYear, int recommendedAge, String actors, String animators){
        ArrayList<String> actorsOrAnimators = new ArrayList<String>();
        if (movieType == 1) {
            String[] actorsArray = actors.split(",");
            for (String actor : actorsArray) {
                actorsOrAnimators.add(actor.trim());
            }
        } else if (movieType == 2) {
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


    public void deleteFilm() throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter movie title to delete: ");
        String titleToDelete = input.nextLine();

        if (filmMap.containsKey(titleToDelete)) {
            filmMap.remove(titleToDelete);
            System.out.println("Movie deleted.");
        } else {
            System.out.println("Movie not found.");
        }

        if (DbContext.connection != null) {

            PreparedStatement preparedStatement = DbContext.connection.prepareStatement("DELETE FROM `basicinfo` WHERE `Title` = ?");
            preparedStatement.setString(1, titleToDelete);
            DbContext.sqlQuaries.add(preparedStatement.toString());
            System.out.println(preparedStatement.toString());

        }
    }




    public  void displayFilms(){
        System.out.println("Films: ");
        for (Film film : filmMap.values()) {
            System.out.println(film.toString());
        }
    }
    public void listPeopleWithMultipleFilms() {
        Set<String> animators = new HashSet<>();
        Map<String, Set<String>> actors = new HashMap<>();

        for (Film film : filmMap.values()) {
            Set<String> currentAnimators = new HashSet<>(((AnimatedFilm) film).getAnimators());
            Set<String> currentActors = new HashSet<>(((FeatureFilm) film).getActors());

            for (String animator : currentAnimators) {
                if (animators.contains(animator)) {
                    System.out.println("Animator who worked on multiple films: " + animator);
                } else {
                    animators.add(animator);
                }
            }

            for (String actor : currentActors) {
                if (actors.containsKey(actor)) {
                    actors.get(actor).add(film.getTitle());
                } else {
                    actors.put(actor, new HashSet<>(Collections.singletonList(film.getTitle())));
                }
            }
        }

        for (Map.Entry<String, Set<String>> entry : actors.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Actor who worked on multiple films: " + entry.getKey() + " - Films: " + entry.getValue());
            }
        }

        if (animators.isEmpty() && actors.isEmpty()) {
            System.out.println("No animators or actors worked on multiple films.");
        }
    }



    public void filmSearch(String name){
        if(filmMap.get(name) != null)
            filmMap.get(name).filmInfo();
        else
            System.out.println("Film not found!");
    }
    public void filmSearchByActorOrAnimator(String name){
        int count = 0;
        for(Film films : filmMap.values()){
            if(films.searchForName(name)){
                System.out.println(films.toString());
                count++;
            }
        }
        if(count == 0)
            System.out.println("Na match found.");

    }
    public void exportFilms(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        StringBuilder sb = new StringBuilder();

        // Add header row
        sb.append("Title,Director,Release Year,Actors/Animators,Recommended Age,Rating\n");

        // Add film data
        for (Film film : filmMap.values()) {
            sb.append(film.getTitle()).append(",")
                    .append(film.getDirector()).append(",")
                    .append(film.getReleaseYear()).append(",")
                    .append(String.join("|", film.getActorsOrAnimators())).append(",");

            if (film instanceof AnimatedFilm) {
                sb.append(((AnimatedFilm) film).getRecommendedAge()).append(",");
            } else {
                sb.append("N/A,");
            }

            if (film.getRatings() == null) {
                sb.append("N/A,N/A\n");
            } else {
                sb.append(film.getRatings()).append(",");

            }
        }

        writer.write(sb.toString());
        writer.close();
    }




    public void importFilmFromFile(String films) {
        try {
            Scanner scanner = new Scanner(new File(films));
            String line = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String type = parts[0];
                String title = parts[1];
                String director = parts[2];
                int releaseYear = Integer.parseInt(parts[3]);
                String[] actorsOrAnimators = parts[4].split(",");
                if (type.equals("animated")) {
                    int recommendedAge = Integer.parseInt(parts[5]);
                    filmMap.put(title, new AnimatedFilm(title, director, releaseYear, new ArrayList<>(Arrays.asList(actorsOrAnimators)), recommendedAge));
                } else {
                    filmMap.put(title, new FeatureFilm(title, director, releaseYear, new ArrayList<>(Arrays.asList(actorsOrAnimators))));
                }
            }

            scanner.close();
            System.out.println("Films imported successfully from file: " + films);
        } catch (IOException e) {
            System.out.println("Error importing films from file: " + films);
            e.printStackTrace();
        }
    }




}

