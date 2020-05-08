/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ConnectScreenController;

/**
 *
 * @author Carlos
 */
public class JavaClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(ConnectScreenController.class.getResource("ConnectScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        //set unresizable
        stage.setResizable(false);

        //set window title
        stage.setTitle("P2PChat");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }

}
