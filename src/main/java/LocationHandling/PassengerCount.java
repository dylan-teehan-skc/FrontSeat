package LocationHandling;

import java.util.Scanner;

public class PassengerCount {
    private static int passengers;
    static int passengersnumber;

    public void passengerCount(int passengers) {
        this.passengers = passengers;
    }
// constructor to set the number of passengers when an instance is created
    public static int getPassengers() {
        return passengers;
    }

    public static void setPassengers(int passengers) {
        PassengerCount.passengersnumber = passengers;
    }

    public void Passengers() { //method to handle passenger input

        Scanner scanner = new Scanner(System.in);
        // previous functionality you had done is now implemented into gui
        System.out.println("You selected " + passengers + "number of passengers");
        Location startLocation = new Location();
        startLocation.RunLocation();


    }

}
