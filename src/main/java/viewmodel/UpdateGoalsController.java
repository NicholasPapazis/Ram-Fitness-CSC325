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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static viewmodel.DashboardController.p; //import person from dashboard

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


    @FXML
    private Text alertTextExerciseProgress;
    @FXML
    private Text alertTextCaloriesProgress;
    @FXML
    private Text alertTextWeightProgress;
    @FXML
    private Text alertTextSleepProgress;

    @FXML
    private Text alertTextExerciseGoal;
    @FXML
    private Text alertTextCaloriesGoal;
    @FXML
    private Text alertTextWeightGoal;
    @FXML
    private Text alertTextSleepGoal;



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




        exerciseProgress.textProperty().addListener((observable, oldValue, newValue) -> {
            validateExerciseProgress();
        });
        caloriesProgress.textProperty().addListener((observable, oldValue, newValue) -> {
            validateCaloriesProgress();
        });
        weightProgress.textProperty().addListener((observable, oldValue, newValue) -> {
            validateWeightProgress();
        });
        sleepProgress.textProperty().addListener((observable, oldValue, newValue) -> {
            validateSleepProgress();
        });

        exerciseGoal.textProperty().addListener((observable, oldValue, newValue) -> {
            validateExerciseGoal();
        });
        caloriesGoal.textProperty().addListener((observable, oldValue, newValue) -> {
            validateCaloriesGoal();
        });
        weightGoal.textProperty().addListener((observable, oldValue, newValue) -> {
            validateWeightGoal();
        });
        sleepGoal.textProperty().addListener((observable, oldValue, newValue) -> {
            validateSleepGoal();
        });

        exerciseProgress.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());
        caloriesProgress.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());
        weightProgress.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());
        sleepProgress.textProperty().addListener((observable, oldValue, newValue) -> checkValidationStatus());


        exerciseProgress.setText(String.valueOf(p.getDailyExerciseTime()));
        caloriesProgress.setText(String.valueOf(p.getDailyCalorieIntake()));
        weightProgress.setText(String.valueOf(p.getDailyWeightLifted()));
        sleepProgress.setText(String.valueOf(p.getSleepDuration()));

        exerciseGoal.setText(String.valueOf(p.getExerciseTarget()));
        caloriesGoal.setText(String.valueOf(p.getCalorieTarget()));
        weightGoal.setText(String.valueOf(p.getWeightTarget()));
        sleepGoal.setText(String.valueOf(p.getSleepTarget()));



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

    //go to help
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



    /* validate fields start */

    // Validation for exerciseProgress
    private boolean validateExerciseProgress() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = exerciseProgress.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            exerciseProgress.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextExerciseProgress.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(exerciseProgress.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            exerciseProgress.setStyle("-fx-border-color: red");
            alertTextExerciseProgress.setStyle("-fx-text-fill: red");
            alertTextExerciseProgress.setStyle("-fx-font-size: 10px");
            alertTextExerciseProgress.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            exerciseProgress.setStyle("");
            alertTextExerciseProgress.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field

    }

    // Validation for caloriesProgress
    private boolean validateCaloriesProgress() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = caloriesProgress.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            caloriesProgress.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextCaloriesProgress.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(caloriesProgress.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            caloriesProgress.setStyle("-fx-border-color: red");
            alertTextCaloriesProgress.setStyle("-fx-text-fill: red");
            alertTextCaloriesProgress.setStyle("-fx-font-size: 10px");
            alertTextCaloriesProgress.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            caloriesProgress.setStyle("");
            alertTextCaloriesProgress.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field

    }


    // Validation for weightProgress
    private boolean validateWeightProgress() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = weightProgress.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            weightProgress.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextWeightProgress.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(weightProgress.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            weightProgress.setStyle("-fx-border-color: red");
            alertTextWeightProgress.setStyle("-fx-text-fill: red");
            alertTextWeightProgress.setStyle("-fx-font-size: 10px");
            alertTextWeightProgress.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            weightProgress.setStyle("");
            alertTextWeightProgress.setText("");
        }
        return false; //return false if user has not yet inputted anything into the text field

    }

    // Validation for sleepProgress
    private boolean validateSleepProgress() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = sleepProgress.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            sleepProgress.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextSleepProgress.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(sleepProgress.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            sleepProgress.setStyle("-fx-border-color: red");
            alertTextSleepProgress.setStyle("-fx-text-fill: red");
            alertTextSleepProgress.setStyle("-fx-font-size: 10px");
            alertTextSleepProgress.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            sleepProgress.setStyle("");
            alertTextSleepProgress.setText("");
        }
        return false; //return false if user has not yet inputted anything into the text field

    }


    //goal fields below

    // Validation for exerciseGoal
    private boolean validateExerciseGoal() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = exerciseGoal.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            exerciseGoal.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextExerciseGoal.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(exerciseGoal.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            exerciseGoal.setStyle("-fx-border-color: red");
            alertTextExerciseGoal.setStyle("-fx-text-fill: red");
            alertTextExerciseGoal.setStyle("-fx-font-size: 10px");
            alertTextExerciseGoal.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            exerciseGoal.setStyle("");
            alertTextExerciseGoal.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field

    }

    // Validation for caloriesProgress
    private boolean validateCaloriesGoal() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = caloriesGoal.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            caloriesGoal.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextCaloriesGoal.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(caloriesGoal.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            caloriesGoal.setStyle("-fx-border-color: red");
            alertTextCaloriesGoal.setStyle("-fx-text-fill: red");
            alertTextCaloriesGoal.setStyle("-fx-font-size: 10px");
            alertTextCaloriesGoal.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            caloriesGoal.setStyle("");
            alertTextCaloriesGoal.setText("");
        }
        return false; //return false if user has not yet inputed anything into the text field

    }


    // Validation for weightProgress
    private boolean validateWeightGoal() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = weightGoal.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            weightGoal.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextWeightGoal.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(weightGoal.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            weightGoal.setStyle("-fx-border-color: red");
            alertTextWeightGoal.setStyle("-fx-text-fill: red");
            alertTextWeightGoal.setStyle("-fx-font-size: 10px");
            alertTextWeightGoal.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            weightGoal.setStyle("");
            alertTextWeightGoal.setText("");
        }
        return false; //return false if user has not yet inputted anything into the text field

    }

    // Validation for sleepProgress
    private boolean validateSleepGoal() {
        final String regex = "\\d+"; //ensure only digits
        String userInput = sleepGoal.getText(); //gets text from input

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(userInput);

        boolean inputValid = matcher.matches(); //check if input matches regex

        //checks if above statement is valid
        if(inputValid){ //if input is valid
            sleepGoal.setStyle("-fx-border-color: #15bc98"); //sets border color of input field to green
            alertTextSleepGoal.setText(""); //gets rid of any alert text in alert box
            return true; //returns true because the input is valid
        }
        else if(sleepGoal.isFocused() && !userInput.isEmpty()  ) { //if field is focused and not empty
            sleepGoal.setStyle("-fx-border-color: red");
            alertTextSleepGoal.setStyle("-fx-text-fill: red");
            alertTextSleepGoal.setStyle("-fx-font-size: 10px");
            alertTextSleepGoal.setText("* must be a numerical value");

        } else { //if the field is empty or not focused, then remove styling and alert
            sleepGoal.setStyle("");
            alertTextSleepGoal.setText("");
        }
        return false; //return false if user has not yet inputted anything into the text field

    }





    //validation methods:
    private void checkValidationStatus() {
        //check if all fields are valid
        boolean allFieldsValid = validateExerciseProgress() && validateCaloriesProgress() && validateWeightProgress() && validateSleepProgress() && validateExerciseGoal() && validateCaloriesGoal() && validateWeightGoal() && validateSleepGoal();

        //enable register button if all fields are valid
        saveBtn.setDisable(!allFieldsValid);
    }

    /* validate fields end */



}

