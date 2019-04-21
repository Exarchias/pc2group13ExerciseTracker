package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable { // ADMIN PAGE

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event){
        System.exit(0);

    }
    @FXML
    public void buttonGoToTheUserPage(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);
    }





}
