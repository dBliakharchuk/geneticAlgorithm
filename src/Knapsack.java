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
        profitOfKnapsack += item.getProfit();
        setWeightOfKnapsack(weightOfKnapsack + item.getWeight());
        return itemsList.add(item);
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

    private void countWeightOfKnapsack() {
        if (itemsList != null && !itemsList.isEmpty()) {
            int sum = 0;
            for (Item i: itemsList) {
                sum += i.getWeight();
                weightOfKnapsack = sum;
            }
        }
    }

    public boolean isFit(Item item) {
        if (item != null) {
            if (weightOfKnapsack + item.getWeight() <= maxWeightOfKnapsack)
            {
                weightOfKnapsack += item.getWeight();
                return true;
            }

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
