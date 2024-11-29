package viewmodel;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.FirestoreContext;
import service.UserSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @FXML
    private Text alertTextEmail;
    @FXML
    private Text alertTextUsername;
    @FXML
    private Text alertTextPassword;

    @FXML
    private Button registerButton;




    public void initialize() {
        registerButton.setDisable(true); //disable loginButton until input fields are valid

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateEmail(); //validate
        });
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateUsername(); //validate
        });
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePassword(); //re-validate first name
        });

        emailField.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());

    }










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


    public boolean signUp(ActionEvent actionEvent) throws IOException {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                /*.setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setDisabled(false);*/
                .setEmail(emailField.getText())
                .setEmailVerified(false)
                .setPassword(passwordField.getText())
                .setDisplayName(usernameField.getText())
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = MainApplication.fauth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            return true;

        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }


    }



    // Validation for email (standard email format)
    private boolean validateEmail() {
        final String regex = "(([a-zA-Z])(\\w)+)@(farmingdale).(edu)";//regular expression
        String userInput = emailField.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            emailField.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextEmail.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(emailField.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            emailField.setStyle("-fx-border-color: red");
            alertTextEmail.setStyle("-fx-text-fill: red");
            alertTextEmail.setStyle("-fx-font-size: 10px");
            alertTextEmail.setText("* Email must end with @farmingdale.edu");

        } else { //if the field is empty or not focused, then remove styling and alert
            emailField.setStyle("");
            alertTextEmail.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field
    }

    // Validation for username (same as LoginController)
    private boolean validateUsername() {
        final String regex = "^.{2,25}$"; //regular expression
        String userInput = usernameField.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            usernameField.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextUsername.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(usernameField.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            usernameField.setStyle("-fx-border-color: red");
            alertTextUsername.setStyle("-fx-text-fill: red");
            alertTextUsername.setStyle("-fx-font-size: 10px");
            alertTextUsername.setText("* Username must be 2-25 characters");

        } else { //if the field is empty or not focused, then remove styling and alert
            usernameField.setStyle("");
            alertTextUsername.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field
    }

    // Validation for password (same as LoginController)
    private boolean validatePassword() {
        final String regex = "^.{2,25}$"; //regular expression
        String userInput = passwordField.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            passwordField.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextPassword.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(passwordField.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            passwordField.setStyle("-fx-border-color: red");
            alertTextPassword.setStyle("-fx-text-fill: red");
            alertTextPassword.setStyle("-fx-font-size: 10px");
            alertTextPassword.setText("* Password must be 2-25 characters");

        } else { //if the field is empty or not focused, then remove styling and alert
            passwordField.setStyle("");
            alertTextPassword.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field
    }


    //validation methods:
    private void checkValidationStatus() {
        //check if all fields are valid
        boolean allFieldsValid = validateEmail() && validateUsername() && validatePassword();

        //enable register button if all fields are valid
        registerButton.setDisable(!allFieldsValid);
    }


}