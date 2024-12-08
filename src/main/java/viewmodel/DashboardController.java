package viewmodel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.UserRecord;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Person;

import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;



/**
 * Controller for the Dashboard view in the Ram Fitness application.
 * Handles UI interactions, updating user progress data, and navigating between views.
 */
public class DashboardController {



    @FXML
    private Pane backgroud;

    @FXML
    private Pane logoutPane;
    @FXML
    private Text exerciseCurrentText;
    @FXML
    private Text caloriesCurrentText;
    @FXML
    private Text weightLiftedCurrentText;
    @FXML
    private Text sleepCurrentText;

    @FXML
    private Text exerciseDailyGoal;
    @FXML
    private Text calorieDailyGoal;
    @FXML
    private Text weightDailyGoal;
    @FXML
    private Text sleepDailyGoal;

    @FXML
    private ProgressBar exerciseProgBar;
    @FXML
    private ProgressBar caloriesProgBar;
    @FXML
    private ProgressBar weightLiftedProgBar;
    @FXML
    private ProgressBar sleepProgBar;


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
    private Text statusText;

    @FXML
    private Text exercisePercentage;
    @FXML
    private Text caloriesPercentage;
    @FXML
    private Text weightPercentage;
    @FXML
    private Text sleepPercentage;

    @FXML
    private Pane indicator;

    static String documentId;
    Document doc;
    static public Person p; //


    /**
     * Initializes the Dashboard by setting up hover effects and displaying progress data.
     */
    public void initialize() {
        indicator.setVisible(false);
        updateDashboardView();

        //hover effects for each pane on side bar
        setupHoverEffect(dashboardLink, "click to go to Dashboard");
        setupHoverEffect(updateGoalsLink, "click to update your goals");
        setupHoverEffect(getStartedLink, "click to learn about working out");
        setupHoverEffect(settingsLink, "click to open settings");

        setupHoverEffect(helpLink, "click for instructions");
        setupHoverEffect(logoutLink, "click logout of your account");



        final boolean[] movingRight = {true};
        if (exerciseCurrentText.getText().equals("0") &&
                caloriesCurrentText.getText().equals("0") &&
                weightLiftedCurrentText.getText().equals("0") &&
                sleepCurrentText.getText().equals("0") &&
                exerciseDailyGoal.getText().equals("0") &&
                calorieDailyGoal.getText().equals("0") &&
                weightDailyGoal.getText().equals("0") &&
                sleepDailyGoal.getText().equals("0")) {
            indicator.setVisible(true); //make arrow visible
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(1000), e -> {
                        if (movingRight[0]) {
                            indicator.setLayoutX(indicator.getLayoutX() + 20);
                        } else {
                            indicator.setLayoutX(indicator.getLayoutX() - 20);
                        }


                        movingRight[0] = !movingRight[0];
                    })
            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

    }

    /****** sidebar methods start *******/

    /**
     * Navigates to the Dashboard view when the Dashboard link is clicked.
     *
     * @param actionEvent The event triggered by the user clicking the dashboard link.
     * @throws IOException if there is an error loading the dashboard view.
     */
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

    /**
     * Navigates to the Update Goals page when the Update Goals link is clicked.
     *
     * @param event The event triggered by the user clicking the update goals link.
     */
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

    /**
     * Navigates to the Get Started page when the Get Started link is clicked.
     *
     * @param actionEvent The event triggered by the user clicking the Get Started link.
     * @throws IOException if there is an error loading the Get Started view.
     */
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

    /**
     * Navigates to the Settings page when the Settings link is clicked.
     *
     * @param actionEvent The event triggered by the user clicking the settings link.
     * @throws IOException if there is an error loading the settings view.
     */
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

