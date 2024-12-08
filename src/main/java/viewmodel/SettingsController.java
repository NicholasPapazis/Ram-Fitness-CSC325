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

public class SettingsController {


    @FXML
    private Pane theme1Pane;
    @FXML
    private Pane theme2Pane;
    @FXML
    private Pane theme3Pane;
    @FXML
    private Pane theme4Pane;


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
    private Button deleteBtn;


    public void initialize() {

        setThemeCircleIndicator();


        setupHoverEffect(dashboardLink, "click to go to Dashboard");
        setupHoverEffect(updateGoalsLink, "click to update your goals");
        setupHoverEffect(getStartedLink, "click to learn about working out");
        setupHoverEffect(settingsLink, "click to open settings");

        setupHoverEffect(helpLink, "click for instructions");
        setupHoverEffect(logoutLink, "click logout of your account");

        setupHoverEffect(theme1Pane, "click to change theme to light");
        setupHoverEffect(theme2Pane, "click to change theme to light/dark");
        setupHoverEffect(theme3Pane, "click to change theme to dark");
        setupHoverEffect(theme4Pane, "click to change theme to dark/light");

        setupHoverEffect(deleteBtn, "click to delete your account");




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




    public void clearThemeCircles()
    {
        theme1Pane.setStyle("-fx-border-color: transparent");
        theme2Pane.setStyle("-fx-border-color: transparent");
        theme3Pane.setStyle("-fx-border-color: transparent");
        theme4Pane.setStyle("-fx-border-color: transparent");
    }

    private void setThemeCircleIndicator(){
        ThemeController.Theme theme = ThemeController.getCurrentTheme();
        if(theme .equals(ThemeController.Theme.THEME_ONE)){
            clearThemeCircles();
            theme1Pane.setStyle("-fx-border-color: #FF6347;" +
                    "-fx-border-width: 3px;"
                    + "-fx-border-radius: 100px;"
                    + "-fx-pref-width: 70px;"
                    + "-fx-pref-height: 70px;");
        } else if(theme .equals(ThemeController.Theme.THEME_TWO)){
            clearThemeCircles();
            theme2Pane.setStyle("-fx-border-color: #FF6347;" +
                    "-fx-border-width: 3px;"
                    + "-fx-border-radius: 100px;"
                    + "-fx-pref-width: 70px;"
                    + "-fx-pref-height: 70px;");
        } else if (theme .equals(ThemeController.Theme.THEME_THREE)){
            clearThemeCircles();
            theme3Pane.setStyle("-fx-border-color: #FF6347;" +
                    "-fx-border-width: 3px;"
                    + "-fx-border-radius: 100px;"
                    + "-fx-pref-width: 70px;"
                    + "-fx-pref-height: 70px;");
        } else if (theme .equals(ThemeController.Theme.THEME_FOUR)){
            clearThemeCircles();
            theme4Pane.setStyle("-fx-border-color: #FF6347;" +
                    "-fx-border-width: 3px;"
                    + "-fx-border-radius: 100px;"
                    + "-fx-pref-width: 70px;"
                    + "-fx-pref-height: 70px;");
        }
    }


    public void setTheme1(MouseEvent mouseEvent)
    {
        ThemeController.applyTheme(ThemeController.Theme.THEME_ONE);
        setThemeCircleIndicator();
    }
    public void setTheme2()
    {
        ThemeController.applyTheme(ThemeController.Theme.THEME_TWO);
        setThemeCircleIndicator();
    }
    public void setTheme3()
    {
        ThemeController.applyTheme(ThemeController.Theme.THEME_THREE);
        setThemeCircleIndicator();
    }
    public void setTheme4()
    {
        ThemeController.applyTheme(ThemeController.Theme.THEME_FOUR);
        setThemeCircleIndicator();
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

    //shows status message
    private void showStatusMessage(String message) {
        statusText.setText(message);
    }

    //clears status message
    private void clearStatusMessage() {
        statusText.setText(""); // Clear the message when mouse leaves the Pane
    }

    /* status bar methods end */


    //delete account button method
    public void deleteAccount(ActionEvent event) {
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



}
