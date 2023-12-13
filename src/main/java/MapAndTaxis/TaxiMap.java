package MapAndTaxis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import static MapAndTaxis.TaxiDriving.taxiGoToDestination;
import static MapAndTaxis.TaxiDriving.taxiGoToPlayer;
import static MapAndTaxis.User.*;

public class TaxiMap {
    public static int mapSize = 7;
    private static int numTaxis;
    public static int[] taxiX = new int[numTaxis];
    public static int[] taxiY = new int[numTaxis];
    private static TaxiDriver[] taxiDrivers = new TaxiDriver[numTaxis];
    private static String[] taxiNames = new String[numTaxis];
    private static String[] taxiTypes = new String[numTaxis];


    static {
        try {
            String csvFilePath = "C:/Users/dylan/MyRepos/FrontSeat/TaxiDrivers.csv";

            // Use FileReader and BufferedReader to read the CSV file
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                // Skip the header line if it exists
                reader.readLine();

                // Count the number of lines in the CSV file
                numTaxis = (int) reader.lines().count();

                // Initialize arrays with dynamic size
                taxiX = new int[(int) numTaxis];
                taxiY = new int[(int) numTaxis];
                taxiDrivers = new TaxiDriver[(int) numTaxis];
                taxiNames = new String[(int) numTaxis];
                taxiTypes = new String[(int) numTaxis];

                // Rewind the reader to read data lines again
                reader.close();

                // Create a new BufferedReader for reading data lines
                BufferedReader dataReader = new BufferedReader(new FileReader(csvFilePath));
                dataReader.readLine(); // Skip the header line

                int index = 0;

                // Read data lines and populate arrays
                String line;
                while ((line = dataReader.readLine()) != null && index < numTaxis) {
                    String[] nextRecord = line.split(",");

                    String name = nextRecord[0].trim();
                    String licensePlate = nextRecord[1].trim();
                    int rating = Integer.parseInt(nextRecord[2].trim());
                    String carType = nextRecord[3].trim();

                    taxiDrivers[index] = new TaxiDriver(name, licensePlate, rating, carType);
                    taxiNames[index] = name;
                    taxiTypes[index] = carType;

                    index++;
                }

                // Close the dataReader
                dataReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Move this loop inside the static block
        for (int i = 0; i < numTaxis; i++) {
            taxiNames[i] = taxiDrivers[i].getName();
            taxiTypes[i] = taxiDrivers[i].getCarType();
        }
    }


    public static String[][] map = new String[mapSize][mapSize];


    public static void RunMap() throws InterruptedException, IOException {
        initialiseMap();
        moveTaxis();
        taxiGoToPlayer();
        taxiGoToDestination(6,6);
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
                checkIfTaxiArrived(j);
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
        for (int i = 0; i < 50; i++) {
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
        int closestTaxiIndex = findClosestTaxi();
        // Print the result
        if (taxiHere) {
            System.out.println(taxiNames[taxiIndex] + " in a " + taxiTypes[taxiIndex] + " taxi has arrived");
            return true;  // Taxi has arrived, set the flag to true

        } else {
            //System.out.println(taxiNames[taxiIndex] + " is not at the player's location yet.");
            return false;  // Taxi hasn't arrived
        }
    }

    public static int findClosestTaxi() {
        int closestTaxiIndex = 0;
        double minDistance = Double.MAX_VALUE;

        for (int j = 0; j < numTaxis; j++) {
            double distance = Math.sqrt(Math.pow(getPlayerX()- taxiX[j], 2) + Math.pow(getPlayerY() - taxiY[j], 2));

            if (distance < minDistance) {
                minDistance = distance;
                closestTaxiIndex = j;
            }
        }

        return closestTaxiIndex;
    }
}
