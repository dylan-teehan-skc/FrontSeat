package Tests;

import MapAndTaxis.TaxiMap;
import MapAndTaxis.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaxiMapTest {

    @Test
    public void testInitializeMap() {
        TaxiMap.initialiseMap();
        // Check if the player is placed on the map
        assertEquals("P", TaxiMap.map[User.getPlayerX()][User.getPlayerY()]);
    }

    @Test
    public void testMoveVehicle() throws InterruptedException {
        //This test assumes that moveTaxis updates the positions of taxis correctly
        TaxiMap.initialiseMap();
        int[] initialTaxiX = TaxiMap.taxiX.clone();
        int[] initialTaxiY = TaxiMap.taxiY.clone();

        TaxiMap.moveTaxis();

        // Check if the positions of taxis have changed
        assertNotEquals(initialTaxiX, TaxiMap.taxiX);
        assertNotEquals(initialTaxiY, TaxiMap.taxiY);
    }

    @Test
    public void testCheckIfTaxiArrived() {
        // Test when a taxi is at the player's location
        TaxiMap.initialiseMap();
        int taxiIndex = 0; // Assuming there is at least one taxi

        TaxiMap.taxiX[taxiIndex] = User.getPlayerX();
        TaxiMap.taxiY[taxiIndex] = User.getPlayerY();

        assertTrue(TaxiMap.checkIfTaxiArrived(taxiIndex));
    }

    @Test
    public void testFindClosestTaxi() {
        // Test the findClosestTaxi method

        TaxiMap.initialiseMap();
        int closestTaxiIndex = TaxiMap.findClosestTaxi("Accessible"); // Assuming there is at least one regular taxi

        // Check if the index returned is valid
        assertTrue(closestTaxiIndex >= 0 && closestTaxiIndex < TaxiMap.numTaxis);
    }

    @Test
    public void testIsValidCoordinate() {
        // Test the isValidCoordinate method
        // Test with valid coordinates
        assertTrue(TaxiMap.isValidCoordinate(2, 3));

        // Test with invalid coordinates
        assertFalse(TaxiMap.isValidCoordinate(-1, 3));
        assertFalse(TaxiMap.isValidCoordinate(2, 8));
    }
}
