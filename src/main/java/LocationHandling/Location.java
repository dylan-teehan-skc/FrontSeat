package LocationHandling;

import MapAndTaxis.TaxiMap;

import java.io.IOException;
import java.util.Scanner;

import static MapAndTaxis.User.setPlayerX;
import static MapAndTaxis.User.setPlayerY;

public class Location  {

    public void RunLocation() {

        System.out.println("A taxi is coming to you now at your location");
        // Sleep for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            TaxiMap.RunMap();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
