package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller12 implements Initializable {
    private int selectedRecipe;

    @FXML
    private Label lblActiveName;
    @FXML
    private Button btnGoToTheAdminPage;

    @FXML
    private Button btnGoToTheUserPage;
    @FXML
    public ListView<String> listView;

    @FXML
    public ListView<String> listView2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        

        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());
        if (DataHolder.isAdmin())
            btnGoToTheAdminPage.setVisible(true);
        else btnGoToTheAdminPage.setVisible(false);


       listView2Display();

    }

    public void logOutButtonPressed(ActionEvent event) throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are Logged out");

    }

    public void someThingIsSelecteOnListView2(MouseEvent event) throws Exception{
      selectedRecipe = listView2.getSelectionModel().getSelectedIndex();
      listView.getItems().add(String.valueOf(selectedRecipe));


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
        Main.getInstance().setScene(Main.Scene3);
    }
    public void manageRecipesButtonPressed(ActionEvent event) throws Exception{
        Main.getInstance().setScene(Main.Scene8);

    }

    public void listView2Display(){
        listView2.getItems().clear();
        for (Recipe x : DataHolder.publicRecipes){
            listView2.getItems().add(String.valueOf(x.getTitle()));

        }
        listView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }





}
