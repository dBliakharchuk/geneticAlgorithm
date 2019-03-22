import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String MAIN_PATH = "C:\\Users\\Dima\\IdeaProjects\\geneticAlgorithm\\student\\";

    public static void main(String[] args) {
        Reader reader = new Reader(MAIN_PATH + "easy_0.ttp");
        reader.load();

        Osobnik osobnik1 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik2 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik3 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik4 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik5 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik6 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
        Osobnik osobnik7 = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());

        osobnik1.createRoad();
        osobnik2.createRoad();
        osobnik3.createRoad();
        osobnik4.createRoad();
        osobnik5.createRoad();
        osobnik6.createRoad();
        osobnik7.createRoad();



        ArrayList<Osobnik> gang = new ArrayList<>();
        gang.add(osobnik1);
        gang.add(osobnik2);
        gang.add(osobnik3);
        gang.add(osobnik4);
        gang.add(osobnik5);
        gang.add(osobnik6);
        gang.add(osobnik7);

        GA ga = new GA(gang);
        ArrayList<Osobnik> os = (ArrayList<Osobnik>) ga.evaluate();

        for (Osobnik o: os) {
            System.out.println("OSOBNIK: ---------------------------------------------------");
            System.out.println(Logic.calculateTimeOfTrip(o));
            System.out.println(Logic.calculateProfitabilityOfTrip(o));
        }



        //System.out.println("Child: ---" + ga.crossover(osobnik1, osobnik2));





        /*System.out.println(Logic.calculateTimeOfTrip(osobnik1));

        System.out.println(osobnik1.getKnapsack().getProfitOfKnapsack());
        System.out.println(Logic.calculateProfitabilityOfTrip(osobnik1));*/






        /*for(int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                System.out.print(distanceMatrix[i][j] + " ") ;
            }
            System.out.println();
        }*/

        /*System.out.println(Arrays.toString(listOfCities.toArray()));
        System.out.println(stringList.size());*/




    }
}
