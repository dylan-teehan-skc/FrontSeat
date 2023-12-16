package GUI;

import MapAndTaxis.AccesableTaxi;
import MapAndTaxis.DeluxeTaxi;
import MapAndTaxis.StandardTaxi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static MapAndTaxis.TaxiDriver.setCarType;

public class DriverLogin {
    @FXML
    private Button regularButton;

    @FXML
    private Button accesableButton;

    @FXML
    private Button deluxeButton;



    @FXML
    private void setStandardDriverType() {
        StandardTaxi runStandardTaxi = new StandardTaxi();
        runStandardTaxi.taxiInfo();
        runStandardTaxi.regNumber();

        setCarType("Standard");
        System.out.println("Taxi type set to Standard");
        openDriverDetails();

    }

    @FXML
    private void setAccessibleDriverType() {
        AccesableTaxi runAccesableTaxi = new AccesableTaxi();
        runAccesableTaxi.regNumber();
        runAccesableTaxi.taxiInfo();
        setCarType("Accessible");
        System.out.println("Taxi type set to Accessible");
        openDriverDetails();
    }

    @FXML
    private void setDeluxeDriverType() {
        DeluxeTaxi runDeluxeTaxi = new  DeluxeTaxi();
        runDeluxeTaxi.regNumber();
        runDeluxeTaxi.taxiInfo();

        setCarType("Deluxe");
        System.out.println("Taxi type set to Deluxe");
        openDriverDetails();
    }

    @FXML
    private void openDriverDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DriverDetails.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 375));
            stage.show();
            stage.setY(230);
            stage.setX(460);

            // Close the current login window if needed
            Stage currentStage = (Stage) deluxeButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
