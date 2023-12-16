package GUI;

import LocationHandling.PlayersDestination;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static LocationHandling.PlayersDestination.setDestinationX;
import static LocationHandling.PlayersDestination.setDestinationY;
public class Destination {
    @FXML
    private Button destinationEnterButton;
    @FXML
    private TextField destinationX;
    @FXML
    private TextField destinationY;

    @FXML
    private void handleLocationEnterButton() {
        try {
            // Get the values from the text boxes
            int x = Integer.parseInt(destinationX.getText());
            int y = Integer.parseInt(destinationY.getText());

            // Check if x and y are valid
            if (isValidCoordinate(x) && isValidCoordinate(y)) {
                System.out.println("Successful: Coordinates entered are valid");
                setDestinationX(x);
                setDestinationY(y);
                openPassengersScene();

            } else {
                System.out.println("Unsuccessful: Please enter valid x and y coordinates (between 0 and 6)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Unsuccessful: Please enter valid integer values for x and y");
        }
    }
    public void openPassengersScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassengerCount.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 475, 264));
            stage.show();
            stage.setY(300);
            stage.setX(550);

            // Close the current login window if needed
            Stage currentStage = (Stage) destinationEnterButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate <= 6;
    }
}


