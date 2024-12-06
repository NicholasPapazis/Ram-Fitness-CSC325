package viewmodel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class getStarted {

    @FXML
    public Pane dashboardLink;

    @FXML
    public Button calisthenicsBtn;
    @FXML
    public Button powerliftingLinkBtn;
    @FXML
    public Button loseFatBtn;
    @FXML
    public Button calisthenicsButton;


    @FXML
    public Pane logoutPane;



    /****** sidebar methods start *******/

    //go to dashboard
    public void goToDashboard(MouseEvent actionEvent) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
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
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
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
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
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
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
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





    //for button
    @FXML
    public void goToCalisthenics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/calisthenics.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for button
    @FXML
    public void goToLoseFat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/loseFat.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for button
    @FXML
    public void goToPowerlifting(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/powerlifting.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for button
    @FXML
    public void goToBuildMuscle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/buildMuscle.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
