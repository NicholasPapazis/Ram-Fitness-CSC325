package viewmodel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class LoginController {


    @FXML
    private Text alertTextLogin;

    @FXML
    private Text alertTextEmail;
    @FXML
    private Text alertTextPassword;

    @FXML
    private Button signUpLink;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public static UserRecord user;
    public static UserRecord sendUserRecord()
    {
        return user;
    }


    // Changes scene to show log in page
    public void goToSignUpPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/signUp.css").toExternalForm());
            Stage window = (Stage) (emailField.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToDashboard() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) (emailField.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Method to list all users' data from Firestore and print it to the console
    public void login() {
        String userEmail = emailField.getText();
        String userPassword = passwordField.getText();
        boolean userFound = false; //tracks if the user is found
        try {
            // Query Firestore to get all users in the 'users' collection
            ApiFuture<QuerySnapshot> query = FirestoreClient.getFirestore().collection("users").get();

            // Get the query result
            QuerySnapshot querySnapshot = query.get();

            // Check if there are any users in the database
            if (querySnapshot.isEmpty()) {
                System.out.println("No users found in the database.");
            } else {
                // Stream the documents and print their data
                querySnapshot.getDocuments().stream()
                        .map(document -> document.toObject(Person.class))  // Convert each document to a Person
                        .filter(person -> person != null) // Optional: filter out null persons
                        .forEach(person -> {
                            // Print each user's data to the console
                            if(userEmail.equals(person.getEmail()) && userPassword.equals(person.getPassword())) {
                                System.out.println("user found");
                                try {
                                    user = FirebaseAuth.getInstance().getUserByEmail(userEmail);

                                    System.out.println("The user is: " + user);
                                    goToDashboard();

                                } catch (FirebaseAuthException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        });

                //checks if user credentials were found in database
                if(!userFound) {
                    System.out.println("invalid email or password");
                    alertTextLogin.setText("Invalid email or password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }




    // Utility method to show error alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }



    private boolean validateUsername(String username) {
        String usernamePattern = "^[a-zA-Z0-9]{5,15}$";
        return username.matches(usernamePattern);
    }

    private boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password.matches(passwordPattern);
    }

    /*private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }*/
}

