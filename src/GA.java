import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GA {
    private static final double mutationRate = 0.015;
    private static final double tournamentRate = 5;
    private static final double crossover = 0.7;
    private static final String MAIN_PATH = "C:\\Users\\Dima\\Desktop\\6semestrPWR\\Sztuczna Intelegencja\\geneticAlgorithm\\student\\";
    private static final String FILE_NAME = "easy_0.ttp";

    private List<Osobnik> gang;
    private List<Osobnik> listTheBestOsobnik;
    private List<Osobnik> lastGang;
    private Osobnik theBestOsobnik;
    private int sizeOfGang = 0;

    private int numberTheBestOsobnik = 5;

    Reader reader;


    public GA(int sizeOfGang) {
        gang = new ArrayList<Osobnik>(sizeOfGang);
        listTheBestOsobnik = new ArrayList<Osobnik>();
        lastGang = new ArrayList<Osobnik>();
        reader = new Reader(MAIN_PATH + FILE_NAME);
        this.sizeOfGang = sizeOfGang;

    }

    public GA(List<Osobnik> gang) {
        this.gang = gang;
    }


    public List<Osobnik> calculateWholePopulation(){
        if (gang.size() == 0) createRandomGang();

        for (int i = 0; i < gang.size(); i++) {
            Logic.calculateProfitabilityOfTrip(gang.get(i));
        }
        findTheBestOsobnikFromGang();

        for (int i = 0; i < listTheBestOsobnik.size(); i++) {
            gang.set(i,listTheBestOsobnik.get(i));
        }


        List<Osobnik> tempListOsobnik = evaluate();

        for (int i = numberTheBestOsobnik; i < gang.size(); i++) {
            gang.set(i,tempListOsobnik.get(i-numberTheBestOsobnik));
        }

        for (int i = 0; i < gang.size(); i++) {
            Logic.calculateProfitabilityOfTrip(gang.get(i));
        }

        findTheBestOsobnikFromGang();
        findTheBest();


        return gang;
    }


    public List<Osobnik> createRandomGang() {
        reader.load();

        for (int i = 0; i < sizeOfGang; i++) {
            Osobnik osobnik = new Osobnik(reader.getCitiesList(), reader.getMaxSpeed(), reader.getMinSpeed(), reader.getCapacityOfKnapsack());
            osobnik.createRoad();
            Logic.calculateProfitabilityOfTrip(osobnik);
            gang.add(osobnik);
        }

        return gang;
    }

    public void findTheBestOsobnikFromGang () {
        if (gang != null && !gang.isEmpty()) {
            for (Osobnik o: gang) {
                double tempProfitability = 0;
                if (listTheBestOsobnik.size() < numberTheBestOsobnik) {
                    listTheBestOsobnik.add(o);
                } else {
                    Osobnik worstOsobnikFromTheBest = getLessProfitable((ArrayList<Osobnik>) listTheBestOsobnik);
                        if (o.getProfitability() > worstOsobnikFromTheBest.getProfitability() ) {
                            listTheBestOsobnik.set(listTheBestOsobnik.indexOf(worstOsobnikFromTheBest), o);
                        }
                }
            }
        }
    }

    private Osobnik getLessProfitable(ArrayList<Osobnik> temp) {
        Osobnik tempOs  = temp.get(0);
        for (Osobnik o: temp) {
            if (tempOs.getProfitability() > o.getProfitability()) tempOs = o;
        }

        return tempOs;
    }


    public List<Osobnik> evaluate() {

        List<Osobnik> tempGang = new ArrayList<>();

        for (int i = 0; i < gang.size(); i++) {
            if (Math.random() < crossover) {
                //selection
                Osobnik parent1 = selection();
                Osobnik parent2 = selection();
                //crossover
                Osobnik child = crossover(parent1,parent2);
                tempGang.add(child);
            } else {
                tempGang.add(gang.get(i));
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

    private void findTheBest() {
        if (gang != null && !gang.isEmpty()) {
            Osobnik theBestOsobnik = getTheBestOsobnik();
            double theBestProfitability = 0;

            if (theBestOsobnik != null) {
                theBestProfitability = theBestOsobnik.getProfitability();
            }

                for (Osobnik o : gang) {
                    if (o.getProfitability() > theBestProfitability) {
                        theBestProfitability = o.getProfitability();
                        theBestOsobnik = o;
                    }

                setTheBestOsobnik(theBestOsobnik);
            }
        }
    }

    //The best osobnik in the Gang
    public Osobnik selection() {
        List<Osobnik> tempGang = new ArrayList<>();
        Osobnik temp = null;
        if (gang != null && !gang.isEmpty()) {
            for (int i = 0; i < tournamentRate; i++) {
                int randNumb = (int) (Math.random() * gang.size());
                temp = gang.get(randNumb);
                tempGang.add(temp);
            }

            for (Osobnik o: tempGang) {
                if (o.getProfitability() > temp.getProfitability()) temp = o;
            }
        }


        return temp;
    }

    public int getSizeOfGang() {
        return gang.size();
    }


    public Osobnik getTheBestOsobnik() {
        return theBestOsobnik;
    }

    public void setTheBestOsobnik(Osobnik theBestOsobnik) {
        this.theBestOsobnik = theBestOsobnik;
    }

    public void setGang(List<Osobnik> gang) {
        gang = gang;
    }

    public List<Osobnik> getGang() {
        return gang;
    }

    public List<Osobnik> getListTheBestOsobnik() {
        return listTheBestOsobnik;
    }

    public void setListTheBestOsobnik(List<Osobnik> listTheBestOsobnik) {
        this.listTheBestOsobnik = listTheBestOsobnik;
    }
}
