import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    private static final String MAIN_PATH = "C:\\Users\\Dima\\Desktop\\6semestrPWR\\Sztuczna Intelegencja\\geneticAlgorithm\\student\\";
    private static final String FILE_NAME = "hard_3.ttp";
    private static final double mutationRate = 0.1;
    private static final double tournamentRate = 1;
    private static final double crossoverRate = 0.7;
    private static final int numberOfBestThieves = 20;
    private static final boolean isTournamentSelection = true;

    private static final int SIZE_OF_GANG = 1000;
    private static final int EVOLVE_RATE = 100;

    private static final int Y_MAX_AXIS = -1500000;
    private static final int Y_MIN_AXIS = -2209924;

    private static final String chartTitle = "Tournament=1 hard=3 PM = 0.1 PX = 0,7" ;

    private static final String firstName = "max";
    private static final String secondName= "avg";
    private static final String thirdName = "min";

    public static void main(String[] args) {

        //Initialize date for graphic
        double[] xAxis = new double[EVOLVE_RATE];
        double[] yMin = new double[EVOLVE_RATE];
        double[] yAvg = new double[EVOLVE_RATE];
        double[] yMax = new double[EVOLVE_RATE];

        //Create charts
        XYSeries max = new XYSeries(firstName);
        XYSeries avg = new XYSeries(secondName);
        XYSeries min = new XYSeries(thirdName);

        XYSeriesCollection dataset = new XYSeriesCollection();
        //Initialize genetic algorithm
        //GA ga = new GA(SIZE_OF_GANG);
        GA ga = ga = new GA(SIZE_OF_GANG, (MAIN_PATH + FILE_NAME), crossoverRate, mutationRate, tournamentRate, numberOfBestThieves, isTournamentSelection);

            for (int i = 0; i < EVOLVE_RATE; i++) {

                ga.calculateWholePopulation();

                xAxis[i] = i + 1;
                yMax[i] = ga.getBestThief().getProfitability();
                yAvg[i] = ga.getAvgFitnessFromGang();
                yMin[i] = ga.getMinFitnessFromGang();

                max.add(xAxis[i], yMax[i]);
                avg.add(xAxis[i], yAvg[i]);
                min.add(xAxis[i], yMin[i]);

                System.out.printf("%30s", yMin[i]);
                System.out.printf("%30s", yAvg[i]);
                System.out.printf("%30s", yMax[i]);
                System.out.println();

            }


        System.out.println("Finished");
        System.out.println("Profitability of last population is: " + ga.getBestThief());

        //Display chart
        dataset.addSeries(max);
        dataset.addSeries(avg);
        dataset.addSeries(min);
        CreateJFrame chart = new CreateJFrame(dataset,Y_MAX_AXIS,Y_MIN_AXIS, chartTitle);
        chart.setVisible(true);











    }
}
