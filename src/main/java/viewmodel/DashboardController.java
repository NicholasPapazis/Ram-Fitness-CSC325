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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person;

import javax.swing.text.Document;
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




    String documentId;
    Document doc;
    Person p;

    public void initialize() {
        updateDashboardView();
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

                        break;
                    }

                }
            }
        } catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }


        //update info logic here
        System.out.println("here is the document info: " + documentId);
        exerciseDailyGoal.setText(String.valueOf(p.getExerciseTarget()));
        calorieDailyGoal.setText(String.valueOf(p.getCalorieTarget()));
        weightDailyGoal.setText(String.valueOf(p.getWeightTarget()));
        sleepDailyGoal.setText(String.valueOf(p.getSleepTarget()));

        exerciseCurrentText.setText(String.valueOf(p.getDailyExerciseTime()));
        caloriesCurrentText.setText(String.valueOf(p.getDailyCalorieIntake()));
        weightLiftedCurrentText.setText(String.valueOf(p.getDailyWeightLifted()));
        sleepCurrentText.setText(String.valueOf(p.getSleepDuration()));





    }


}