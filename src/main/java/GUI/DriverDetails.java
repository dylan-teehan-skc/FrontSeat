package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static MapAndTaxis.TaxiDriver.getCarType;

public class DriverDetails {
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField; // registration number
    @FXML
    private TextField ratingField;
    @FXML
    private Button detailsEnterButton;

    @FXML
    private void onDetailsEnterButtonClicked() {
        String name = nameField.getText();
        String number = numberField.getText();
        String ratingText = ratingField.getText();

        if (name.isEmpty() || number.isEmpty() || ratingText.isEmpty()) {
            showErrorMessage("All fields must be filled out.");
            return;
        }

        try {
            double rating = Double.parseDouble(ratingText);

            if (rating < 1 || rating > 10) {
                showErrorMessage("Rating must be between 1 and 10.");
                return;
            }

            // All validations passed, show success message
            showSuccessMessage("Driver registered successfully!");
            addTaxiDriver(name, number, ratingText, getCarType());

            // Close the current scene
            closeCurrentScene();
        } catch (NumberFormatException e) {
            // Handle the case where the rating is not a valid double
            showErrorMessage("Invalid rating format.");
        }
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeCurrentScene() {
        Stage stage = (Stage) detailsEnterButton.getScene().getWindow();
        stage.close();
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
}
