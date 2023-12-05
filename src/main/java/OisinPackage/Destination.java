package OisinPackage;

import java.util.Scanner;

public class Destination extends Map {

    public static void main(String[] args) {
        new Destination();
    }

    public Destination() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Where do you want to go?");
        String destination = scanner.nextLine();
        boolean found = false;

        String[][] map = getMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (destination.equalsIgnoreCase(map[i][j])) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        if (found) {
            System.out.println("Finding you a taxi for: " + destination);
            Location startLocation = new Location();
            startLocation.RunLocation();
        } else {
            System.out.println("Your destination doesn't exist");
        }
    }


}