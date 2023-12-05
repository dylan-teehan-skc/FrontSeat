package MapAndTaxis;

import java.io.IOException;
import java.util.Random;

public class TaxiMap2 {

    private static int mapSize = 8;
    private static int numTaxis = 5;
    private static int playerX = 1;
    private static int playerY = 2;
    private static int[] taxiX = new int[numTaxis];
    private static int[] taxiY = new int[numTaxis];
    TaxiDriver jon = new TaxiDriver("jon", "212-D-423", 8, "Delux");
    private static String[] taxiNames = {"Taxi1", "Taxi2", "Taxi3", "Taxi4", "Taxi5"};
    private static String[][] map = new String[mapSize][mapSize];

    public static void main(String[] args) throws InterruptedException, IOException {
        initialiseMap();
        moveTaxis();
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
            map[taxiX[i]][taxiY[i]] = taxiNames[i].substring(0, 1); // Use the first character of the taxi name
        }
    }

    public static void moveTaxis() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
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
                map[taxiX[j]][taxiY[j]] = taxiNames[j].substring(0, 1); // Use the first character of the taxi name

                // Check for nearby taxis
                checkNearbyPlayer(j);
            }

            // Update the player's position
            map[playerX][playerY] = "P";

            printMap(map);

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }
    }

    public static void checkNearbyPlayer(int taxiIndex) {
        int taxiXCoord = taxiX[taxiIndex];
        int taxiYCoord = taxiY[taxiIndex];

        int playerXCoord = playerX;
        int playerYCoord = playerY;

        // Check if the player is in the 2-block square around the taxi
        boolean playerNearby = Math.abs(taxiXCoord - playerXCoord) <= 2 && Math.abs(taxiYCoord - playerYCoord) <= 2;

        // Print the result
        if (playerNearby) {
            System.out.println(taxiNames[taxiIndex] + " is close to the player!");
        } else {
           // System.out.println( taxiNames[taxiIndex] + " is not close to player");
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
            System.out.println(taxiNames[k] + " is at coordinates [" + taxiX[k] + "," + taxiY[k] + "]");
        }

        System.out.println();
    }

    // Clear the console (print empty lines)
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println(); // Print empty lines
        }
    }
}
