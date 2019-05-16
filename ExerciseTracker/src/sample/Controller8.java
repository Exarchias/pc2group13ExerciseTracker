package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller8 implements Initializable {  //MANAGE RECIPES PAGE

    private int selectedRecipe = 0;
    private String recipeDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!DataHolder.isLogin()) {
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());
        recipeSelector.setText(String.valueOf(selectedRecipe));

        recipeDisplay();
        listViewRecipeDisplay();

        if (DataHolder.isAdmin())
            btnGoToTheAdminPage.setVisible(true);
        else btnGoToTheAdminPage.setVisible(false);


    }


    @FXML
    Label lblActiveName;
    @FXML
    TextField recipeSelector;

    @FXML
    TextArea recipesTextArea;

    @FXML
    Button btnGoToTheAdminPage;
    @FXML
    ListView<String> listView;

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
    public void testViewRecipes(ActionEvent event){
        System.out.println("Presenting the recipes...");
        recipeDisplay();
        System.out.println(recipeDisplay);
    }

    @FXML
    public void addRecipe(ActionEvent event) throws Exception{
        System.out.println("adding an exercise to the list");
        Main.getInstance().setScene(Main.Scene10);
    }

    @FXML
    public void deleteRecipe(ActionEvent event){
        selectedRecipe = Integer.parseInt(recipeSelector.getText());
        System.out.println("Deleting the exercise " +
                DataHolder.activeUser.recipeList.get(selectedRecipe).getTitle() + " from the list");
        DataHolder.activeUser.recipeList.remove(selectedRecipe);
        recipeDisplay();

    }

    @FXML
    public void editRecipe(ActionEvent event) throws Exception{
        selectedRecipe = Integer.parseInt(recipeSelector.getText());
        System.out.println("editing the exercise" +
                DataHolder.activeUser.recipeList.get(selectedRecipe).getTitle() + " from the list");
        DataHolder.supervisedRecipe = DataHolder.activeUser.recipeList.get(selectedRecipe);
        DataHolder.setSupervisedExercisePostion(selectedRecipe);
        Main.getInstance().setScene(Main.Scene9);
    }
    public void somethingIsSelectedOnListView(MouseEvent event){
        selectedRecipe = listView.getSelectionModel().getSelectedIndex();
        recipeSelector.setText(String.valueOf(selectedRecipe));
    }

    public void recipeDisplay() {
        int count = 0;
        recipeDisplay = "";
        for (Recipe x : DataHolder.activeUser.recipeList) {
            recipeDisplay += count + ") " + x.getTitle() + "\n";
            count++;
        }
        recipesTextArea.setText(recipeDisplay);

    }
    public void listViewRecipeDisplay(){
     listView.getItems().clear();
     for (Recipe x : DataHolder.activeUser.recipeList){
         listView.getItems().add(x.getTitle());
     }
     listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }




}
