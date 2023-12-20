package MapAndTaxis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
// Import your custom ArrayList class
import MapAndTaxis.MyArrayList;

import static MapAndTaxis.Taxi.getTaxitype;
import static MapAndTaxis.TaxiDriving.*;
import static LocationHandling.PlayersDestination.getDestinationX;
import static LocationHandling.PlayersDestination.getDestinationY;
import static MapAndTaxis.User.getPlayerX;
import static MapAndTaxis.User.getPlayerY;

public class TaxiMap {
    public static int mapSize = 7;
    public static int numTaxis;
    public static int[] taxiX;
    public static int[] taxiY;
    private static Driver[] taxiDrivers;
    private static String[] taxiNames;
    private static String[] taxiTypes;
    private static String[] licensePlates;

    static int PlayerX = getPlayerX();
    static int PlayerY = getPlayerY();

    // Use your custom ArrayList instead of the built-in ArrayList
    private static MyArrayList<Driver> myTaxiDrivers = new MyArrayList<>();

    static {
        try {
            String csvFilePath = "TaxiDrivers.csv";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                reader.readLine();

                numTaxis = (int) reader.lines().count();

                taxiX = new int[numTaxis];
                taxiY = new int[numTaxis];
                taxiDrivers = new Driver[numTaxis];
                taxiNames = new String[numTaxis];
                taxiTypes = new String[numTaxis];
                licensePlates = new String[numTaxis];

                reader.close();
                BufferedReader dataReader = new BufferedReader(new FileReader(csvFilePath));
                dataReader.readLine();

                int index = 0;

                String line;
                while ((line = dataReader.readLine()) != null && index < numTaxis) {
                    String[] nextRecord = line.split(",");

                    String name = nextRecord[0].trim();
                    String licensePlate = nextRecord[1].trim();
                    int rating = Integer.parseInt(nextRecord[2].trim());
                    String carType = nextRecord[3].trim();

                    taxiDrivers[index] = new Driver(name, licensePlate, rating, carType);
                    taxiNames[index] = name;
                    taxiTypes[index] = carType;
                    licensePlates[index] = licensePlate;

                    // Add the taxi driver to your custom ArrayList
                    myTaxiDrivers.add(new Driver(name, licensePlate, rating, carType));

                    index++;
                }

                dataReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Populate taxiNames and taxiTypes arrays based on your custom ArrayList
        for (int i = 0; i < numTaxis; i++) {
            taxiNames[i] = myTaxiDrivers.get(i).getName();
            taxiTypes[i] = myTaxiDrivers.get(i).getCarType2();
        }
    }

    //create the map
    public static String[][] map = new String[mapSize][mapSize];

    //Method to run the taxi simulation on the map
    public static void RunMap() throws InterruptedException, IOException {
        initialiseMap();
        moveTaxis();
        taxiGoToPlayer(getTaxitype()); // get which taxi the user chose and only that
        taxiGoToDestination(getDestinationX(), getDestinationY(), getTaxitype()); //after user is collected go to destination
    }

    //Method to initialize the map with player and taxi positions
    public static void initialiseMap() {
        // Initialize the map with empty strings
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "-";
            }
        }

        // Place the player on the map - chosen by player
        map[PlayerX][PlayerY] = "P";

