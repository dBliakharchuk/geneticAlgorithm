import java.util.ArrayList;
import java.util.List;

public class GA {
    private static final double mutationRate = 0.015;
    private static final double tournamentRate = 5;
    private static final double crossover = 0.7;

    private List<Osobnik> Gang;
    private Osobnik theBestOsobnik;




    public GA(int sizeOfGang) {
        Gang = new ArrayList<Osobnik>(sizeOfGang);
    }

    public GA(List<Osobnik> gang) {
        this.Gang = gang;
    }

    public List<Osobnik> evaluate() {

        List<Osobnik> tempGang = new ArrayList<>();

        for (int i = 0; i < Gang.size(); i++) {
            if (Math.random() < crossover) {
                //selection
                Osobnik parent1 = selection();
                Osobnik parent2 = selection();
                //crossover
                Osobnik child = crossover(parent1,parent2);
                //
                tempGang.add(child);
            } else {
                tempGang.add(Gang.get(i));
            }
        }

        //mutation
        for (Osobnik o: tempGang){
            mutate(o);
        }

        return tempGang;
    }



    //Crossover
    public Osobnik crossover(Osobnik parent1, Osobnik parent2) {
        Osobnik child = null;
        if (parent1 != null && parent2 != null) {
            if (parent1.getVisitedCities() != null && parent2.getVisitedCities() != null) {
                 child = new Osobnik(parent1.getVisitedCities().size(), parent1.getMaxSpeed(), parent1.getMinSpeed(), parent1.getMaxWeightOfKnapsack());

                 int pivot = (int) (Math.random()*parent1.getVisitedCities().size());

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
        return child;
    }



    //Mutation
    private void mutate(Osobnik osobnik) {
        if (osobnik != null) {
            if (osobnik.getVisitedCities() != null && !osobnik.getVisitedCities().isEmpty()) {
                if (Math.random() < mutationRate) {
                    for (int pos1 = 0; pos1 < osobnik.getVisitedCities().size(); pos1++){
                        int pos2 = (int) (Math.random() * osobnik.getVisitedCities().size());
                        if (pos1 != pos2) {
                            City city1 = osobnik.getVisitedCities().get(pos1);
                            City city2 = osobnik.getVisitedCities().get(pos2);

                            osobnik.getVisitedCities().set(pos1, city2);
                            osobnik.getVisitedCities().set(pos2, city1);

                        }
                    }
                }
            }
        }
    }


    //The best osobnik in the Gang
    public Osobnik selection() {
        List<Osobnik> tempGang = new ArrayList<>();
        Osobnik temp = null;
        if (Gang != null && !Gang.isEmpty()) {
            for (int i = 0; i < tournamentRate; i++) {
                int randNumb = (int) (Math.random() * Gang.size());
                temp = Gang.get(randNumb);
                tempGang.add(temp);
            }

            for (Osobnik o: tempGang) {
                if (o.getProfitability() > temp.getProfitability()) temp = o;
            }
        }

        setTheBestOsobnik(temp);
        return temp;
    }

    public int getSizeOfGang() {
        return Gang.size();
    }


    public Osobnik getTheBestOsobnik() {
        return theBestOsobnik;
    }

    public void setTheBestOsobnik(Osobnik theBestOsobnik) {
        this.theBestOsobnik = theBestOsobnik;
    }
}
