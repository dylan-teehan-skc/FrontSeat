package MapAndTaxis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AddTaxiDriver {

    public static void main(String[] args) {
        // Example usage:
        addTaxiDriver("NewDriver", "XX-YYY-123", "8", "Standard");
    }

    public static void addTaxiDriver(String name, String registrationNumber, String rating, String type) {
        // New driver information
        String[] newDriver = {name, registrationNumber, rating, type};

        // CSV file path
        String csvFilePath = "TaxiDrivers.csv";

        // Open the CSV file in append mode
        try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFilePath, true))) {
            // Write the new driver information to the CSV file
            csvWriter.println(String.join(",", newDriver));

            System.out.println("The new driver has been added to " + csvFilePath);
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected abstract PrintWriter createPrintWriter(FileWriter fileWriter) throws IOException;

    protected abstract FileWriter createFileWriter(String csvFilePath, boolean append) throws IOException;

}
