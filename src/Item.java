public class Item {

    private int node;
    private int weight;
    private int profit;

    public Item(int profit, int weight, int node) {
        this.node = node;
        this.weight = weight;
        this.profit = profit;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "node=" + node +
                ", weight=" + weight +
                ", profit=" + profit +
                '}';
    }
}
