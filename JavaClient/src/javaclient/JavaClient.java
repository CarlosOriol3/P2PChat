package javaclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ConnectScreenController;

public class JavaClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ConnectScreenController.class.getResource("ConnectScreen.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(ConnectScreenController.class.getResource("connectscreen.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        //set unresizable
        stage.setResizable(false);

        //set window title
        stage.setTitle("P2P Chat");
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

}
