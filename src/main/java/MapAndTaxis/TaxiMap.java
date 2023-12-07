package MapAndTaxis;

import java.io.IOException;
import java.util.Random;

import static MapAndTaxis.Car.taxiJourney;

public class TaxiMap {
    private static int mapSize = 7;
    private static int numTaxis = 5;
    private static int playerX;
    private static int playerY;
    private static int[] taxiX = new int[numTaxis];
    private static int[] taxiY = new int[numTaxis];
    private static TaxiDriver[] taxiDrivers = new TaxiDriver[numTaxis];
    private static String[] taxiNames = new String[numTaxis];
    private static String[] taxiTypes = new String[numTaxis];

    static {
        taxiDrivers[0] = new TaxiDriver("Jon", "212-D-423", 8, "Delux");
        taxiDrivers[1] = new TaxiDriver("Alice", "123-A-456", 7, "Standard");
        taxiDrivers[2] = new TaxiDriver("Bob", "456-B-789", 9, "Accessable");
        taxiDrivers[3] = new TaxiDriver("Charlie", "789-C-012", 6, "Standard");
        taxiDrivers[4] = new TaxiDriver("David", "012-D-345", 8, "Standard");

        for (int i = 0; i < numTaxis; i++) {
            taxiNames[i] = taxiDrivers[i].getName();
            taxiTypes[i] = taxiDrivers[i].getCarType();
        }
    }
    private static String[][] map = new String[mapSize][mapSize];

    public void setPlayerLocation(int playerX, int playerY){
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public static void RunMap() throws InterruptedException, IOException {
        initialiseMap();
        moveTaxis();
        taxiGoToPlayer();
    }

    public static void initialiseMap() {
        // Initialize the map with empty strings
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "-";

            }
        }

        // Place the player on the map
        map[playerX][playerY] = "P";
//        map[1][1] = "A";
//        map[1][5] = "B";
//        map[3][1] = "C";
//        map[3][5] = "D";
//        map[5][1] = "E";
//        map[5][5] = "F";

        // Initialize taxi positions
        Random random = new Random();
        for (int i = 0; i < numTaxis; i++) {
            int x, y;
            do {
                x = random.nextInt(mapSize);
                y = random.nextInt(mapSize);
            } while (map[x][y].equals("T") || map[x][y].equals("P")); // Ensure a taxi doesn't overlap with player or other taxis

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
            map[playerX][playerY] = "P";

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

    public static void taxiGoToPlayer() throws InterruptedException {
        Random random = new Random();
        boolean taxiArrived = false;

        for (int i = 0; i < 10 && !taxiArrived; i++) {
            // Clear the console (print empty lines)
            clearConsole();

            int closestTaxiIndex = findClosestTaxi();

            // Move the closest taxi towards the player
            int playerXCoord = playerX;
            int playerYCoord = playerY;
            int taxiXCoord = taxiX[closestTaxiIndex];
            int taxiYCoord = taxiY[closestTaxiIndex];

            int newX, newY;

            if (taxiXCoord < playerXCoord) {
                newX = taxiXCoord + 1;
            } else if (taxiXCoord > playerXCoord) {
                newX = taxiXCoord - 1;
            } else {
                newX = taxiXCoord;
            }

            if (taxiYCoord < playerYCoord) {
                newY = taxiYCoord + 1;
            } else if (taxiYCoord > playerYCoord) {
                newY = taxiYCoord - 1;
            } else {
                newY = taxiYCoord;
            }

            // Check boundaries to prevent going out of the map
            newX = Math.max(0, Math.min(newX, mapSize - 1));
            newY = Math.max(0, Math.min(newY, mapSize - 1));

            // Update the taxi position
            map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
            taxiX[closestTaxiIndex] = newX;
            taxiY[closestTaxiIndex] = newY;
            map[newX][newY] = "T";

            // Check for nearby taxis
            taxiArrived = checkIfTaxiArrived(closestTaxiIndex);

            // Print the map
            printMap(map);

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }
    }

    public static boolean checkIfTaxiArrived(int taxiIndex) {
        int taxiXCoord = taxiX[taxiIndex];
        int taxiYCoord = taxiY[taxiIndex];

        int playerXCoord = playerX;
        int playerYCoord = playerY;

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
            double distance = Math.sqrt(Math.pow(playerX - taxiX[j], 2) + Math.pow(playerY - taxiY[j], 2));

            if (distance < minDistance) {
                minDistance = distance;
                closestTaxiIndex = j;
            }
        }

        return closestTaxiIndex;
    }
}
