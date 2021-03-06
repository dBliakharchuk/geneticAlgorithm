import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.logging.Logger;

public class Logic {

    private final static Logger logger = Logger.getLogger("Logic.class");

    private static double[][] distanceMatrix;



    public static double calculateProfitabilityOfTrip(Osobnik osobnik) {
        if (osobnik != null) {
            osobnik.getKnapsack().refreshKnapsack();
            calculateTimeOfTrip(osobnik);
            double profitability = osobnik.getKnapsack().getProfitOfKnapsack() - osobnik.getTravelTime();
            osobnik.setProfitability(profitability);
            return profitability;
        } else {
            logger.info("calculateProfitabilityOfTrip osobnik = null");
            return Double.MAX_VALUE;
        }
    }

    //f(x,y)
    private static double calculateTimeOfTrip(Osobnik osobnik) {
        double result = 0;

        if (osobnik != null) {
            ArrayList<City> visitedCities = (ArrayList<City>) osobnik.getVisitedCities();


            if (visitedCities != null && !visitedCities.isEmpty()) {
                //create cityMatrix
                if (distanceMatrix == null) createDistanceMatrix(visitedCities);

                int numberOfCities = visitedCities.size();
                int currentIdCity;
                int nextIdCity;

                for (int i = 0; i < numberOfCities - 1; i++) {
                    currentIdCity = visitedCities.get(i).getCityID();
                    nextIdCity = visitedCities.get(i + 1).getCityID();

                    boolean isItemAdded = osobnik.addItemToKnapsack(visitedCities.get(currentIdCity).mostvaluableItem());
                    if (isItemAdded) result += distanceMatrix[currentIdCity][nextIdCity] / osobnik.getCurSpeed();
                }

                    currentIdCity = visitedCities.get(numberOfCities - 1).getCityID();
                    nextIdCity = visitedCities.get(0).getCityID();
                    result += (distanceMatrix[currentIdCity][nextIdCity] / osobnik.getCurSpeed());
                    osobnik.setTravelTime(result);
            } else {
                logger.info("calculateTimeOfTrip: osobnik doesnt have cities");

            }
        } else {
            logger.info("calculateTimeOfTrip: osobnik = null" );
        }
        return result;
    }


    private static void createDistanceMatrix(ArrayList<City> listOfCities) {
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
        } else {
            logger.info("createDistanceMatrix: listOfCities is Empty");
        }
    }

    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }
}
