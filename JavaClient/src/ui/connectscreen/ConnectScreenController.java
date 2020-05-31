package ui.connectscreen;

import javaclient.JavaClient;
import ui.mainscreen.*;
import ui.ScreenController;
import models.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ConnectScreenController extends ScreenController implements Initializable {

    @FXML
    private TextField txtCodename;

    @FXML
    private TextField txtIp;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnCreate;

    @FXML
    private Label myIp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Platform.runLater(() -> {
//            StartListener();
//        });

        String systemipaddress;
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com/");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        } catch (IOException e) {
            systemipaddress = "Cannot Execute Properly";
            System.out.println(e.getMessage());
        }
        String ip = "My ip: " + systemipaddress;
        myIp.setText(ip);
    }

    @FXML
    private void handleConnectAction(ActionEvent event) {
        String tmpIp = txtIp.getText();
        String tmpCodename = txtCodename.getText();
        if (!(tmpIp.trim().isEmpty()) && !(tmpCodename.trim().isEmpty())) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource("MainScreen.fxml"));

            JavaClient.changeWindow(stage, fxmlLoader, "Main Screen");

            MainScreenController mainScreenController = fxmlLoader.getController();
            User newUser = new User(tmpCodename);
            mainScreenController.setIp(tmpIp);
            mainScreenController.addUser(newUser);

            Stage connectScreen = (Stage) btnConnect.getScene().getWindow();
            
            connectScreen.close();
            
            
        } else {
            System.out.println("Campos invalidos");
            if (tmpCodename.trim().isEmpty()) {
                txtCodename.setPromptText("Cannot be empty");
            }
            if (tmpIp.trim().isEmpty()) {
                txtIp.setPromptText("Cannot be empty");
            }
        }
    }

    @FXML
    private void handleCreateAction(ActionEvent event) {
        String tmpIp = txtIp.getText();
        String tmpCodename = txtCodename.getText();
        
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource("MainScreen.fxml"));

        JavaClient.changeWindow(stage, fxmlLoader, "Main Screen");

        MainScreenController mainScreenController = fxmlLoader.getController();
        User newUser = new User(tmpCodename);
        mainScreenController.setIp(tmpIp);
        mainScreenController.addUser(newUser);

        Stage connectScreen = (Stage) btnConnect.getScene().getWindow();

        connectScreen.close();
    }

//    private synchronized void StartListener() {
//        listener = new Thread("listener") {
//            public void run() {
//                try {
//                    ServerSocket server = new ServerSocket(10000);
//                    try {
//                        socket = server.accept();
//                        if (socket.isConnected()) {
//                            System.out.println("Connected");
//                        }
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    } finally {
//                        server.close();
//                    }
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                    Platform.exit();
//                }
//            }
//        };
//        listener.start();
//    }
}
