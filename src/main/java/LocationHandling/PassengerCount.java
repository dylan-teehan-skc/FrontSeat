package LocationHandling;
import java.util.Scanner;


public class PassengerCount {
    private static int passengers;
    public void passengerCount(int passengers){
        this.passengers = passengers;
    }

    public static int getPassengers() {
        return passengers;
    }

    public static void setPassengers(int passengers) {
        PassengerCount.passengers = passengers;
    }

    public void Passengers() {

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("How many passengers will be travelling with you");
            passengers = getPassengers();

            if (passengers< 0 || passengers > 7) {
                System.out.println("The maximum capacity is 7");

            } else {
                System.out.println("You selected " + passengers + "number of passengers");
                Location startLocation = new Location();
                startLocation.RunLocation();
            }
        } while (passengers < 0 || passengers > 7);



    }

}
