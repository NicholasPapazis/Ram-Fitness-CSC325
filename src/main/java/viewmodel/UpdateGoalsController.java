package viewmodel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UpdateGoalsController {

    @FXML
    private Pane logoutPane;

    @FXML
    public TextField caloriesGoal;

    @FXML
    public  TextField caloriesProgress;

    @FXML
    public  TextField exerciseGoal;

    @FXML
    public  TextField exerciseProgress;

    @FXML
    private Pane navigationSection;

    @FXML
    public  Pane sideBar;

    @FXML
    public  TextField sleepGoal;

    @FXML
    public  TextField sleepProgress;

    @FXML
    public  Pane statusBar;

    @FXML
    public  Pane topBar;

    @FXML
    public  TextField weightGoal;

    @FXML
    public  TextField weightProgress;

    @FXML
    private Pane dashboardLink;



    /****** sidebar methods start *******/

    //go to dashboard
    public void goToDashboard(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //go to Update Goals
    public void goToUpdateDataPage(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateData.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //go to Get Started
    public void goToGetStarted(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/getStarted.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //go to Settings
    public void goToSettings(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //log out. Go to login page
    public void logOut(MouseEvent event) {
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

    /****** sidebar methods end******/







    public void saveData(){
        String documentId = DashboardController.getDocumentId();
        // Get a reference to the document
        DocumentReference docRef = MainApplication.fstore.collection("users").document(documentId);

        //create map of fields to update
        Map<String, Object> updates = new HashMap<>();

        updates.put("exerciseTarget", Integer.parseInt(exerciseGoal.getText()));//goal
        updates.put("calorieTarget", Integer.parseInt(caloriesGoal.getText()));//goal
        updates.put("weightTarget", Integer.parseInt(weightGoal.getText()));//goal
        updates.put("sleepTarget", Integer.parseInt(sleepGoal.getText()));//goal

        updates.put("dailyExerciseTime", Integer.parseInt(exerciseProgress.getText()));//progress
        updates.put("dailyCalorieIntake", Integer.parseInt(caloriesProgress.getText()));//progress
        updates.put("dailyWeightLifted", Integer.parseInt(weightProgress.getText()));//progress
        updates.put("sleepDuration", Integer.parseInt(sleepProgress.getText()));//progress

        // Asynchronously update the document
        ApiFuture<WriteResult> result = docRef.update(updates);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }






}

