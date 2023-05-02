import java.sql.*;

public class DbContext {
    static String url = "jdbc:mysql://localhost:3306/films";
    static String user = "root";
    static String password = "";
    static Connection connection;

    public static void LoadFromDb(ListOfFilms list){
        DbConnect();
        try {

            String sql = "SELECT * FROM basicinfo";
            Statement statementFilm = connection.createStatement();
            ResultSet resultFilm = statementFilm.executeQuery(sql);

            while (resultFilm.next()) {
                 String title = resultFilm.getString("Title");
                 String director = resultFilm.getString("Director");
                 int releaseYear = resultFilm.getInt("ReleaseYear");
                 int recommendedAge = resultFilm.getInt("RecommendedAge");
                 int movieType = resultFilm.getInt("MovieType");
                 String actors = "";
                 String animators = "";

                 if (movieType == 1){
                     sql = "SELECT actors.Name FROM actors LEFT JOIN filmactors ON actors.Name = filmactors.Name " +
                             "LEFT JOIN basicinfo ON filmactors.Title = basicinfo.Title WHERE basicinfo.Title = ?";
                     PreparedStatement statementFeaturedFilm = connection.prepareStatement(sql);
                     statementFeaturedFilm.setString(1, title);
                     ResultSet resultFeaturedFilm = statementFeaturedFilm.executeQuery();
                     for (int i = 0 ;resultFeaturedFilm.next(); i++){
                         if(i != 0) {
                             actors += ",";
                         }
                         actors += resultFeaturedFilm.getString("Name");
                     }

                 } else if (movieType == 2) {
                     sql = "SELECT animators.Name FROM animators LEFT JOIN filmanimators ON animators.Name = filmanimators.Name " +
                             "LEFT JOIN basicinfo ON filmanimators.Title = basicinfo.Title WHERE basicinfo.Title = ?";
                     PreparedStatement statementAnimatedFilm = connection.prepareStatement(sql);
                     statementAnimatedFilm.setString(1, title);
                     ResultSet resultAnimatedFilm = statementAnimatedFilm.executeQuery();
                     for (int i = 0 ;resultAnimatedFilm.next(); i++){
                         if(i != 0) {
                             animators += ",";
                         }
                         animators += resultAnimatedFilm.getString("Name");
                     }
                }
                 list.addFilmFromDb(movieType, title, director, releaseYear, recommendedAge, actors, animators);

                 sql = "SELECT Stars, Comment FROM ratings WHERE Title = ?";
                PreparedStatement statementRatings = connection.prepareStatement(sql);
                statementRatings.setString(1, title);
                ResultSet resultRatings = statementRatings.executeQuery();
                while (resultRatings.next()){
                    Rating rating = new Rating();
                    rating.setStars(resultRatings.getInt("Stars"));
                    rating.setComment(resultRatings.getString("Comment"));
                    list.addRating(title, rating);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbDisconnect();
        }
    }
    public static void DbConnect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }

    public static void DbDisconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected from database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}