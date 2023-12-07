package OisinPackage;

import MapAndTaxis.TaxiMap;

import java.io.IOException;
import java.util.Scanner;

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
        TaxiMap player = new TaxiMap();
        if (location.equals("Kilmurry")){
            player.setPlayerX(1);
            player.setPlayerY(3);
        }
        else if (location.equals("Plassey")){
            player.setPlayerX(3);
            player.setPlayerY(5);
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
        Review startReview = new Review();
        startReview.GiveReview();
    }


}