import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    private List<Item> itemsList;
    private int weightOfKnapsack;
    private int profitOfKnapsack;

    public Knapsack() {
        itemsList = new ArrayList<>();
    }

    public boolean addToKnapsack(Item item) {
        return itemsList.add(item);
    }

    private void countProfitOfKnapsack() {
        int sum = 0;
        if (itemsList != null && !itemsList.isEmpty()) {
            for (Item i: itemsList) {
                sum += i.getProfit();
            }
        }
        profitOfKnapsack = sum;
    }

    private void countWeightOfKnapsack() {
        int sum = 0;
        if (itemsList != null && !itemsList.isEmpty()) {
            for (Item i: itemsList) {
                sum += i.getWeight();
            }
        }
        weightOfKnapsack = 0;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public int getWeightOfKnapsack() {
        countWeightOfKnapsack();
        return weightOfKnapsack;
    }

    public int getProfitOfKnapsack() {
        countProfitOfKnapsack();
        return profitOfKnapsack;
    }


}
