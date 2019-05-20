package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller12 implements Initializable {

    @FXML
    public ListView<?> exerciseListView;

    @FXML
    public ListView<?> recipesListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void logOutButtonPressed(ActionEvent event) throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are Logged out");

    }
    public void goToTheAdminPageButtonPressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }
    public void goToTheUserPageButtonPressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);
    }

    public void useExerciseButtonPressed(ActionEvent event) throws Exception{

    }
    public void useRecipeButtonPressed(ActionEvent event)throws Exception{

    }
    public void cancelButtonPressed(ActionEvent event)throws Exception{

    }
    public void manageRecipesButtonPressed(ActionEvent event) throws Exception{

    }
}
