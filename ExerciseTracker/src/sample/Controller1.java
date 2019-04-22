package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller1 implements Initializable { //LOGIN PAGE

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnAdminPressed(ActionEvent event) throws Exception{
        Main.getInstance().setScene(Main.Scene2);


    }
    @FXML
    public void btnUserPressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);

    }


}
