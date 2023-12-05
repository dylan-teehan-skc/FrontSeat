module com.example.frontseat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.frontseat to javafx.fxml;
    exports com.example.frontseat;
}