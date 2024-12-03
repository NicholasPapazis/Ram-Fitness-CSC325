package viewmodel;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person;

import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DashboardController {

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





    static String documentId;
    Document doc;
    Person p;

    public void initialize() {
        updateDashboardView();
    }



    //for text
    public void goToGetStarted(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/getStarted.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // switches to login.fxml page
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

    // switches to updateData.fxml page
    public void goToUpdateDataPage(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateData.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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




    }

    public static String getDocumentId() {
        return documentId;
    }



}