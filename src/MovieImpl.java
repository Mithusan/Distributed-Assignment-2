import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieImpl implements MovieInterface {
    List<Movie> movies = new ArrayList<>();
    List<Movie> list = new ArrayList<>();
    String output;

    public MovieImpl(String s) throws RemoteException{
        super();
    }

    public String initMovies(String s) throws RemoteException{
        movies.add(new Movie("Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("Pulp Fiction", "Crime", 8.9));
        movies.add(new Movie("The Godfather", "Crime", 9.2));
        movies.add(new Movie("The Purge", "Crime", 5.7));
        movies.add(new Movie("Forrest Gump", "Drama", 8.8));
        movies.add(new Movie("The Shawshank Redemption", "Drama", 9.3));
        movies.add(new Movie("The Dark Knight", "Action", 9.0));
        movies.add(new Movie("Spider-man", "Action", 9.8));
        movies.add(new Movie("The Lord of the Rings", "Fantasy", 8.9));

        return null;
    }

    public String getMovieGenres() throws RemoteException{
        List<String> genreList = new ArrayList<>();
        output = "";

        for (Movie movie : movies) {
            if(!genreList.contains(movie.getGenre())) {
                genreList.add(movie.getGenre());
            }
        }

        output = String.join("\n", genreList);

        return output;
    }

    @Override
    public String findMovies(String genre) throws RemoteException {
        List<Movie> filteredMovies = new ArrayList<>();
        output = "";

        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filteredMovies.add(movie);
            }
        }

        // Printing the filtered movies
        for (Movie movie : filteredMovies) {
            output += ("Title: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Rating: " + movie.getRating() + "\n");
        }

        return output;
    }

    @Override
    public String movieRecommendation() throws RemoteException {
        List<String> genreList = new ArrayList<>();
        List<Movie> recMovie = new ArrayList<>();
        output = "";

        for (Movie movie : list) {
            if(!genreList.contains(movie.getGenre())) {
                genreList.add(movie.getGenre());
            }
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if(genreList.contains(movie.getGenre())) {
                if(!list.contains(movie)) {
                    filteredMovies.add(movie);
                }
            }
        }

        // Printing the filtered movies
        for (Movie movie : filteredMovies) {
            output += ("Title: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Rating: " + movie.getRating() + "\n");
        }

        return output;
    }

    @Override
    public boolean addMovie(String movieName) throws RemoteException {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                if(!list.contains(movie)) {
                    list.add(movie);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeMovie(String movieName) throws RemoteException {
        for (Movie movie : list) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                list.remove(movie);
                return true;
            }
        }
        return false;
    }

    @Override
    public String viewList() throws RemoteException {
        String userList = "";
        for (Movie movie : list) {
            userList += "Movie: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Rating: " + movie.getRating() + "\n";
        }

        return userList;
    }
}
