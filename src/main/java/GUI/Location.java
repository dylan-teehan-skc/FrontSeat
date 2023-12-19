package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static MapAndTaxis.User.setPlayerX;
import static MapAndTaxis.User.setPlayerY;

public class Location {
    @FXML
    private Button locationEnterButton;
    @FXML
    private TextField locationX;
    @FXML
    private TextField locationY;

    public void openDestinationScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Destination.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 407, 565));
            stage.show();
            stage.setY(100);
            stage.setX(550);

            // Close the current login window if needed
            Stage currentStage = (Stage) locationEnterButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLocationEnterButton() {
        try {
            // Get the values from the text boxes
            int x = Integer.parseInt(locationX.getText());
            int y = Integer.parseInt(locationY.getText());

            // Check if x and y are valid
            if (isValidCoordinate(x) && isValidCoordinate(y)) {
                System.out.println("Successful: Coordinates entered are valid");
                setPlayerX(x);
                setPlayerY(y);
                openDestinationScene();
            } else {
                System.out.println("Unsuccessful: Please enter valid x and y coordinates (between 0 and 6)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Unsuccessful: Please enter valid integer values for x and y");
        }
    }

    private boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate <= 6;
    }
}
