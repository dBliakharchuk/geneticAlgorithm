import java.util.*;

public class GA {
    private double mutationRate = 0.015;
    private double tournamentRate = 10;
    private double crossoverRate = 0.7;
    private static final String MAIN_PATH = "C:\\Users\\Dima\\Desktop\\6semestrPWR\\Sztuczna Intelegencja\\geneticAlgorithm\\student\\";
    private static final String FILE_NAME = "hard_4.ttp";
    private int numberOfBestThieves = 10;
    private boolean isTournamentSelection = true;


    private List<Osobnik> gang;
    private List<Osobnik> listOfBestThieves;
    private Osobnik theBestThief;
    private int sizeOfGang;

    private Reader reader;


     GA (int sizeOfGang, String file_path, double crossoverRate, double mutationRate, double tournamentRate, int numberOfBestThieves, boolean isTournamentSelection) {
         this.numberOfBestThieves = numberOfBestThieves;
         this.crossoverRate = crossoverRate;
         this.mutationRate = mutationRate;
         this.tournamentRate = tournamentRate;
         this.sizeOfGang = sizeOfGang;
         this.isTournamentSelection= isTournamentSelection;

         gang = new ArrayList<Osobnik>(sizeOfGang + numberOfBestThieves);
         listOfBestThieves = new ArrayList<>(numberOfBestThieves);
         reader = new Reader(file_path);
         theBestThief = null;
     }
     GA (int sizeOfGang) {
        this.sizeOfGang = sizeOfGang;
        gang = new ArrayList<Osobnik>(sizeOfGang + numberOfBestThieves);
        listOfBestThieves = new ArrayList<>(numberOfBestThieves);
        reader = new Reader(MAIN_PATH + FILE_NAME);
        theBestThief = null;

    }



    List<Osobnik> calculateWholePopulation(){
        if (gang.isEmpty()) createRandomGang();

        for (int i = 0; i < gang.size(); i++) {
            Logic.calculateProfitabilityOfTrip(gang.get(i));
        }

        //Collections.sort(listOfBestThieves);
        findAndSetTheBestThievesFromGang();

        List<Osobnik> tempListOfThieves = evaluate();

        for (int i = 0; i < gang.size(); i++) {
            gang.set(i,tempListOfThieves.get(i));
        }

        for (int i = 0; i < numberOfBestThieves; i++) {
            gang.set(i,listOfBestThieves.get(i));
        }


        Collections.sort(listOfBestThieves);
        findAndSetTheBestThievesFromGang();
        findAndChangeBestThief();

        return gang;
    }


     private void createRandomGang() {
        reader.load();

        for (int i = 0; i < sizeOfGang + numberOfBestThieves; i++) {
            Osobnik thief = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
            thief.createRoad();
            gang.add(thief);
        }
    }

    private void findAndSetTheBestThievesFromGang() {
        if (gang != null && !gang.isEmpty()) {
            if (listOfBestThieves.isEmpty()) {
                fillOutListOfTheBestThievesAtStart();
            }

            for (int i = numberOfBestThieves; i < gang.size(); i++) {
                Osobnik currThief = gang.get(i);
                Collections.sort(listOfBestThieves);
                Osobnik worstThiefFromTheBest = listOfBestThieves.get(0);
                    if (currThief.getProfitability() > worstThiefFromTheBest.getProfitability() && !listOfBestThieves.contains(currThief) ) {
                        //worstThiefFromTheBest.createRoad();
                        gang.set(i, worstThiefFromTheBest);
                        listOfBestThieves.set(listOfBestThieves.indexOf(worstThiefFromTheBest), currThief);

                    }
            }
        } else {
            System.out.println("findAndSetTheBestThievesFromGang: GANG IS NULL");
        }
    }
    private void fillOutListOfTheBestThievesAtStart() {
        for (int i = numberOfBestThieves; i < 2 * numberOfBestThieves; i++) {
            Logic.calculateProfitabilityOfTrip(gang.get(i));
            listOfBestThieves.add(gang.get(i));
        }
    }

    /*private Osobnik getWorstThiefFromTheBest() {
        Osobnik worstThiefFromBest  = null;
        if (listOfBestThieves != null && !listOfBestThieves.isEmpty()) {
            worstThiefFromBest  = gang.get(numberOfBestThieves);
            for (Osobnik currThief: listOfBestThieves) {
                if (worstThiefFromBest.getProfitability() > currThief.getProfitability()) worstThiefFromBest = currThief;
            }
        } else {
            System.out.println("getWorstThiefFromTheBest: LIST OF THE BEST THIFS iS EMPTY or null!!!!!");
        }
        return worstThiefFromBest;
    }*/


    public List<Osobnik> evaluate() {

        List<Osobnik> newEvaluatedGang = new ArrayList<>();

        for (Osobnik thief : gang) {
            if (Math.random() < crossoverRate) {
                Osobnik parent1 = null;
                Osobnik parent2 = null;
                if (isTournamentSelection) {
                    //tourSelection
                    parent1 = tourSelection();
                    parent2 = tourSelection();
                } else {
                    //roulleteSelection
                    parent1 = roulleteSelection();
                    parent2 = roulleteSelection();
                }

                //crossoverRate
                Osobnik child = crossover(parent1, parent2);
                Logic.calculateProfitabilityOfTrip(child);
                newEvaluatedGang.add(child);
            } else {
                thief.createRoad();
                newEvaluatedGang.add(thief);
            }
        }

        //mutation
        for (Osobnik o: newEvaluatedGang){
            mutate(o);
        }

        return newEvaluatedGang;
    }



