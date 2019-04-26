package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {  //USER PAGE

    private int selectedExercise = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()){
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        exerciseSelector.setText(String.valueOf(selectedExercise));

    }

    @FXML
    TextField exerciseSelector;

    @FXML
    TextArea exerciseListTextArea;

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
    public void buttonGoToTheSettingPage(ActionEvent event) throws Exception{
        DataHolder.supervisedUser = DataHolder.activeUser;
        System.out.println("Supervised user is: " + DataHolder.supervisedUser.getUserName());
        Main.getInstance().setScene(Main.Scene4);
    }

    @FXML
    public void testViewExercises(ActionEvent event){
        System.out.println("Presenting the exercises...");
    }

    @FXML
    public void addExercise(ActionEvent event) throws Exception{
        System.out.println("adding an exercise to the list");
        Main.getInstance().setScene(Main.Scene6);
    }

    @FXML
    public void deleteExercise(ActionEvent event){
        selectedExercise = Integer.parseInt(exerciseSelector.getText());
        System.out.println("Deleting the exercise " + selectedExercise + " from the list");

    }

    @FXML
    public void editExercise(ActionEvent event) throws Exception{
        selectedExercise = Integer.parseInt(exerciseSelector.getText());
        System.out.println("editing the exercise" + selectedExercise + " from the list");
        Main.getInstance().setScene(Main.Scene4);
    }


}
