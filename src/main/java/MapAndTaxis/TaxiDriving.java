package MapAndTaxis;

import LocationHandling.PassengerCount;

import static MapAndTaxis.Car.taxiJourney;
import static MapAndTaxis.Taxi.getTaxitype;
import static MapAndTaxis.TaxiMap.*;
import static MapAndTaxis.User.getPlayerX;
import static MapAndTaxis.User.getPlayerY;
import static LocationHandling.Review.GiveReview;

public class TaxiDriving {
    // Instance variable to store the distance of the taxi journey
    private double distance;

    // Setter for distance
    public void setDistance(double distance) {
        this.distance = distance;
    }

    //Getter for distance
    public double getDistance() {
        return distance;
    }

    // Method to simulate a taxi going to the player's location
    public static void taxiGoToPlayer(String taxiType) throws InterruptedException {
        moveTaxis();
        boolean taxiArrived = false;

        for (int i = 0; i < 10 && !taxiArrived; i++) {
            // Clear the console (print empty lines)
            clearConsole();

            int closestTaxiIndex = findClosestTaxi(taxiType);

            if (closestTaxiIndex != -1) {
                // Move the closest taxi towards the player
                int playerXCoord = getPlayerX();
                int playerYCoord = getPlayerY();
                int taxiXCoord = taxiX[closestTaxiIndex];
                int taxiYCoord = taxiY[closestTaxiIndex];

                int newX, newY;

                // Move the taxi one step closer to the player's location
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

                // Update the taxi position on the map
                map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
                taxiX[closestTaxiIndex] = newX;
                taxiY[closestTaxiIndex] = newY;
                map[newX][newY] = "T";

                // Check if the taxi has arrived at the player's location
                taxiArrived = checkIfTaxiArrived(closestTaxiIndex);

                // Print the map
                printMap(map);

                //Sleep for 2 seconds to simulate taxi movement
                Thread.sleep(2000);
            } else {
                System.out.println("No available taxis of type " + taxiType + " in the vicinity. Please wait until one is available.");
                break; // Exit the loop if no taxi of the chosen type is available
            }
        }
    }

    // Method to simulate a taxi going to specified destination
    public static void taxiGoToDestination(int destinationX, int destinationY, String taxiType) throws InterruptedException {
        taxiJourney();
        double distance = Math.sqrt(Math.pow(getPlayerX() - destinationX, 2) + Math.pow(getPlayerY() - destinationY, 2));

        // Use the setDistance method to set the distance for Other class and get
        TaxiDriving distanceObject = new TaxiDriving();
        distanceObject.setDistance(distance);

        // Find the closest taxi to the player's location
        int closestTaxiIndex = findClosestTaxi(taxiType);
        boolean taxiArrived = false;
        boolean repeat = true;

        if (closestTaxiIndex == -1) {
            return; // Exit the method if no taxi of the specified type is available
        }

        boolean journeyStarted = false; // track whether the taxi journey has started

        // Loop until the taxi reaches the destination
        while (repeat) {
            clearConsole();

            int taxiXCoord = taxiX[closestTaxiIndex];
            int taxiYCoord = taxiY[closestTaxiIndex];

            // Determine the new X-coordinate for the taxi based on its current position and the destination
            int newX;
            if (taxiXCoord < destinationX) {
                // If the current X-coordinate is less than the destination X-coordinate, move one step to the right
                newX = taxiXCoord + 1;
            } else if (taxiXCoord > destinationX) {
                // If the current X-coordinate is greater than the destination X-coordinate, move one step to the left
                newX = taxiXCoord - 1;
            } else {
                // If the current X-coordinate is equal to the destination X-coordinate, stay in the same column
                newX = taxiXCoord;
            }

// Determine the new Y-coordinate for the taxi based on its current position and the destination
            int newY;
            if (taxiYCoord < destinationY) {
                // If the current Y-coordinate is less than the destination Y-coordinate, move one step down
                newY = taxiYCoord + 1;
            } else if (taxiYCoord > destinationY) {
                // If the current Y-coordinate is greater than the destination Y-coordinate, move one step up
                newY = taxiYCoord - 1;
            } else {
                // If the current Y-coordinate is equal to the destination Y-coordinate, stay in the same row
                newY = taxiYCoord;
            }


            // Check boundaries to prevent going out of the map
            newX = Math.max(0, Math.min(newX, mapSize - 1));
            newY = Math.max(0, Math.min(newY, mapSize - 1));

            // Update the taxi position on the map
            map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
            taxiX[closestTaxiIndex] = newX;
            taxiY[closestTaxiIndex] = newY;

            //red text
            String redColorCode = "\u001B[31m";
            // back to default
            String resetColorCode = "\u001B[0m";

            map[newX][newY] = redColorCode + "T" + resetColorCode;

            // Check if the taxi has arrived at the destination
            if (taxiXCoord == destinationX && taxiYCoord == destinationY) {
                taxiArrived = true;
                System.out.println("You have reached your destination");
                // start the payment class
                LocationHandling.Payment startPayment = new LocationHandling.Payment();
                startPayment.proccessPayment(distanceObject);

                repeat = false; // Exit the loop when the destination is reached
            } else {
                if (!journeyStarted) {
                    System.out.println("Your taxi journey has begun");
                    journeyStarted = true; //flag to true after printing the message
                }
                printMap(map); // Print the map only if the taxi hasn't arrived yet
            }

            //Sleep for 2 seconds
            Thread.sleep(2000);
        }

        // Update the map one last time after reaching the destination
        map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "T";
    }
}
