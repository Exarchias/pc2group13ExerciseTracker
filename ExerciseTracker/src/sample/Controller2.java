package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable { // ADMIN PAGE

    private int selectedUser = 0;

    @FXML
    TextField userSelector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()){
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userSelector.setText(String.valueOf(selectedUser));

    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event)throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToTheUserPage(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);
    }

    @FXML
    public void testViewUsers(ActionEvent event){
        System.out.println("I am firing ma lazer");
    }

    @FXML
    public void addNewUser(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene5);
        System.out.println("Adding some user to the system");
    }

    @FXML
    public void deleteUser(ActionEvent event){
        selectedUser = Integer.parseInt(userSelector.getText());
        System.out.println("Removing the user " + selectedUser + "from the system");
    }

    @FXML
    public void editUser(ActionEvent event){
        selectedUser = Integer.parseInt(userSelector.getText());
        System.out.println("Editing the user " + selectedUser + "from the system");
    }






}
