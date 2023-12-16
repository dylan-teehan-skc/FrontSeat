package MapAndTaxis;

import LocationHandling.PassengerCount;

import static MapAndTaxis.Car.taxiJourney;
import static MapAndTaxis.Taxi.getTaxitype;
import static MapAndTaxis.TaxiMap.*;
import static MapAndTaxis.User.getPlayerX;
import static MapAndTaxis.User.getPlayerY;
import static LocationHandling.Review.GiveReview;

public class TaxiDriving {
    private double distance;

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

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
            } else {
                System.out.println("No available taxis of type " + taxiType + " in the vicinity. Please wait until one is available.");
                break; // Exit the loop if no taxi of the specified type is available
            }
        }
    }

    public static void taxiGoToDestination(int destinationX, int destinationY, String taxiType) throws InterruptedException {
        taxiJourney();
        double distance = Math.sqrt(Math.pow(getPlayerX() - destinationX, 2) + Math.pow(getPlayerY() - destinationY, 2));

        // Use the setDistance method to set the distance
        TaxiDriving distanceObject = new TaxiDriving();
        distanceObject.setDistance(distance);

        int closestTaxiIndex = findClosestTaxi(taxiType);
        boolean taxiArrived = false;
        boolean repeat = true;

        if (closestTaxiIndex == -1) {
            System.out.println("No available taxis of type " + taxiType + " in the vicinity. Please wait until one is available.");
            return; // Exit the method if no taxi of the specified type is available
        }

        boolean journeyStarted = false; // Flag to track whether the taxi journey has started

        while (repeat) {
            clearConsole();

            int taxiXCoord = taxiX[closestTaxiIndex];
            int taxiYCoord = taxiY[closestTaxiIndex];

            // Move the closest taxi directly to the destination
            int newX = (taxiXCoord < destinationX) ? taxiXCoord + 1 : (taxiXCoord > destinationX) ? taxiXCoord - 1 : taxiXCoord;
            int newY = (taxiYCoord < destinationY) ? taxiYCoord + 1 : (taxiYCoord > destinationY) ? taxiYCoord - 1 : taxiYCoord;

            // Check boundaries to prevent going out of the map
            newX = Math.max(0, Math.min(newX, mapSize - 1));
            newY = Math.max(0, Math.min(newY, mapSize - 1));

            map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
            taxiX[closestTaxiIndex] = newX;
            taxiY[closestTaxiIndex] = newY;

            // ANSI escape code for red text
            String redColorCode = "\u001B[31m";
            // ANSI escape code to reset the text color to default
            String resetColorCode = "\u001B[0m";

            map[newX][newY] = redColorCode + "T" + resetColorCode;

            // Check if the taxi arrived at the destination
            if (taxiXCoord == destinationX && taxiYCoord == destinationY) {
                taxiArrived = true;
                System.out.println("You have reached your destination");
                LocationHandling.Payment startPayment = new LocationHandling.Payment();
                startPayment.proccessPayment(distanceObject);

                repeat = false; // Exit the loop when the destination is reached
            } else {
                if (!journeyStarted) {
                    System.out.println("Your taxi journey has begun");
                    journeyStarted = true; // Set the flag to true after printing the message
                }
                printMap(map); // Print the map only if the taxi hasn't arrived yet
            }

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }

        // Update the map one last time after reaching the destination
        map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "T";
    }
}
