/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchatjava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ConnectScreenController implements Initializable {

    @FXML
    private TextField txtCodename;

    @FXML
    private TextField txtIp;

    @FXML
    private Button btnConnect;
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
