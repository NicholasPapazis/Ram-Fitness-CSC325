package viewmodel;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.FirestoreContext;

/**
 * MainApplication is the entry point of the Ram Fitness application.
 * It sets up the initial user interface and manages the transitions between scenes.
 * It also initializes the connection to Firebase for authentication and Firestore database operations.
 */
public class MainApplication extends Application {

    private static Scene scene;
    //private static DbConnectivityClass cnUtil;
    private Stage primaryStage;


    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();


    /**
     * The main method launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //cnUtil = new DbConnectivityClass();
        launch(args);

    }

    /**
     *This method is called after the application is initialized.
     *It sets the application window icon, title, and displays the initial splash screen.
     *
     * @param primaryStage The primary stage (window) for the application
     */
    public void start(Stage primaryStage) {
        Image icon = new Image(getClass().getResourceAsStream("/images/ramFitLogo.png"));
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Ram Fitness");
        showScene1();
    }

    /**
     * Displays the splash screen scene and initializes the Firebase connection.
     */
    private void showScene1() {
        try {
            initializeFB();
            Parent root = FXMLLoader.load(getClass().getResource("/view/splashscreen.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/splashscreen.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the scene from the splash screen to the login screen with a fade effect.
     */
    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/view/login.fxml").toURI().toURL());
            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            currentScene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(.25);
            fadeOut.setDelay(Duration.seconds(3)); //delay the fading
            fadeOut.setOnFinished(e -> {
                Scene newScene = new Scene(newRoot, 900, 600);
                primaryStage.setScene(newScene);
                primaryStage.show();
            });
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the Firebase services
     */
    public void initializeFB(){
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
    }


}