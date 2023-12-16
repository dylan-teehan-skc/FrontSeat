package GUI;

import LocationHandling.PlayersDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static LocationHandling.PassengerCount.setPassengers;


public class PassengerCount {

    @FXML
    private Button passengerEnterButton;
    @FXML
    private TextField passengerTextField;
    @FXML
    private Label passengerLabel;


    @FXML
    private void handlePassengerEnterButton() {
        try {
            // Get the values from the text boxes
            int passengers = Integer.parseInt(passengerTextField.getText());

            // Check if the number of passengers is valid
            if (isValidNumber(passengers)) {
                System.out.println("Successful: Number of passengers entered is valid");
                setPassengers(passengers);
                // Close the current stage
                Stage currentStage = (Stage) passengerEnterButton.getScene().getWindow();
                currentStage.close();
                // Start the PlayersDestination
                PlayersDestination startProgram = new PlayersDestination();
                startProgram.destination();
            } else {
                passengerLabel.setText("Unsuccessful: We can only hold between 1 and 7 passengers");
                System.out.println("Unsuccessful: We can only hold between 1 and 7 passengers");
            }
        } catch (NumberFormatException e) {
            passengerLabel.setText("Unsuccessful: Please enter valid integer values");
            System.out.println("Unsuccessful: Please enter valid integer values");
        }
    }

    private boolean isValidNumber(int passengers) {
        return passengers > 0 && passengers <= 7;
    }
}
