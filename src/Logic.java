import java.text.CollationElementIterator;
import java.util.*;
import java.util.logging.Logger;

public class Logic {

    private final static Logger logger = Logger.getLogger("Logic.class");

    private double[][] distanceMatrix;
    private List<City> listOfCities;
    private double minSpeed;
    private double maxSpeed;
    private double curSpeed;

    public Logic(List<City> listOfCities, double minSpeed, double maxSpeed) {
        this.listOfCities = listOfCities;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        curSpeed = maxSpeed;
    }

    public Osobnik createRandomOsobnik() {

        ArrayList<City> suffledCities = (ArrayList<City>) listOfCities;
        Collections.shuffle(suffledCities);

        Osobnik osobnik = new Osobnik(suffledCities);
        logger.info("createRandomOsobnik: random Osobnik created");

        return osobnik;
    }

    public double calculateDistance(Osobnik osobnik) {
        double result = 0;

        if (osobnik != null) {
            ArrayList<City> visitedCities = (ArrayList<City>) osobnik.getVisitedCities();

            if (visitedCities != null && !visitedCities.isEmpty()) {

                int numberOfCities = visitedCities.size();
                int currentIdCity;
                int nextIdCity;

                for (int i = 0; i < numberOfCities - 1; i++){
                    currentIdCity = visitedCities.get(i).getCityID();
                    nextIdCity = visitedCities.get(i+1).getCityID();

                    result += distanceMatrix[currentIdCity][nextIdCity]/curSpeed;
                }
                currentIdCity = visitedCities.get(numberOfCities-1).getCityID();
                nextIdCity = visitedCities.get(0).getCityID();
                result += distanceMatrix[currentIdCity][nextIdCity]/curSpeed;
            }
        }

        return result;
    }


    private void createDistanceMatrix() {
        if (listOfCities != null && !listOfCities.isEmpty()) {
            int sizeOfCityList = listOfCities.size();
            distanceMatrix = new double[sizeOfCityList][sizeOfCityList];
            for (int i = 0; i < sizeOfCityList; i++) {
                for (int j = 0; j < sizeOfCityList; j++) {
                    double x1 = listOfCities.get(i).getX();
                    double x2 = listOfCities.get(j).getX();
                    double y1 = listOfCities.get(i).getY();
                    double y2 = listOfCities.get(j).getY();

                    distanceMatrix[i][j] = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
                }
            }
            System.out.println(Arrays.toString(distanceMatrix));
            logger.info("getDistanceMatrix: Matrix created successfully");

        }
    }



    public double[][] getDistanceMatrix() {
        createDistanceMatrix();
        return distanceMatrix;
    }
}
