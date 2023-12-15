package GUI;

import MapAndTaxis.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static GUI.RegisterUser.hashPassword;

public class RiderLogin {

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label successLabel;
    private static File csvFile;

    // Method to attempt login
    @FXML
    public void TryLogin() {
        // Ensure the CSV file exists
        createCSV();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String user = usernameField.getText();
            String password = passwordField.getText();
            System.out.println(user);
            System.out.println(password);
            boolean loggedIn = false;

            // Iterate through each line in the CSV file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Check if the line has the correct format and matches the entered credentials
                if (parts.length == 2 && parts[0].equals(user) && verifyPassword(password, parts[1])) {
                    System.out.println("Login successful");
                    loggedIn = true;
                    //User.setName(user);
                    // Close the current login window and open the game mode selection scene
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();
                    openRiderTaxiChoice();
                }
            }

            // Display login failure message if not logged in
            if (!loggedIn) {
                System.out.println("Login Failed");
                successLabel.setText("Login Failed");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to verify the entered password against the stored password hash
    private boolean verifyPassword(String enteredPassword, String storedPasswordHash) {
        // Hash the entered password and compare it with the stored hash
        String hashedEnteredPassword = hashPassword(enteredPassword);
        return hashedEnteredPassword != null && hashedEnteredPassword.equals(storedPasswordHash);
    }

    // Method to open the registration scene
    public void openRegisterScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterScene.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 650, 450));
            stage.show();
            stage.setTitle("Login");
            stage.setY(120);
            stage.setX(400);

            // Close the current login window if needed
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to open the game mode selection scene
    // Method to create the CSV file if it doesn't exist
    public void createCSV() {
        csvFile = new File("user_data.csv");
        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
                System.out.println("New CSV file created: " + csvFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }


    public void openRiderTaxiChoice() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaxiChoice.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 674, 438));
            stage.show();
            stage.setY(190);
            stage.setX(470);

            // Close the current login window if needed
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
