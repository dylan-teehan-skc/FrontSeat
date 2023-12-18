package Tests;

import MapAndTaxis.AddTaxiDriver;
import MapAndTaxis.TaxiMap;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static MapAndTaxis.User.getPlayerX;
import static MapAndTaxis.User.getPlayerY;
import static org.junit.Assert.*;

public class TaxiTests implements VehicleHiringTest {
    @Override
    public void testAddToMap() throws InterruptedException {
        // Mocking PrintWriter and FileWriter for testing
        PrintWriter mockPrintWriter = new PrintWriter(System.out);
        FileWriter mockFileWriter = null;
        try {
            mockFileWriter = new FileWriter("mockFile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileWriter finalMockFileWriter = mockFileWriter;
        AddTaxiDriver addTaxiDriver = new AddTaxiDriver() {
            @Override
            protected PrintWriter createPrintWriter(FileWriter fileWriter) throws IOException {
                return mockPrintWriter;
            }

            @Override
            protected FileWriter createFileWriter(String csvFilePath, boolean append) throws IOException {
                return finalMockFileWriter;
            }
        };
    }

    @Override
    public void testMoveVehicle() {
        // This test assumes that moveTaxis updates the positions of taxis correctly
        TaxiMap.initialiseMap();

        // Clone the initial positions of taxis
        int[] initialTaxiX = TaxiMap.taxiX.clone();
        int[] initialTaxiY = TaxiMap.taxiY.clone();

        try {
            // Run the moveTaxis method
            TaxiMap.moveTaxis();
        } catch (InterruptedException e) {
            // Handle or log the InterruptedException if needed
            e.printStackTrace();
        }

        // Check if the positions of taxis have changed
        for (int i = 0; i < TaxiMap.numTaxis; i++) {
            assertNotEquals(initialTaxiX[i], TaxiMap.taxiX[i]);
            assertNotEquals(initialTaxiY[i], TaxiMap.taxiY[i]);
        }
    }

    @Override
    public void testGetVehiclesInRange() {
        // Arrange
        TaxiMap.numTaxis = 2;

        // Set player position
        TaxiMap.map[getPlayerX()][getPlayerY()] = "P";

        // Set taxi positions
        TaxiMap.taxiX = new int[]{1, 3};
        TaxiMap.taxiY = new int[]{1, 3};
        TaxiMap.map[TaxiMap.taxiX[0]][TaxiMap.taxiY[0]] = "T";
        TaxiMap.map[TaxiMap.taxiX[1]][TaxiMap.taxiY[1]] = "T";

        // Act and Assert
        assertEquals(0, TaxiMap.findClosestTaxi("Standard"));
        assertEquals(1, TaxiMap.findClosestTaxi("Luxury"));
    }
}
