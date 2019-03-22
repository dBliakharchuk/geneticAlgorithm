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

    public Item mostvaluableItem(){
        Item valuableItem = null;
        if(!itemsList.isEmpty()){
            valuableItem = itemsList.get(0);
            Item tempItem;
            double currValue = (double) valuableItem.getProfit()/valuableItem.getWeight();
            for(int i = 1; i < itemsList.size() - 1; i++){
                tempItem = itemsList.get(i);
                double value = (double) tempItem.getProfit()/tempItem.getWeight();
                if(value > currValue){
                    currValue = value;
                    valuableItem = tempItem;
                }
            }
        }
        return valuableItem;

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
