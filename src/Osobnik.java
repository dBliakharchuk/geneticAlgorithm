import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Osobnik {

    private int osobnikID;
    private List<City> visitedCities;
    private Knapsack knapsack;

    private int maxWeightOfKnapsack;
    private double maxSpeed;
    private double minSpeed;
    private double curSpeed;
    private double travelTime;
    private double profitability;


    public Osobnik(List<City> visitedCities, double maxSpeed, double minSpeed, int maxWeightOfKnapsack) {
        this.visitedCities = visitedCities;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        curSpeed = maxSpeed;
        this.maxWeightOfKnapsack = maxWeightOfKnapsack;
        knapsack = new Knapsack(maxWeightOfKnapsack);
    }

    public Osobnik(int numberOfCities, double maxSpeed, double minSpeed, int maxWeightOfKnapsack) {
        this.visitedCities = new ArrayList<>(numberOfCities);
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        curSpeed = maxSpeed;
        this.maxWeightOfKnapsack = maxWeightOfKnapsack;
        knapsack = new Knapsack(maxWeightOfKnapsack);
    }

    public Osobnik(List<City> visitedCities, double maxSpeed, double minSpeed, Knapsack knapsack) {
        this.visitedCities = visitedCities;
        this.knapsack = knapsack;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
    }



    public void createRoad() {
        ArrayList<City> suffledCities = (ArrayList<City>) visitedCities;
        ArrayList<City> tempList = new ArrayList<>(visitedCities.size());

        tempList.addAll(visitedCities);

        Collections.shuffle(tempList);
        setVisitedCities(tempList);
    }

    public boolean addCity(City city) {

        return visitedCities.add(city);
    }

    public boolean addItemToKnapsack(Item item) {
        boolean isAdded = false;
        if (item != null) {
            boolean isFit = knapsack.isFit(item);
            if (isFit) {
                isAdded = true;
                calculateCurSpeed();
                knapsack.addToKnapsack(item);
            }
        }
        return isAdded;
    }

    public boolean containsCity(City city){
        return visitedCities.contains(city);
    }

    private void calculateCurSpeed(){
        curSpeed = maxSpeed - knapsack.getWeightOfKnapsack()*((maxSpeed - minSpeed)/knapsack.getMaxWeightOfKnapsack());
    }

    public List<City> getVisitedCities() {
        return visitedCities;
    }

    public int getOsobnikID() {
        return osobnikID;
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public int getMaxWeightOfKnapsack() {
        return maxWeightOfKnapsack;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public double getCurSpeed() {
        return curSpeed;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public void setVisitedCities(List<City> visitedCities) {
        this.visitedCities = visitedCities;
    }

    @Override
    public String toString() {
        return " = " + profitability;
        /*return "Osobnik{" +
                "osobnikID=" + osobnikID +
                ", visitedCities=" + visitedCities +
                ", knapsack=" + knapsack +
                ", maxWeightOfKnapsack=" + maxWeightOfKnapsack +
                ", maxSpeed=" + maxSpeed +
                ", minSpeed=" + minSpeed +
                ", curSpeed=" + curSpeed +
                ", travelTime=" + travelTime +
                ", profitability=" + profitability +
                '}';*/
    }
}
