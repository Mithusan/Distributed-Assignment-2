import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MovieImpl implements MovieInterface {
    List<Movie> movies = new ArrayList<>();
    List<Movie> list = new ArrayList<>();
    String output;

    // Constructor initializing the MovieImpl object
    public MovieImpl(String s) throws RemoteException{
        super();
    }

    // Method to initialize movie data
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

    // Method to retrieve available movie genres
    public String getMovieGenres() throws RemoteException{
        List<String> genreList = new ArrayList<>();
        output = "";

        // Creating a list of unique movie genres
        for (Movie movie : movies) {
            if(!genreList.contains(movie.getGenre())) {
                genreList.add(movie.getGenre());
            }
        }

        // Joining the genre list elements into a single string
        output = String.join("\n", genreList);

        return output;
    }

    // Method to find movies based on a specified genre
    @Override
    public String findMovies(String genre) throws RemoteException {
        List<Movie> filteredMovies = new ArrayList<>();
        output = "";

        // Filtering movies by the specified genre
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filteredMovies.add(movie);
            }
        }

        // Generating a formatted output of filtered movies
        for (Movie movie : filteredMovies) {
            output += ("Title: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Rating: " + movie.getRating() + "\n");
        }

        return output;
    }

    // Method to provide movie recommendations based on existing movie selections
    @Override
    public String movieRecommendation() throws RemoteException {
        List<String> genreList = new ArrayList<>();
        List<Movie> recMovie = new ArrayList<>();
        output = "";

        // Gets all unique genres in client list
        for (Movie movie : list) {
            if(!genreList.contains(movie.getGenre())) {
                genreList.add(movie.getGenre());
            }
        }

        // Filtering movies by the specified genres found
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

    // Method to add a movie to the client's movie list
    @Override
    public boolean addMovie(String movieName) throws RemoteException {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) { // If movie exists on server list
                if(!list.contains(movie)) { // if movie doesn't already exist in client list
                    list.add(movie);
                    return true;
                }
            }
        }
        return false;
    }

    // Method to remove a movie from the client's movie list
    @Override
    public boolean removeMovie(String movieName) throws RemoteException {
        for (Movie movie : list) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) { //If movie exists on client list
                list.remove(movie);
                return true;
            }
        }
        return false;
    }

    // Method to display the client's movie list
    @Override
    public String viewList() throws RemoteException {
        String userList = "";

        // Printing client list
        for (Movie movie : list) {
            userList += "Movie: " + movie.getTitle() +
                    ", Genre: " + movie.getGenre() +
                    ", Rating: " + movie.getRating() + "\n";
        }

        return userList;
    }
}
