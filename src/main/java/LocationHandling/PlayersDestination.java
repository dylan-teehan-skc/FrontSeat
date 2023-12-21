package LocationHandling;

public class PlayersDestination {
    private static int DestinationX;//store x and y coordinates
    private static int DestinationY;

    public void Destination(int DestinationX, int DestinationY) { //method to set the destination coordinates
        this.DestinationX = DestinationX;
        this.DestinationY = DestinationY;
    }

    public static int getDestinationX() {
        return DestinationX;
    }

    public static void setDestinationX(int destinationX) {
        DestinationX = destinationX;
    }

    public static int getDestinationY() {
        return DestinationY;
    }

    public static void setDestinationY(int destinationY) {
        DestinationY = destinationY;
    }

    public void destination() { //method to indicate to the user that its finding a taxi
        System.out.println("Finding you a taxi for your destination ");
        Location startLocation = new Location();
        startLocation.RunLocation(); //running location
    }
}