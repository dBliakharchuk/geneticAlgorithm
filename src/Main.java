import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String MAIN_PATH = "C:\\Users\\Dima\\IdeaProjects\\geneticAlgorithm\\student\\";

    public static void main(String[] args) {

        GA ga = new GA(15);
       /* ga.findTheBestOsobnikFromGang();
        ga.createRandomGang();
        ga.findTheBestOsobnikFromGang();
*/
        ArrayList<Osobnik> tempPOP = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tempPOP = (ArrayList<Osobnik>) ga.calculateWholePopulation();
            System.out.println("Profitability of " + i + " population is: " + ga.getTheBestOsobnik());

            System.out.println("********");
        }


    }
}
