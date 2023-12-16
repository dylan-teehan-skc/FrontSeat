package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static MapAndTaxis.Taxi.setTaxitype;

public class RiderTaxiChoice {
    @FXML
    private Button regularButton;
    @FXML
    private Button accesableButton;
    @FXML
    private Button deluxeButton;

    private String taxitype; // Variable to store the selected taxi type

    public void openCurrentLocation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Location.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 407, 565));
            stage.show();
            stage.setY(100);
            stage.setX(550);

            // Close the current login window if needed
            Stage currentStage = (Stage) deluxeButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setStandardTaxiType() {
        setTaxitype("Standard");
        System.out.println("Taxi type set to Standard");
        openCurrentLocation();
    }

    @FXML
    private void setAccessibleTaxiType() {
        setTaxitype("Accessible");
        System.out.println("Taxi type set to Accessible");
        openCurrentLocation();
    }

    @FXML
    private void setDeluxeTaxiType() {
        setTaxitype("Deluxe");
        System.out.println("Taxi type set to Deluxe");
        openCurrentLocation();
    }
}
