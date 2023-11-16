import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MovieServer{
    public static void main(String argv[]) {
        try {
            MovieInterface mi = new MovieImpl("MovieServer");
            MovieInterface stub = (MovieInterface) UnicastRemoteObject.exportObject(mi, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MovieServer",stub);

            Naming.rebind("//127.0.0.1/MovieServer", mi);
            System.out.println("Server Ready - service is running...");
        } catch(Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
