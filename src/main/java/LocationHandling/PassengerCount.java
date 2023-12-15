package LocationHandling;
import java.util.Scanner;


public class PassengerCount {


    private int passengers;
    public int getPassengers() {
        return passengers;
    }

    public void Passengers() {

        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("How many passengers will be travelling with you");
            passengers = scanner.nextInt();

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
