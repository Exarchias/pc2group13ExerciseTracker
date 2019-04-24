package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {  //USER PAGE

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event)throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToTheAdminPagePressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }
    @FXML
    public void buttonGoToLogPagePressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene5);

    }
    @FXML
    public void buttonGoToTheSettingPage(ActionEvent event) throws Exception{
        Main.getInstance().setScene(Main.Scene4);
    }


}
