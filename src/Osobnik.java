import java.util.ArrayList;
import java.util.List;

public class Osobnik {

    private int osobnikID;
    private List<City> visitedCities;
    private Knapsack knapsack;
    private double speed;
    private double road;

    public Osobnik() {
        visitedCities = new ArrayList<>();
    }

    public Osobnik(List<City> visitedCities){
        this.visitedCities = visitedCities;
    }

    public Osobnik(int osobnikID, double speed) {
        this.speed = osobnikID;
        visitedCities = new ArrayList<>();
        knapsack = new Knapsack();
        this.speed = speed;
    }

    public boolean addCity(City city) {

        return visitedCities.add(city);
    }

    public List<City> getVisitedCities() {
        return visitedCities;
    }
}
