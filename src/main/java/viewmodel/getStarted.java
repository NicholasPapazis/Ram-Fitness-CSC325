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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class getStarted {

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



    public void initialize() {


        //hover effects for each pane on side bar
        setupHoverEffect(dashboardLink, "click to go to Dashboard");
        setupHoverEffect(updateGoalsLink, "click to update your goals");
        setupHoverEffect(getStartedLink, "click to learn about working out");
        setupHoverEffect(settingsLink, "click to open settings");

        setupHoverEffect(helpLink, "click for instructions");
        setupHoverEffect(logoutLink, "click logout of your account");


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





    //for button
    @FXML
    public void goToCalisthenics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/calisthenics.fxml"));
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

    //for button
    @FXML
    public void goToLoseFat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/loseFat.fxml"));
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

    //for button
    @FXML
    public void goToPowerlifting(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/powerlifting.fxml"));
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

    //for button
    @FXML
    public void goToBuildMuscle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/buildMuscle.fxml"));
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


    /* status bar methods start */

    //detects where mouse is
    private void setupHoverEffect(Pane pane, String message){
        pane.setOnMouseEntered(event -> showStatusMessage(message));
        pane.setOnMouseExited(event -> clearStatusMessage());
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
