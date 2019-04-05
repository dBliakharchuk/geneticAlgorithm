import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    private List<Item> itemsList;
    private int maxWeightOfKnapsack;
    private int weightOfKnapsack;
    private int profitOfKnapsack;

    public Knapsack(int maxWeight) {
        maxWeightOfKnapsack = maxWeight;
        profitOfKnapsack = 0;
        itemsList = new ArrayList<>();
    }

    public boolean addToKnapsack(Item item) {
        if (isFit(item)) {
            profitOfKnapsack += item.getProfit();
            setWeightOfKnapsack(weightOfKnapsack + item.getWeight());
            itemsList.add(item);
            return true;
        }
        return false;
    }

    public int countProfitOfKnapsack() {
        if (itemsList != null && !itemsList.isEmpty()) {
            int sum = 0;

            for (Item i: itemsList) {
                sum += i.getProfit();
            }
            profitOfKnapsack = sum;
        }
        return profitOfKnapsack;
    }

    private void calculateWeightOfKnapsack() {
        if (itemsList != null && !itemsList.isEmpty()) {
            int sum = 0;
            for (Item i: itemsList) {
                sum += i.getWeight();
                weightOfKnapsack = sum;
            }
        }
    }

    public void refreshKnapsack() {
        this.itemsList = new ArrayList<>();
        weightOfKnapsack = 0;
        profitOfKnapsack = 0;
    }

    public boolean isFit(Item item) {
        if (item != null) {
            return weightOfKnapsack + item.getWeight() <= maxWeightOfKnapsack;
        }
        return false;
    }

    public int getMaxWeightOfKnapsack() {
        return maxWeightOfKnapsack;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public int getWeightOfKnapsack() {
        return weightOfKnapsack;
    }

    public int getProfitOfKnapsack() {
        return profitOfKnapsack;
    }

    public void setWeightOfKnapsack(int weightOfKnapsack) {
        this.weightOfKnapsack = weightOfKnapsack;
    }

    public void setProfitOfKnapsack(int profitOfKnapsack) {
        this.profitOfKnapsack = profitOfKnapsack;
    }
}
