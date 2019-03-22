import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Reader {

    public static final int NR_LINE_CITIES_START = 10;
    public static final int NR_LINE_ITEMS_START = 63;
    public static final int NR_LINE_CITIES = 2;
    public static final int NR_LINE_ITEMS = 3;
    public static final int NR_LINE_KNAPSACK_SIZE = 4;
    public static final int NR_LINE_MIN_SPEED = 5;
    public static final int NR_LINE_MAX_SPEED = 6;

    private String file_path;
    private int numberOfCities;
    private int numberOfItems;
    private int capacityOfKnapsack;
    private double maxSpeed;
    private double minSpeed;
    private int rentingRation;

    private ArrayList<String> fileList;
    private ArrayList<City> citiesList;
    private ArrayList<Item> itemsList;

    private final static Logger logger = Logger.getLogger("Reader.class");

    public Reader(String path) {
        this.file_path = path;
        this.citiesList = new ArrayList<>();
        this.fileList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
    }

    public void load() {
        readFile();
        retrieveMainInformation();
        retrieveCities();
        retrieveItems();
    }


    private void readFile() {
        try {
            File file = new File(file_path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                fileList.add(temp);
               // System.out.println(temp);
            }
            sc.close();
            //logger.info("All date were successfully retrieved! And stored on FileList");
        } catch (Exception e) {
            logger.warning("Some mistake during retrieving data from the file: " + e);
            e.printStackTrace();
        }
    }

    private void retrieveMainInformation() {
        if (!fileList.isEmpty()) {
            //retrieving number of cities from file
            String[] tempString = fileList.get(NR_LINE_CITIES).trim().split("\\s+");
            numberOfCities = Integer.parseInt(tempString[1]);

            //retrieving number of items from file
            tempString = fileList.get(NR_LINE_ITEMS).trim().split("\\s+");
            numberOfItems = Integer.parseInt(tempString[3]);

            //retrieving min speed
            tempString = fileList.get(NR_LINE_MIN_SPEED).trim().split("\\s+");
            minSpeed = Double.parseDouble(tempString[2]);

            //retrieving max speed
            tempString = fileList.get(NR_LINE_MAX_SPEED).trim().split("\\s+");
            maxSpeed = Double.parseDouble(tempString[2]);

            //retrieving capacity of knapsack
            tempString = fileList.get(NR_LINE_KNAPSACK_SIZE).trim().split("\\s+");
            capacityOfKnapsack = Integer.parseInt(tempString[3]);

            //logger.info("retrieveMainInformation: main information successfully retrieved ");
        } else {
            logger.warning("retrieveMainInformation: empty fileList");
        }
    }

    private void retrieveCities() {
        if (!fileList.isEmpty() && numberOfCities > 0) {
            City tempCity = null;
            for (int i = NR_LINE_CITIES_START; i <= numberOfCities + NR_LINE_CITIES_START; i++) {
                String[] strCity = fileList.get(i).trim().split("\\s+");
                if (strCity.length == 3) {
                    tempCity = new City(Integer.parseInt(strCity[0])-1, Double.parseDouble(strCity[1]), Double.parseDouble(strCity[2]));
                    citiesList.add(tempCity);
                }
            }
            //logger.info("retrieveCities: Cities were successfully retrieved!");
        } else {
            logger.warning("retrieveCities: Cites didnt retrieve!");
        }
    }

    private List<Item> retrieveItems() {
        if (!fileList.isEmpty() && numberOfItems > 0) {
            Item tempItem = null;
            for (int i = NR_LINE_ITEMS_START; i < numberOfItems + NR_LINE_ITEMS_START; i++) {
                String[] strItem = fileList.get(i).trim().split("\\s+");
                if (strItem.length == 4) {
                    tempItem = new Item(Integer.parseInt(strItem[1]), Integer.parseInt(strItem[2]), Integer.parseInt(strItem[3]));

                    //Sign item to city
                    signItemToCity(tempItem);
                }
            }
            //logger.info("retrieveItems: Items were successfully retrieved!");
            return itemsList;
        } else {
            return null;
        }
    }

    private void signItemToCity(Item item) {
        citiesList.get(item.getNode()-1).getItemsList().add(item);
        itemsList.add(item);
    }


    public ArrayList<String> getFile() {
        return fileList;
    }

    public String getFile_path() {
        return file_path;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getCapacityOfKnapsack() {
        return capacityOfKnapsack;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public int getRentingRation() {
        return rentingRation;
    }

    /*public ArrayList<String> getFileList() {
        readFile();
        retrieveCities();
        return fileList;
    }*/

    public ArrayList<City> getCitiesList() {
        return citiesList;
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }
}