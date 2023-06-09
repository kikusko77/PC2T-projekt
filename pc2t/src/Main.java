import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        int select;
        Scanner sc = new Scanner(System.in);
        ListOfFilms list = new ListOfFilms();

        DbContext.LoadFromDb(list);


        while (true) {
            System.out.println("1. Add new film");
            System.out.println("2. Adjust film");
            System.out.println("3. Delete film");
            System.out.println("4. Add rating");
            System.out.println("5. Film list");
            System.out.println("6. Search film");
            System.out.println("7. List of actors or animators, who played in more than 1 film");
            System.out.println("8. List of films searched by actor or animator");
            System.out.println("9. Save film info into file");
            System.out.println("10. Load film info from file");
            System.out.println("11. Exit app");

            select = sc.nextInt();
            sc.nextLine();


            switch (select) {
                case 1:
                    list.addFilm();

                    break;
                case 2:
                    list.editFilm();

                    break;
                case 3:
                    list.deleteFilm();

                    break;
                case 4:

                    System.out.println("Type name of film, you want to add rating to.");
                    String filmName = sc.nextLine();
                    System.out.println(" and number of stars.");
                    int stars = sc.nextInt();
                    sc.nextLine();
                    Rating rating = new Rating();
                    if(rating.tryAddRating(list.getFilm(filmName), stars)){
                           list.addRating(filmName, rating);
                           System.out.println("Rating successfully added");
                    } else
                        System.out.println("\u001B[31m" + "Adding of rating aborted!" + "\u001B[0m");
                        System.out.println();

                    break;
                case 5:
                    list.displayFilms();

                    break;
                case 6:

                    System.out.println("Type name of film you are looking for");
                    list.filmSearch(sc.nextLine());

                    break;
                case 7:
                    list.listPeopleWithMultipleFilms();


                    break;
                case 8:

                    System.out.println("Type name of actor or animator to show filtered film list");
                    list.filmSearchByActorOrAnimator(sc.nextLine());
                    break;
                case 9:
                    String exportFilePath = "./data/films.txt";
                    list.exportFilms(exportFilePath);

                    break;
                case 10:
                    String importFilePath = "./data/filmsIn.txt";
                    list.importFilmFromFile(importFilePath);

                    break;
                case 11:
                    DbContext.SaveToDb();

                    break;
            }
        }
    }
}