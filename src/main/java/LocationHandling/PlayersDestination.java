package LocationHandling;

public class PlayersDestination extends Map {
    private static int DestinationX;
    private static int DestinationY;

    public void Destination(int DestinationX, int DestinationY) {
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

    public void destination() {
        System.out.println("Finding you a taxi for your destination ");
        Location startLocation = new Location();
        startLocation.RunLocation();
    }
}