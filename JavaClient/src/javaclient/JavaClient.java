package javaclient;


//Import Libraries
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.connectscreen.ConnectScreenController;
import ui.ScreenController;

public class JavaClient extends Application {

    //Open the Connect Screen
    @Override
    public void start(Stage stage) throws Exception {
        changeWindow(stage, new FXMLLoader(ConnectScreenController.class.getResource("ConnectScreen.fxml")),
                "Connect To IP");
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

    //Method to change to another window
    public static void changeWindow(Stage stage, FXMLLoader fxmlLoader, String windowTitle) {
        if (stage == null) {
            stage = new Stage();
        }
        try {
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            ScreenController controller = fxmlLoader.getController();
            scene.getStylesheets().add(getCSS(controller));
            stage.setScene(scene);
            stage.setTitle(windowTitle);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getClass().getCanonicalName());
            System.exit(0);
        }
    }

    //Method to read CSS
    private static String getCSS(ScreenController controller) {
        return controller.getClass()
                .getResource("style.css")
                .toExternalForm();
    }

}
