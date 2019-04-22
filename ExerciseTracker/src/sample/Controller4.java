package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller4 implements Initializable { //SETTINGS PAGE

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event) throws Exception{
        System.exit(0);

    }
    @FXML
    public void buttonGoToTheAdminPagePressed(ActionEvent event) throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }


}
