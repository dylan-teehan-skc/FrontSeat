package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Main class for starting the login application
public class LoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("driverLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 674, 438);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stage.setY(120);
        stage.setX(400);
    }

    public void PrintHello(){
        System.out.println("Hello");
    }
    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}