    /**
     * Opens the Help window when the Help link is clicked.
     *
     * @param actionEvent The event triggered by the user clicking the help link.
     * @throws IOException if there is an error loading the help window.
     */
    public void goToHelp(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/help.fxml"));
            Scene helpScene = new Scene(root, 500, 300); // Adjust the size as needed
            helpScene.getStylesheets().add(getClass().getResource("/css/theme1.css").toExternalForm());
            Stage helpStage = new Stage();
            helpStage.setScene(helpScene);
            helpStage.setTitle("Help");
            helpStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            helpStage.initOwner(mainStage); // This makes sure the help window stays on top of the main window
            helpStage.showAndWait(); // Wait for the user to close the modal

            ThemeController.registerScene(helpScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs out the current user and navigates to the Login page.
     *
     * @param event The event triggered by the user clicking the logout link.
     */
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






    /**
     * Updates the dashboard view with the latest data for the user.
     * This includes displaying the users goals, current progress, and updating progress bars.
     */
    public void updateDashboardView() {
        UserRecord user = LoginController.sendUserRecord();
        System.out.println("Dashboard user: " + user);

        ApiFuture<QuerySnapshot> future = MainApplication.fstore.collection("users").get();
        List<QueryDocumentSnapshot> documents;
        try{
            documents = future.get().getDocuments();
            if(documents.size() > 0){
                System.out.println("Outting...");
                for (QueryDocumentSnapshot document : documents) {
                    if(document.getData().get("email") .equals(user.getEmail())){
                        System.out.println(document.getData().get("email"));
                        documentId = document.getId();
                        p = new Person(
                                document.getData().get("email").toString(),
                                document.getData().get("username").toString(),
                                document.getData().get("password").toString(),
                                Integer.parseInt(document.getData().get("exerciseTarget").toString()),
                                Integer.parseInt(document.getData().get("calorieTarget").toString()),
                                Integer.parseInt(document.getData().get("weightTarget").toString()),
                                Integer.parseInt(document.getData().get("sleepTarget").toString())

                                );

                        p.setDailyExerciseTime(Integer.parseInt(document.getData().get("dailyExerciseTime").toString()));
                        p.setDailyCalorieIntake(Integer.parseInt(document.getData().get("dailyCalorieIntake").toString()));
                        p.setDailyWeightLifted(Integer.parseInt(document.getData().get("dailyWeightLifted").toString()));
                        p.setSleepDuration(Integer.parseInt(document.getData().get("sleepDuration").toString()));

                        break;
                    }

                }
            }
        } catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }


        //update info logic here
        System.out.println("here is the document info: " + documentId); //prints the document where user info is stored for this session
        exerciseDailyGoal.setText(String.valueOf(p.getExerciseTarget()));
        calorieDailyGoal.setText(String.valueOf(p.getCalorieTarget()));
        weightDailyGoal.setText(String.valueOf(p.getWeightTarget()));
        sleepDailyGoal.setText(String.valueOf(p.getSleepTarget()));

        exerciseCurrentText.setText(String.valueOf(p.getDailyExerciseTime()));
        caloriesCurrentText.setText(String.valueOf(p.getDailyCalorieIntake()));
        weightLiftedCurrentText.setText(String.valueOf(p.getDailyWeightLifted()));
        sleepCurrentText.setText(String.valueOf(p.getSleepDuration()));


        double exProg = (double)p.getDailyExerciseTime() / (double)p.getExerciseTarget();
        exerciseProgBar.setProgress(exProg);
        exerciseProgBar.setStyle("-fx-accent: #63C041;");

        double calProg = (double)p.getDailyCalorieIntake() / (double)p.getCalorieTarget();
        caloriesProgBar.setProgress(calProg);
        caloriesProgBar.setStyle("-fx-accent: #F97316;");

        double weightProg = (double)p.getDailyWeightLifted() / (double)p.getWeightTarget();
        weightLiftedProgBar.setProgress(weightProg);
        weightLiftedProgBar.setStyle("-fx-accent: #8B5CF6;");

        double sleepProg = (double)p.getSleepDuration() / (double)p.getSleepTarget();
        sleepProgBar.setProgress(sleepProg);
        sleepProgBar.setStyle("-fx-accent: #06B6D4;");


        //set text of progress bars
        exercisePercentage.setText((String.format("%.0f", exProg*100) + "%"));
        caloriesPercentage.setText((String.format("%.0f", calProg*100) + "%"));
        weightPercentage.setText((String.format("%.0f", weightProg*100) + "%"));
        sleepPercentage.setText((String.format("%.0f", sleepProg*100) + "%"));



    }

    /**
     * Retrieves the document ID of the current user.
     *
     * @return The document ID of the current user.
     */
    public static String getDocumentId() {
        return documentId;
    }



    /* status bar methods start */

    /**
     * sets up hover effects for the given pane.
     *
     * @param pane The pane to apply the hover effect to.
     * @param message The message to display when the mouse enters the pane.
     */
    private void setupHoverEffect(Pane pane, String message){
        pane.setOnMouseEntered(event -> showStatusMessage(message));
        pane.setOnMouseExited(event -> clearStatusMessage());
    }

    /**
     * Displays the given status message in the status text area.
     *
     * @param message The status message to display.
     */
    private void showStatusMessage(String message) {
        statusText.setText(message);
    }

    /**
     * Clears the status message from the status text area.
     * This method is typically called when the mouse exits a pane.
     */
    private void clearStatusMessage() {
        statusText.setText(""); //clear the message when the mouse leaves the pane
    }

    /* status bar methods end */



}
