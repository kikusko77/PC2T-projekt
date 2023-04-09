import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int select;
        Scanner sc = new Scanner(System.in);
        ListOfFilms listOfFilms = new ListOfFilms();

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
            ListOfFilms list = new ListOfFilms();

            switch (select) {
                case 1:

                    listOfFilms.addFilm();

                    break;
                case 2:
                    listOfFilms.editFilm();

                    break;
                case 3:
                    listOfFilms.deleteFilm();

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
                    listOfFilms.displayFilms();

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
            }
        }
    }
}