package viewmodel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private Text logInLink;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    // Changes scene to show log in page
    public void logIn(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Validates and processes the sign-up action
    public void signUp(MouseEvent event) {
        if (validateFirstName(firstNameField.getText()) &&
                validateLastName(lastNameField.getText()) &&
                validateEmail(emailField.getText()) &&
                validateUsername(usernameField.getText()) &&
                validatePassword(passwordField.getText())) {

            // Proceed with registration logic here (e.g., save to database)
            showAlert("Registration Successful", "Your account has been created successfully.");

            // Optionally redirect to login page after successful registration
            logIn(event);
        } else {
            showAlert("Invalid Input", "Please check the input fields and try again.");
        }
    }

    public void login(ActionEvent event) {
        StringBuilder errorMessage = new StringBuilder();

        // Check each field and add an error message if validation fails
        if (!validateFirstName(firstNameField.getText())) {
            errorMessage.append("First name must contain only letters and be between 2-30 characters.\n");
        }

        if (!validateLastName(lastNameField.getText())) {
            errorMessage.append("Last name must contain only letters and be between 2-30 characters.\n");
        }

        if (!validateEmail(emailField.getText())) {
            errorMessage.append("Please enter a valid email address.\n");
        }

        if (!validateUsername(usernameField.getText())) {
            errorMessage.append("Username must be 5-15 characters long and contain only letters and numbers.\n");
        }

        if (!validatePassword(passwordField.getText())) {
            errorMessage.append("Password must be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.\n");
        }

        // If there's any error message, show an alert and return without proceeding
        if (errorMessage.length() > 0) {
            showAlert("Invalid Input", errorMessage.toString());
            return;
        }

        // If all validations pass, proceed to the login scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Validation for first name (only letters, 2-30 characters)
    private boolean validateFirstName(String firstName) {
        String firstNamePattern = "^[A-Za-z]{2,30}$";
        return firstName.matches(firstNamePattern);
    }

    // Validation for last name (only letters, 2-30 characters)
    private boolean validateLastName(String lastName) {
        String lastNamePattern = "^[A-Za-z]{2,30}$";
        return lastName.matches(lastNamePattern);
    }

    // Validation for email (standard email format)
    private boolean validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailPattern);
    }

    // Validation for username (same as LoginController)
    private boolean validateUsername(String username) {
        String usernamePattern = "^[a-zA-Z0-9]{5,15}$";
        return username.matches(usernamePattern);
    }

    // Validation for password (same as LoginController)
    private boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password.matches(passwordPattern);
    }

    // Helper method to show alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}