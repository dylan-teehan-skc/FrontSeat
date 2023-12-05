package OisinPackage;

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

        if (found) {
            System.out.println("A taxi is coming to you now at " + location);
        } else {
            System.out.println("Your destination doesn't exist");
        }
        Review startReview = new Review();
        startReview.GiveReview();
    }


}