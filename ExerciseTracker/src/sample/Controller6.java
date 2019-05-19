package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller6 implements Initializable { //EXERCISE PAGE

    private int selectedRecipe = 0;
    private String recipeDisplay;

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

        recipeSelector.setText(String.valueOf(selectedRecipe));
        addToSelectedRecipeCheckBox.setSelected(false);
        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());
        updateDisplay();
        listViewDisplay();

    }
    @FXML
    Button btnGoToAdminPage;

    @FXML
    TextField titleTextField;

    @FXML
    TextArea descriptionTextArea;
    @FXML
    Label lblActiveName;

    @FXML
    TextArea recipesTextArea;

    @FXML
    TextField recipeSelector;

    @FXML
    CheckBox addToSelectedRecipeCheckBox;
    @FXML
    public ListView<String> listView;

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
            Exercise exer = new Exercise(titleTextField.getText(),
                    descriptionTextArea.getText(), DataHolder.userList.indexOf(DataHolder.supervisedUser));
            DataHolder.supervisedUser.recipeList.get(selectedRecipe).exerciseList.add(exer);
            System.out.println("The exercise is considered added to a recipe");
            recipeDisplay();
        } else {
            addTheExercise(); //I made it that way for simplicity.
            System.out.println("The exercise is considered added and checked as completed");
            Main.getInstance().setScene(Main.Scene3);
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
        recipeDisplay();

    }

    @FXML
    public void manageRecipes(ActionEvent event)throws Exception{
        System.out.println("going to manage the Recipes on the recipe page");
        Main.getInstance().setScene(Main.Scene8);

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
        Main.getInstance().setScene(Main.Scene10);

    }

    public void addTheExercise(){
        Exercise exer = new Exercise(titleTextField.getText(),
                descriptionTextArea.getText(), DataHolder.userList.indexOf(DataHolder.supervisedUser));
        DataHolder.supervisedUser.exerciseList.add(exer);
    }
    @FXML
    public void somethingIsSelectedOnListView(MouseEvent event){
        selectedRecipe = listView.getSelectionModel().getSelectedIndex();
        recipeSelector.setText(String.valueOf(selectedRecipe));
    }

    public void updateDisplay() {
        int count = 0;
        recipeDisplay = "";
        for (Recipe x : DataHolder.supervisedUser.recipeList) {
            recipeDisplay += count + ") " + x.getTitle() + "\n";
            count++;
        }
        recipesTextArea.setText(recipeDisplay);
    }

    public void recipeDisplay() {
        int count = 0;
        String inRecipeDisplay = "";
        System.out.println(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getTitle());
        for (Exercise x : DataHolder.supervisedUser.recipeList.get(selectedRecipe).exerciseList) {
            inRecipeDisplay += count + ") " + x.getTitle() + "\n";
            count++;
        }
        System.out.println(inRecipeDisplay);
    }
    public void listViewDisplay(){
        listView.getItems().clear();
        for (Recipe x : DataHolder.supervisedUser.recipeList){
            listView.getItems().add(x.getTitle());
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }




}
