import java.util.ArrayList;
import java.util.List;

public class City {

    private int cityID;
    private double x;
    private double y;
    private List<Item> itemsList;

    public City(int cityID, double x, double y, List<Item> itemsList) {
        this.cityID = cityID;
        this.x = x;
        this.y = y;
        this.itemsList = itemsList;
    }

    public City(int cityID, double x, double y) {
        this.cityID = cityID;
        this.x = x;
        this.y = y;
        this.itemsList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "City{" +
                "cityID=" + cityID +
                ", x=" + x +
                ", y=" + y +
                ", itemsList=" + itemsList +
                '}';
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
