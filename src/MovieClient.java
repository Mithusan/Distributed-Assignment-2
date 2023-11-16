import java.rmi.Naming;
import java.util.*;

public class MovieClient {
    public static void main(String[] args){
        try{
            System.out.println("Client Ready - remote stub active....");
            MovieInterface list = (MovieInterface) Naming.lookup("MovieServer");
            list.initMovies("");
            String genres = list.getMovieGenres();

            Scanner sc = new Scanner(System.in);
            String input;
            String output;
            int outer = 0;

            System.out.println("\n***************************\n\nMovie List App\n\n***************************");
            System.out.println("Select a Service By Entering the Provided Values: ");

            while(outer == 0){
                System.out.println("1.Find Movies to Watch" +
                        "\n2.Add Movies to Your List" +
                        "\n3.Get Movie Recommendations" +
                        "\n4.Remove Movies from Your List" +
                        "\n5.See all Movies in your List" +
                        "\n-----------------------------------");
                input = sc.nextLine().toLowerCase();

                System.out.println("\n");
                switch (input) {
                    case "1":
                        System.out.println("What Movie Genre would you like to Search for?" +
                                "\n" + genres + "\n-----------------------------------");
                        input = sc.nextLine();

                        String filteredMovies = list.findMovies(input);
                        System.out.println(filteredMovies);
                        break;

                    case "2":
                        System.out.println("What Movie would You Like to add to your List?");
                        input = sc.nextLine();

                        if(!list.addMovie(input)){
                            System.out.println("Error!" + input +" does not Exist. Please Try again.");
                        }else{
                            System.out.println(input + " has been Added to your List");
                        }
                        break;

                    case "3":
                        output = list.movieRecommendation();
                        System.out.println(output);
                        break;

                    case "4":
                        System.out.println("What Movie would You Like to Remove from your List?");
                        input = sc.nextLine();

                        if(!list.removeMovie(input)){
                            System.out.println("Error!" + input + " does not Exist. Please Try again.");
                        }else{
                            System.out.println(input + " has been Removed from your List");
                        }

                        break;

                    case "5":
                        output = list.viewList();
                        System.out.println(output);
                        break;

                    case "close":
                        System.out.println("Goodbye.");
                        outer++;
                        break;

                    default:
                        System.out.println("Please Enter a Valid Entry.");
                        break;
                }

                System.out.println("\nYou can Leave by entering \"Close\"");
            }

        } catch(Exception e) {
            System.err.println("Shopping Cart Server exception: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
