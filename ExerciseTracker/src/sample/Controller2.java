package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller2 implements Initializable { // ADMIN PAGE
    private int selectedUser = 0;
    private String userListDisplay = "";


    @FXML
    private AnchorPane testMonitor;

   @FXML
    public ListView<String> listView;

    @FXML
    Label lblActiveName;

    @FXML
    TextField userSelector;

    @FXML
    TextArea userListTextArea;

    @FXML
    Button btnLogOut;

    @FXML
    private Button btnViewUser;

    @FXML
    private Button btnAddNewUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnEditUser;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()) {
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        DataHolder.supervisedUser = DataHolder.activeUser;
      testMonitor.getStylesheets().add("anchorpane-loginscene");
        btnLogOut.getStyleClass().add("button-logout");
        btnAddNewUser.getStyleClass().add("button-crud");
        btnDeleteUser.getStyleClass().add("button-crud");
        btnEditUser.getStyleClass().add("button-crud");
        btnViewUser.getStyleClass().add("button-crud");


        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());

        userSelector.setText(String.valueOf(selectedUser));
        updateDisplay(); //TEXTAREAMETHOD
        listViewDisplay();//LISTVIEWMETHOD

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
        updateDisplay();
        listViewDisplay();//LISTVIEWMETHOD
        System.out.println(userListDisplay);
    }

    @FXML
    public void addNewUser(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene5);
        System.out.println("Adding some user to the system");
    }

    @FXML
    public void deleteUser(ActionEvent event){

        selectedUser = Integer.parseInt(userSelector.getText());
        System.out.println("Removing the user " + DataHolder.userList.get(selectedUser).getUserName()
                + "from the system");
        DataHolder.userList.remove(selectedUser);
        updateDisplay();
        listViewDisplay();//LISTVIEWMETHOD
    }

    @FXML
    public void editUser(ActionEvent event)throws Exception{
        selectedUser = Integer.parseInt(userSelector.getText());
        System.out.println("Editing the user " +
                DataHolder.userList.get(selectedUser).getUserName() + "from the system");
        DataHolder.supervisedUser = DataHolder.userList.get(selectedUser);
        Main.getInstance().setScene(Main.Scene7);
    }

    @FXML
    public void somethingIsSelectedOnListView(MouseEvent event)throws Exception{
        selectedUser = listView.getSelectionModel().getSelectedIndex();
        userSelector.setText(String.valueOf(selectedUser));
    }


    public void updateDisplay() {
        int count = 0;
        userListDisplay = "";
        for (User x : DataHolder.userList) {
            userListDisplay += count + ") " + x.getUserName() + "\n";
            count++;
        }
        userListTextArea.setText(userListDisplay);


    }
        public void listViewDisplay(){
        listView.getItems().clear();
        for (User x : DataHolder.userList){
           listView.getItems().add(x.getUserName());
        }

      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



    }









}
