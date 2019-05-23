package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller5 implements Initializable { //LOG PAGE

    private String username;
    private String password;
    private String email;
    private boolean userIsAdmin;
    @FXML
    Label lblActiveName;
    @FXML
    TextField userNameTextField;

    @FXML
    TextField passWordTextField;

    @FXML
    TextField emailTextField;

    @FXML
    CheckBox isAdminCheckBox;
    @FXML
    Button btnGoToAdminPage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()){
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (DataHolder.isAdmin())
            btnGoToAdminPage.setVisible(true);
        else btnGoToAdminPage.setVisible(false);
        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());

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
    public void createUser(ActionEvent event)throws Exception{
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
        }
        User userObj = new User(username, password, email, userIsAdmin);
        DataHolder.userList.add(userObj);
        System.out.println("The User is considered created");
        Main.getInstance().setScene(Main.Scene2);
        //TESTING ADDING A USER ON THE DATA BASE(START)
        DB db = new DB();
        db.addOneUser(username, password, email, userIsAdmin);
        //TESTING ADDING A USER ON THE DATA BASE(END)
    }

    @FXML
    public void cleanTheFields(ActionEvent event)throws Exception{
        System.out.println("Cleaning the fields...");
        userNameTextField.setText("");
        passWordTextField.setText("");
        emailTextField.setText("");
        isAdminCheckBox.setSelected(false);
    }



}