    //Crossover
    public Osobnik crossover(Osobnik parent1, Osobnik parent2) {
        Osobnik child = null;
        if (parent1 != null && parent2 != null) {
            if (parent1.getVisitedCities() != null && parent2.getVisitedCities() != null) {
                 child = new Osobnik(parent1.getVisitedCities().size(), parent1.getMaxSpeed(), parent1.getMinSpeed(), parent1.getMaxWeightOfKnapsack());

                 int pivot = (int) (Math.random()*(parent1.getVisitedCities().size()));

                 for (int i = 0; i < pivot; i++) {
                     child.addCity(parent1.getVisitedCities().get(i));
                 }

                 for (int i = 0; i < parent2.getVisitedCities().size(); i++) {
                     if (!child.containsCity(parent2.getVisitedCities().get(i))) {
                         child.addCity(parent2.getVisitedCities().get(i));
                     }
                 }
            }
        }
        Logic.calculateProfitabilityOfTrip(child);
        return child;
    }


    //Mutation
    private void mutate(Osobnik thief) {
        
        if (thief != null) {
            if (thief.getVisitedCities() != null && !thief.getVisitedCities().isEmpty()) {
                if (Math.random() < mutationRate) {
                    for (int pos1 = 0; pos1 < thief.getVisitedCities().size(); pos1++){
                        int pos2 = (int) (Math.random() * thief.getVisitedCities().size());
                        if (pos1 != pos2) {
                            City city1 = thief.getVisitedCities().get(pos1);
                            City city2 = thief.getVisitedCities().get(pos2);

                            thief.getVisitedCities().set(pos1, city2);
                            thief.getVisitedCities().set(pos2, city1);

                        }
                    }
                }
            } else { System.out.println("MUTATE: OSOBNIK DOESN'T HAVE CITIES" ); }
        } else { System.out.println("MUTATE: OSOBNIK IS NULL"); }
    }

    private void findAndChangeBestThief() {
        if (gang != null && !gang.isEmpty()) {
            Osobnik theBestThief = getBestThief();
            double theBestProfitability = 0;

            if (theBestThief == null) {
                theBestThief = listOfBestThieves.get(numberOfBestThieves-1);
            }

            theBestProfitability = theBestThief.getProfitability();

            for (Osobnik o : listOfBestThieves) {
                if (o.getProfitability() > theBestProfitability) {
                    theBestProfitability = o.getProfitability();
                    theBestThief = o;
                }
            }
            
            setTheBestThief(theBestThief);
        } else {
            System.out.println("findAndChangeBestThief: GANG IS NULL");
        }
    }

    //The best thief in the tournament
    public Osobnik tourSelection() {
        List<Osobnik> listOfRandomThiefs = new ArrayList<>();
        Osobnik bestRandomThief = null;
        if (gang != null && !gang.isEmpty()) {
            for (int i = 0; i < tournamentRate; i++) {
                int randNumb = (int) (Math.random() * gang.size());
                bestRandomThief = gang.get(randNumb);
                listOfRandomThiefs.add(bestRandomThief);
            }

            for (Osobnik o: listOfRandomThiefs) {
                if (o.getProfitability() > bestRandomThief.getProfitability()) bestRandomThief = o;
            }
        } else {
            System.out.println("SELECTION: GANG IS EMPTY");
        }
        return bestRandomThief;
    }

     Osobnik roulleteSelection(){
        double totalFitness = 0;
        for(int i = 0; i < gang.size(); i++){
            totalFitness += gang.get(i).getProfitability();
        }
        double value = (Math.random()*totalFitness);
        for(int i = 0; i < gang.size(); i++){
            value -= gang.get(i).getProfitability();
            if(value < 0)
                return gang.get(i);
        }
        return gang.get(gang.size() - 1);


    }

    public int getSizeOfGang() {
        return gang.size();
    }

    public double getAvgFitnessFromGang() {
        double sum = 0;

        if (gang != null) {
             for (Osobnik o: gang) {
                 sum += o.getProfitability();
             }
            return sum/gang.size();
        }
         return -1.0;
    }

    public double getMinFitnessFromGang() {
         double min = Double.MAX_VALUE;
         if (gang != null) {
             for (Osobnik o: gang) {
                 if (o.getProfitability() < min) min = o.getProfitability();
             }
         }
        return min;
    }




    public Osobnik getBestThief() {
        return theBestThief;
    }

    public void setTheBestThief(Osobnik theBestThief) {
        this.theBestThief = theBestThief;
    }

    public void setGang(List<Osobnik> gang) {
        this.gang = gang;
    }

    public List<Osobnik> getGang() {
        return gang;
    }

    public List<Osobnik> getListOfBestThieves() {
        return listOfBestThieves;
    }

    public void setListOfBestThieves(List<Osobnik> listOfBestThieves) {
        this.listOfBestThieves = listOfBestThieves;
    }
}
