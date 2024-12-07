package viewmodel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    @FXML
    private Pane updateGoalsLink;
    @FXML
    private Pane getStartedLink;
    @FXML
    private Pane settingsLink;

    @FXML
    private Pane helpLink;
    @FXML
    private Pane logoutLink;

    @FXML
    private Button saveBtn;


    @FXML
    private Text statusText;


    public void initialize() {

        //hover effects for each pane on side bar
        setupHoverEffect(dashboardLink, "click to go to Dashboard");
        setupHoverEffect(updateGoalsLink, "click to update your goals");
        setupHoverEffect(getStartedLink, "click to learn about working out");
        setupHoverEffect(settingsLink, "click to open settings");

        setupHoverEffect(helpLink, "click for instructions");
        setupHoverEffect(logoutLink, "click logout of your account");

        setupHoverEffect(saveBtn, "click to save changes");

        setupHoverEffect(exerciseProgress, "modify your daily exercise time");
        setupHoverEffect(caloriesProgress, "modify your daily calorie intake");
        setupHoverEffect(weightProgress, "modify your daily weight lifted total");
        setupHoverEffect(sleepProgress, "modify the amount of sleep you received last night");

        setupHoverEffect(exerciseGoal, "modify your daily exercise time goal");
        setupHoverEffect(caloriesGoal, "modify your daily calorie intake goal");
        setupHoverEffect(weightGoal, "modify your daily weight lifted total goal");
        setupHoverEffect(sleepGoal, "modify the amount of sleep you received last night goal");


    }

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
            ThemeController.registerScene(scene);//register theme so that style can be applied
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
            ThemeController.registerScene(scene);//register theme so that style can be applied
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
            ThemeController.registerScene(scene);//register theme so that style can be applied
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
            ThemeController.registerScene(scene);//register theme so that style can be applied
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




    /* status bar methods start */

    //detects where mouse is for pane
    private void setupHoverEffect(Pane pane, String message){
        pane.setOnMouseEntered(event -> showStatusMessage(message));
        pane.setOnMouseExited(event -> clearStatusMessage());
    }

    //detects where mouse is for button
    private void setupHoverEffect(Button btn, String message){
        btn.setOnMouseEntered(event -> showStatusMessage(message));
        btn.setOnMouseExited(event -> clearStatusMessage());
    }

    //detects where mouse is for button
    private void setupHoverEffect(TextField txt, String message){
        txt.setOnMouseEntered(event -> showStatusMessage(message));
        txt.setOnMouseExited(event -> clearStatusMessage());
    }

    //shows status message
    private void showStatusMessage(String message) {
        statusText.setText(message);
    }

    //clears status message
    private void clearStatusMessage() {
        statusText.setText(""); // Clear the message when mouse leaves the Pane
    }

    /* status bar methods end */





}

