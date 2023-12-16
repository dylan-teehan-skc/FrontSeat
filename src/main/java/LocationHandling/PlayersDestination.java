package LocationHandling;

import java.util.Scanner;


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



//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Where do you want to go?");
//        String destination = scanner.nextLine();
//        boolean found = false;
//
//        String[][] map = getMap();
//
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                if (destination.equalsIgnoreCase(map[i][j])) {
//                    found = true;
//                    break;
//                }
//            }
//            if (found) {
//                break;
//            }
//        }
//
//        if (found) {
//            System.out.println("Finding you a taxi for your destination " + destination);
//            Location startLocation = new Location();
//            startLocation.RunLocation();
//        } else {
//            System.out.println("Your destination doesn't exist");
//        }
//    }
//
//
//}