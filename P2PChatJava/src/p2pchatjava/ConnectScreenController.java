/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchatjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.net.*;
import javafx.scene.control.Label;

public class ConnectScreenController implements Initializable {

    @FXML
    private TextField txtCodename;

    @FXML
    private TextField txtIp;

    @FXML
    private Button btnConnect;
    
    @FXML
    private Label myIp;

    private Thread listener;

    private volatile Socket socket;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater( () -> {StartListener();} );

        String systemipaddress = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com/");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            systemipaddress = "Cannot Execute Properly"; 
            System.out.println(e.getClass().getCanonicalName());
        }
        String ip = "My ip: "+systemipaddress;
        myIp.setText(ip);
    }

    @FXML
    private void handleConnectAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent MainScreen = (Parent)fxmlLoader.load();
        MainScreenController controller = fxmlLoader.<MainScreenController>getController();
        controller.setIp(this.txtIp.getText());
        controller.setCodename(this.txtCodename.getText());

        Scene scene = new Scene(MainScreen);

        stage.setScene(scene);
        
        //set unresizable
        stage.setResizable(false);
        
        //set window title
        stage.setTitle("P2PChat");
        
        stage.show();

        Stage ConnectScreen = (Stage) btnConnect.getScene().getWindow();
        
        ConnectScreen.close();
        
    }

    private synchronized void StartListener() {
        listener = new Thread("listener") {
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(10000);
                    try {
                        socket = server.accept();
                        if (socket.isConnected()) {
                            System.out.println("Connected");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        server.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Platform.exit();
                }
            }
        };
        listener.start();
    }
}
