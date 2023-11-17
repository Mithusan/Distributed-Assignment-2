import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MovieServer{
    public static void main(String argv[]) {
        try {
            MovieInterface mi = new MovieImpl("MovieServer");
            MovieInterface stub = (MovieInterface) UnicastRemoteObject.exportObject(mi, 0);

            // Obtaining the registry on the server's host
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MovieServer",stub); // Binding stub with a unique name

            // Rebinding the stub to a specific name in the RMI registry
            Naming.rebind("//127.0.0.1/MovieServer", mi);
            System.out.println("Server Ready - service is running...");
        } catch(Exception e) {
            //Error
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
