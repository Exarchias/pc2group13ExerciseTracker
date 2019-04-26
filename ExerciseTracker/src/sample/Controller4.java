package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller4 implements Initializable { //LOG PAGE

    private String username;
    private String password;
    private String email;
    private boolean userIsAdmin;

    @FXML
    TextField userNameTextField;

    @FXML
    TextField passWordTextField;

    @FXML
    TextField emailTextField;

    @FXML
    CheckBox isAdminCheckBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()){
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userNameTextField.setText(DataHolder.supervisedUser.getUserName());
        passWordTextField.setText(DataHolder.supervisedUser.getPassWord());
        emailTextField.setText(DataHolder.supervisedUser.getEmail());
        if(DataHolder.supervisedUser.isAnAdmin()){
            isAdminCheckBox.setSelected(true);
        } else {
            isAdminCheckBox.setVisible(false);
        }


    }
    @FXML
    public void buttonLogOutPressed(ActionEvent event)throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToTheAdminPage(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);
    }

    @FXML
    public void editUser(ActionEvent event)throws Exception{
        System.out.println("Creating User...");
        username = userNameTextField.getText();
        password = passWordTextField.getText();
        email = emailTextField.getText();
        userIsAdmin = isAdminCheckBox.isSelected();
        System.out.println("The username is: " + username);
        System.out.println("The password is: " + password);
        System.out.println("The email is: " + email);
        if(userIsAdmin){
            System.out.println("The user is an Admin");
            DataHolder.supervisedUser.setAnAdmin(true);
        } else {
            System.out.println("The user is NOT an Admin");
            DataHolder.supervisedUser.setAnAdmin(false);
        }
        DataHolder.supervisedUser.setUserName(username);
        DataHolder.supervisedUser.setPassWord(password);
        DataHolder.supervisedUser.setEmail(email);
        System.out.println("The User is considered edited");
        Main.getInstance().setScene(Main.Scene2);
    }




}
