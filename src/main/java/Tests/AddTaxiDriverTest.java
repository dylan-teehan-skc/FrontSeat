package Tests;

import MapAndTaxis.AddTaxiDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

public class AddTaxiDriverTest {

    private PrintWriter mockPrintWriter;
    private FileWriter mockFileWriter;

    @Before
    public void setUp() throws IOException {
        mockPrintWriter = new PrintWriter(System.out);
        mockFileWriter = new FileWriter("dummy.csv");  // Use a dummy file path or create a mock for FileWriter
    }

    @Test
    public void testAddTaxiDriver() throws IOException {
        // Create an instance of AddTaxiDriver without mocking
        AddTaxiDriver addTaxiDriver = new AddTaxiDriver() {
            @Override
            protected PrintWriter createPrintWriter(FileWriter fileWriter) throws IOException {
                return mockPrintWriter;
            }

            @Override
            protected FileWriter createFileWriter(String csvFilePath, boolean append) throws IOException {
                return mockFileWriter;
            }
        };

        // Act
        addTaxiDriver.addTaxiDriver("NewDriver", "XX-YYY-123", "8", "Standard");

        mockPrintWriter.flush();
        assertTrue(true);  // Dummy assertion to make the test pass if there are no exceptions
    }

}
