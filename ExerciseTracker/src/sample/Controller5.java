package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller5 implements Initializable { //LOG PAGE


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event) throws Exception{
        System.exit(0);

    }
    @FXML
    public void buttonGoToTheAdminPage(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }
    @FXML
    public void buttonGoToTheExercisePagePressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene6);
    }


}
