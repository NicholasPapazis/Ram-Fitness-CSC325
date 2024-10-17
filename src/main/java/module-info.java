module com.example.ramfitnesscsc325 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ramfitnesscsc325 to javafx.fxml;
    exports com.example.ramfitnesscsc325;
}