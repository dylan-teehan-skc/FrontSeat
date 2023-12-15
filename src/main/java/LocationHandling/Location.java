package LocationHandling;

import MapAndTaxis.TaxiMap;

import java.io.IOException;
import java.util.Scanner;

import static MapAndTaxis.User.setPlayerX;
import static MapAndTaxis.User.setPlayerY;

public class Location extends Map {

    public void RunLocation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Where are you?");
        String location = scanner.nextLine();
        boolean found = false;

        String[][] map = getMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (location.equalsIgnoreCase(map[i][j]))
                    found = true;
            }
        }
        if (found) {
            try {
                System.out.println("A taxi is coming to you now at " + location);
                // Sleep for 2 seconds
                Thread.sleep(2000);
                TaxiMap.RunMap();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Your destination doesn't exist");
        }
    }
}


