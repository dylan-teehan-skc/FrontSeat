package MapAndTaxis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static MapAndTaxis.Taxi.getTaxitype;
import static MapAndTaxis.TaxiDriving.*;
import static MapAndTaxis.User.*;
import static LocationHandling.PlayersDestination.getDestinationX;
import static LocationHandling.PlayersDestination.getDestinationY;

public class TaxiMap {
    public static int mapSize = 7;
    private static int numTaxis;
    public static int[] taxiX;
    public static int[] taxiY;
    private static TaxiDriver[] taxiDrivers;
    private static TaxiDriver2[] taxiDrivers2;
    private static String[] taxiNames;
    private static String[] taxiTypes;
    private static String[] licensePlates;

    static {
        try {
            String csvFilePath = "TaxiDrivers.csv";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                reader.readLine();

                numTaxis = (int) reader.lines().count();

                taxiX = new int[numTaxis];
                taxiY = new int[numTaxis];
                taxiDrivers2 = new TaxiDriver2[numTaxis];
                taxiNames = new String[numTaxis];
                taxiTypes = new String[numTaxis];
                licensePlates = new String[numTaxis];

                // Reset the reader to the beginning of the file
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

                    taxiDrivers2[index] = new TaxiDriver2(name, licensePlate, rating, carType);
                    taxiNames[index] = name;
                    taxiTypes[index] = carType;
                    licensePlates[index] = licensePlate;

                    index++;
                }

                dataReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numTaxis; i++) {
            taxiNames[i] = taxiDrivers2[i].getName();
            taxiTypes[i] = taxiDrivers2[i].getCarType2();
        }
    }

    public static void printAllTaxiDrivers() {
        for (int i = 0; i < numTaxis; i++) {
            System.out.println("Taxi Driver " + (i + 1) + " Details:");
            System.out.println("Name: " + taxiDrivers2[i].getName());
            System.out.println("License Plate: " + taxiDrivers2[i].getReg2());
            System.out.println("Rating: " + taxiDrivers2[i].getRating2());
            System.out.println("Car Type: " + taxiDrivers2[i].getCarType2());
            System.out.println();
        }
    }


    public static String[][] map = new String[mapSize][mapSize];

    public static void RunMap() throws InterruptedException, IOException {
        initialiseMap();
        moveTaxis();
        taxiGoToPlayer(getTaxitype());
        taxiGoToDestination(getDestinationX(), getDestinationY(), getTaxitype());
    }

    public static void initialiseMap() {
        // Initialize the map with empty strings
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "-";

            }
        }

        // Place the player on the map
        map[getPlayerX()][getPlayerY()] = "P";

        // Initialize taxi positions
        Random random = new Random();
        for (int i = 0; i < numTaxis; i++) {
            int x, y;
            do {
                x = random.nextInt(mapSize);
                y = random.nextInt(mapSize);
            } while (map[x][y].equals("T") || map[x][y].equals("P") || (x == getPlayerX() && y == getPlayerY())); // Ensure a taxi doesn't overlap with player or other taxis

            taxiX[i] = x;
            taxiY[i] = y;
            map[taxiX[i]][taxiY[i]] = "T";
        }
    }

    public static void moveTaxis() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
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

                } while (newX == taxiX[j] && newY == taxiY[j]);  // Ensure the taxi moves to a new position

                taxiX[j] = newX;
                taxiY[j] = newY;

                // Update the new position
                map[taxiX[j]][taxiY[j]] = "T";

                // Check for nearby taxis
                //checkIfTaxiArrived(j);
            }

            // Update the player's position
            map[getPlayerX()][getPlayerY()] = "P";

            printMap(map);

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }
    }

    public static void printMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // Print taxi names and coordinates
        for (int k = 0; k < numTaxis; k++) {
            //System.out.println(taxiNames[k] + " is at coordinates [" + taxiX[k] + "," + taxiY[k] + "]");
        }

        System.out.println();
    }

    // Clear the console (print empty lines)
    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println(); // Print empty lines
        }
    }


    public static boolean checkIfTaxiArrived(int taxiIndex) {
        int taxiXCoord = taxiX[taxiIndex];
        int taxiYCoord = taxiY[taxiIndex];

        int playerXCoord = getPlayerX();
        int playerYCoord = getPlayerY();

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

    public static int findClosestTaxi(String requiredTaxiType) {
        int playerXCoord = getPlayerX();
        int playerYCoord = getPlayerY();

        // Set the radius for proximity check (5-block radius)
        int radius = 5;

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

        System.out.println("No available taxis of type " + requiredTaxiType + " in the vicinity. Please wait until one is available.");
        return -1;
    }




    // Add this method to check if the coordinates are within the valid map bounds
    private static boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < mapSize && y >= 0 && y < mapSize;
    }


    private static int findTaxiIndexAtCoordinates(int x, int y) {
        for (int i = 0; i < numTaxis; i++) {
            if (taxiX[i] == x && taxiY[i] == y) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws InterruptedException {
        printAllTaxiDrivers();
        initialiseMap();

        findClosestTaxi("Standard");
        System.out.println(findClosestTaxi("Standard"));
        System.out.println(findClosestTaxi("Deluxe"));
        System.out.println(findClosestTaxi("Accesible"));
    }

}