        // Initialize taxi positions randomly
        Random random = new Random();
        for (int i = 0; i < numTaxis; i++) {
            int x, y;
            do {
                x = random.nextInt(mapSize);
                y = random.nextInt(mapSize);
            } while (map[x][y].equals("T") || map[x][y].equals("P") || (x == PlayerX && y == PlayerY)); // Ensure a taxi doesn't overlap with player or other taxis

            taxiX[i] = x;
            taxiY[i] = y;
            map[taxiX[i]][taxiY[i]] = "T";
        }
    }

    // Method to simulate taxi movement on the map
    public static void moveTaxis() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 1; i++) {
            // Clear the console (print empty lines)
            clearConsole();

            // Move each taxi
            for (int j = 0; j < numTaxis; j++) {
                // Clear the previous position
                map[taxiX[j]][taxiY[j]] = "-";

                int randomNum3;
                int newX, newY;

                do {
                    randomNum3 = random.nextInt(4);

                    newX = taxiX[j];
                    newY = taxiY[j];

                    switch (randomNum3) {
                        case 1:
                            newX += 1;
                            break;
                        case 2:
                            newX -= 1;
                            break;
                        case 3:
                            newY += 1;
                            break;
                        case 0:
                            newY -= 1;
                            break;
                    }

                    // Check boundaries to prevent going out of the map
                    newX = Math.max(0, Math.min(newX, mapSize - 1));
                    newY = Math.max(0, Math.min(newY, mapSize - 1));

                } while (newX == taxiX[j] && newY == taxiY[j]);  //Ensure the taxi moves to a new position

                taxiX[j] = newX;
                taxiY[j] = newY;

                // Update the new position
                map[taxiX[j]][taxiY[j]] = "T";
            }

            // Update the player's position
            map[PlayerX][PlayerY] = "P";

            printMap(map);

            //Sleep for 2 seconds
            Thread.sleep(2000);
        }
    }

    // Method to print the current state of the map
    public static void printMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to clear the console (print empty lines)
    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println(); // Print empty lines
        }
    }



    public static boolean checkIfTaxiArrived(int taxiIndex) {
        int taxiXCoord = taxiX[taxiIndex];
        int taxiYCoord = taxiY[taxiIndex];

        int playerXCoord = PlayerX;
        int playerYCoord = PlayerY;

        // Check if the player is in the same block as the taxi
        boolean taxiHere = (taxiXCoord == playerXCoord) && (taxiYCoord == playerYCoord);
        int closestTaxiIndex = findClosestTaxi(getTaxitype());
        // Print the result
        if (taxiHere) {
            System.out.println(taxiNames[taxiIndex] + " in a " + taxiTypes[taxiIndex] + " taxi has arrived - (" + licensePlates[taxiIndex] + ")");
            return true;  // Taxi has arrived, set the flag to true

        } else {
            //System.out.println(taxiNames[taxiIndex] + " is not at the player's location yet.");
            return false;  // Taxi hasn't arrived
        }
    }
    // Method to find the closest available taxi of the specified type within a given radius
    public static int findClosestTaxi(String requiredTaxiType) {
        int playerXCoord = PlayerX;
        int playerYCoord = PlayerY;

        // Set the radius for proximity check
        int radius = 9; //set to 9 to find one now but can easily be changed to smaller radius

        for (int r = 1; r <= radius; r++) {
            for (int x = playerXCoord - r; x <= playerXCoord + r; x++) {
                for (int y = playerYCoord - r; y <= playerYCoord + r; y++) {
                    // Check if the current coordinates are within the valid map bounds
                    if (isValidCoordinate(x, y) && map[x][y].equals("T")) {
                        int taxiIndex = findTaxiIndexAtCoordinates(x, y);
                        if (taxiIndex != -1) {
                            // Check if the found taxi matches the required type
                            if (taxiTypes[taxiIndex].equalsIgnoreCase(requiredTaxiType)) {
                                return taxiIndex;
                            }
                        }
                    }
                }
            }
        }

        // Print a message if no available taxis of the specified type are found in the vicinity
        System.out.println("No available taxis of type " + requiredTaxiType + " in the vicinity. Please wait until one is available.");
        return -1;
    }

    //Method to check if the coordinates are within the valid map bounds
    public static boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }

    // Method to find the index of the taxi at the specified coordinates
    private static int findTaxiIndexAtCoordinates(int x, int y) {
        for (int i = 0; i < numTaxis; i++) {
            if (taxiX[i] == x && taxiY[i] == y) {
                return i;
            }
        }
        return -1;
    }

}

