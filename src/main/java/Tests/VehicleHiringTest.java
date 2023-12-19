package Tests;

import LocationHandling.Location;

import java.util.List;

public interface VehicleHiringTest {
    // Inserts the vehicle with registration number reg to the map at location loc if it has not been already added to map.
// It should return false if the vehicle is not registered or is already on map
    public abstract void testAddToMap() throws InterruptedException;
    // Update the location of the vehicle with the specified reg number to location loc if vehicle exists and return true.
//Return false if vehicle not registered or has not been added to the map
    public abstract void testMoveVehicle();
    // Remove the vehicle with the specified reg number from the map if it is registered and return true.
// If vehicle is not registered or is not on map the method returns false.
    // Return a list of all vehicles registration numbers located within a square of side 2*r centered at location loc (inclusive
//of the boundaries).
    public abstract void testGetVehiclesInRange();
}
