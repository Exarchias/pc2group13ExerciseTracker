package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller6 implements Initializable { //EXERCISE PAGE

    private int selectedRecipe = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!DataHolder.isLogin()){
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        recipeSelector.setText(String.valueOf(selectedRecipe));
        addToSelectedRecipeCheckBox.setSelected(false);

    }

    @FXML
    TextField titleTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    TextArea recipesTextArea;

    @FXML
    TextField recipeSelector;

    @FXML
    CheckBox addToSelectedRecipeCheckBox;

    @FXML
    public void buttonLogOutPressed(ActionEvent event)throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToAdminPagePressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);

    }

    @FXML
    public void goToUserPage(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);

    }

    @FXML
    public void addExercise(ActionEvent event)throws Exception{
        System.out.println("The title of the exercise is: " + titleTextField.getText());
        System.out.println("The description of the exercise is: " + descriptionTextArea.getText());
        if(addToSelectedRecipeCheckBox.isSelected()){
            selectedRecipe = Integer.parseInt(recipeSelector.getText());
            System.out.println("The exercise is added to the recipe: " + selectedRecipe);
            System.out.println("The exercise is considered added");
        } else {
            System.out.println("The exercise is considered added");
            Main.getInstance().setScene(Main.Scene2);
        }

    }

    @FXML
    public void cleanFields(ActionEvent event){
        titleTextField.setText("");
        descriptionTextArea.setText("");
        addToSelectedRecipeCheckBox.setSelected(false);
        System.out.println("Cleaning the fields");

    }

    @FXML
    public void testViewSelectedRecipes(ActionEvent event){
        System.out.println("Viewing the recipes...");

    }

    @FXML
    public void manageRecipes(ActionEvent event)throws Exception{
        System.out.println("going to manage the Recipes on the recipe page");
        //Main.getInstance().setScene(Main.Scene8);

    }

    @FXML
    public void editTheRecipe(ActionEvent event)throws Exception{
        selectedRecipe = Integer.parseInt(recipeSelector.getText());
        System.out.println("going to edit the recipe:" + selectedRecipe);
        //Main.getInstance().setScene(Main.Scene9);

    }

    @FXML
    public void addaNewRecipe(ActionEvent event)throws Exception{
        System.out.println("Adding a new recipe");
        //Main.getInstance().setScene(Main.Scene10);

    }





}
