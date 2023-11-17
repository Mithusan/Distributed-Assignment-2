import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MovieInterface extends Remote {
    // Initializes movie data based on provided information
    String initMovies(String s) throws RemoteException;

    // Retrieves available movie genres
    String getMovieGenres() throws RemoteException;

    // Finds movies based on a specified genre
    String findMovies(String genre) throws RemoteException;

    // Provides movie recommendations based on existing movie selections
    String movieRecommendation() throws RemoteException;

    // Adds a movie to the client's movie list
    boolean addMovie(String movieName) throws RemoteException;

    // Removes a movie from the client's movie list
    boolean removeMovie(String movieName) throws RemoteException;

    // Displays the client's movie list
    String viewList() throws RemoteException;
}
