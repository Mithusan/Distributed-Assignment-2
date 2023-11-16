import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MovieInterface extends Remote {
    String initMovies(String s) throws RemoteException;

    String getMovieGenres() throws RemoteException;

    String findMovies(String genre) throws RemoteException;

    String movieRecommendation() throws RemoteException;

    boolean addMovie(String movieName) throws RemoteException;

    boolean removeMovie(String movieName) throws RemoteException;

    String viewList() throws RemoteException;
}
