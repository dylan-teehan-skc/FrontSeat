package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserTypeScene extends Application {

    @FXML
    private Button driverButton;
    @FXML
    private Button riderButton;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DriverLogin.class.getResource("UserTypeScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 639, 639);
        stage.setScene(scene);
        stage.setTitle("UserChoice");
        stage.show();
        stage.setY(60);
        stage.setX(447);
    }

    public void openDriverLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("driverLogin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 674, 438));
            stage.show();
            stage.setY(190);
            stage.setX(470);

            // Close the current login window if needed
            Stage currentStage = (Stage) driverButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRiderLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("riderLogin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 484, 487));
            stage.show();
            stage.setY(140);
            stage.setX(505);

            // Close the current login window if needed
            Stage currentStage = (Stage) riderButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}


