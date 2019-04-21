package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller6 implements Initializable { //EXERCISE PAGE

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void buttonLogOutPressed(ActionEvent event)throws Exception{
        System.exit(0);

    }
    @FXML
    public void buttonGoToAdminPagePressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }



}
