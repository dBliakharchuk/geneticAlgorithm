import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static final String MAIN_PATH = "C:\\Users\\Dima\\IdeaProjects\\geneticAlgorithm\\student\\";

    public static void main(String[] args) {
        Reader reader = new Reader(MAIN_PATH + "easy_0.ttp");
        ArrayList<String> stringList = reader.getFileList();
        ArrayList<City> listOfCities = reader.getCitiesList();

        System.out.println("Max: " + reader.getMaxSpeed());
        System.out.println("Min: " + reader.getMinSpeed());



        Logic logic = new Logic(listOfCities, reader.getMinSpeed(), reader.getMaxSpeed());
        double[][] distanceMatrix = logic.getDistanceMatrix();

        Osobnik osobnik = logic.createRandomOsobnik();
        System.out.println(logic.calculateDistance(osobnik));






        /*for(int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                System.out.print(distanceMatrix[i][j] + " ") ;
            }
            System.out.println();
        }*/

        /*System.out.println(Arrays.toString(listOfCities.toArray()));
        System.out.println(stringList.size());*/




    }
}
