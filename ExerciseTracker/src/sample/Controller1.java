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
        DataHolder.setAdmin(false);

    }

    @FXML
    public void btnAdminPressed(ActionEvent event) throws Exception{
        DataHolder.setAdmin(true);
        DataHolder.activeUser = DataHolder.userList.get(0);
        System.out.println("Now you are Credible for Admin");


    }
    @FXML
    public void btnUserPressed(ActionEvent event)throws Exception{
        DataHolder.setAdmin(false);
        DataHolder.activeUser = DataHolder.userList.get(1);
        System.out.println("Now you are NOT Credible for Admin");

    }

    @FXML
    public void btnLoginIsTrue(ActionEvent event)throws Exception{
        DataHolder.setLogin(true);
        System.out.println("Now you are Credible to Log In");

    }

    @FXML
    public void btnLoginIsFalse(ActionEvent event)throws Exception{
        DataHolder.setLogin(false);
        System.out.println("Now your credentials are invalid");
    }

    @FXML
    public void btnLogin(ActionEvent event)throws Exception{
        for (User x: DataHolder.userList){
            if((txtUserName.getText().equalsIgnoreCase(x.getUserName())) &&
                    (txtPassword.getText().equalsIgnoreCase(x.getPassWord()))){
                DataHolder.setLogin(true);
                DataHolder.activeUser = x;
                DataHolder.setActiveName(DataHolder.activeUser.getUserName());
                if(DataHolder.activeUser.isAnAdmin()){
                    DataHolder.setAdmin(true);
                }
            }
        }


        if(DataHolder.isLogin()){
            System.out.println("You are logged as " + DataHolder.activeUser.getUserName());
            if(DataHolder.isAdmin()){
                Main.getInstance().setScene(Main.Scene2);
            } else {
                Main.getInstance().setScene(Main.Scene3);
            }
        } else {
            System.out.println("Access denied!");
        }

    }




}
