package viewmodel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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


    public void initialize() {

        setThemeCircleIndicator();

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





}
