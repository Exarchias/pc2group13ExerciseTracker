package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller8 implements Initializable {  //MANAGE RECIPES PAGE

    private int selectedRecipe;
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
        //recipeSelector.setText(String.valueOf(selectedRecipe));

        updateDisplay();

        if (DataHolder.isAdmin())
            btnGoToTheAdminPage.setVisible(true);
        else btnGoToTheAdminPage.setVisible(false);


    }


    @FXML
    Label lblActiveName;

    //@FXML
    //TextField recipeSelector;

    @FXML
    TextArea recipesTextArea;

    @FXML
    Button btnGoToTheAdminPage;

    @FXML
    Button publishBtn;

    @FXML
    ListView<String> listView;

    @FXML
    public void buttonLogOutPressed(ActionEvent event) throws Exception {
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToTheAdminPagePressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene2);

    }

    @FXML
    public void buttonGoToTheUserPagePressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene3);

    }

    @FXML
    public void buttonGoToTheSettingPage(ActionEvent event) throws Exception {
//        DataHolder.supervisedUser = DataHolder.activeUser;
//        System.out.println("Supervised user is: " + DataHolder.supervisedUser.getUserName());
        Main.getInstance().setScene(Main.Scene4);
    }

//    @FXML
//    public void testViewRecipes(ActionEvent event){
//        System.out.println("Presenting the recipes...");
//        recipeDisplay();
//        System.out.println(recipeDisplay);
//        updateDisplay();
//    }

    @FXML
    public void addRecipe(ActionEvent event) throws Exception {
        System.out.println("adding an exercise to the list");
        Main.getInstance().setScene(Main.Scene10);
    }

    @FXML
    public void deleteRecipe(ActionEvent event) {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            //selectedRecipe = Integer.parseInt(recipeSelector.getText());
            System.out.println("Deleting the exercise " +
                    DataHolder.supervisedUser.recipeList.get(selectedRecipe).getTitle() + " from the list");
            //TESTING DELETING A RECIPE ON THE DATA BASE(START)
            DB db = new DB();
            db.deleteAllTheExercisesOfTheRecipe(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID());
            db.deleteOneRecipe(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID());
            db = null; //cuts the connection
            //TESTING DELETING A RECIPE ON THE DATA BASE(END)
            DataHolder.supervisedUser.recipeList.remove(selectedRecipe);
            updateDisplay();
        }
    }

    @FXML
    public void editRecipe(ActionEvent event) throws Exception {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            //selectedRecipe = Integer.parseInt(recipeSelector.getText());
            System.out.println("editing the exercise" +
                    DataHolder.supervisedUser.recipeList.get(selectedRecipe).getTitle() + " from the list");
            DataHolder.supervisedRecipe = DataHolder.supervisedUser.recipeList.get(selectedRecipe);
            DataHolder.setSupervisedExercisePostion(selectedRecipe);
            Main.getInstance().setScene(Main.Scene9);
        }
    }

    public void somethingIsSelectedOnListView(MouseEvent event) {
        selectedRecipe = listView.getSelectionModel().getSelectedIndex();
        //recipeSelector.setText(String.valueOf(selectedRecipe));
        updateDisplay();
    }

    //displays the information about the selected Recipe
    public void recipeDisplay() {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            recipeDisplay = Methods.displayTherecipe(DataHolder.supervisedUser.recipeList.get(selectedRecipe));
            recipesTextArea.setText(recipeDisplay);
        } else {
            recipeDisplay = "";
            recipesTextArea.setText(recipeDisplay);
        }
    }

    public void listViewRecipeDisplay() {
        listView.getItems().clear();
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            for (Recipe x : DataHolder.supervisedUser.recipeList) {
                listView.getItems().add(x.getTitle() + " " + Methods.displayIfRecipeIsPublic(x));
            }
        } else {
            listView.getItems().add("");
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void updateDisplay() {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            if (DataHolder.supervisedUser.recipeList.get(selectedRecipe).isPublic()) {
                publishBtn.setText("Unpublish Recipe");
            } else {
                publishBtn.setText("Publish Recipe");
            }
            recipeDisplay();
            listViewRecipeDisplay();
            for (Recipe x : DataHolder.publicRecipes) {
                System.out.println(x.getTitle());
            }
            System.out.println("End of the updatedisplay statements");
        }
    }

    @FXML
    public void publishRecipe(ActionEvent event) throws Exception {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            if (DataHolder.supervisedUser.recipeList.get(selectedRecipe).isPublic()) {
                DataHolder.supervisedUser.recipeList.get(selectedRecipe).setPublic(false);
                //TESTING MAKING A RECIPE PUBLIC IN A DATA BASE (START)
                DB db = new DB();
                db.recipeIsPublic(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID(), false);
                db = null; //cuts the connection
                //TESTING MAKING A RECIPE PUBLIC IN A DATA BASE (END)
                DataHolder.unpublishRecipes();
                publishBtn.setText("Publish Recipe");
            } else {
                DataHolder.supervisedUser.recipeList.get(selectedRecipe).setPublic(true);
                DataHolder.publicRecipes.add(DataHolder.supervisedUser.recipeList.get(selectedRecipe));
                //TESTING MAKING A RECIPE PUBLIC IN A DATA BASE (START)
                DB db = new DB();
                db.recipeIsPublic(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID(), true);
                db = null; //cuts the connection
                //TESTING MAKING A RECIPE PUBLIC IN A DATA BASE (END)
                publishBtn.setText("Unublish Recipe");
            }
        }
    }

    @FXML
    public void browseRecipesButtonPressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene12);

    }

    @FXML
    public void getPdf(ActionEvent event) throws Exception {
        PDF.pdfRecipelist(DataHolder.supervisedUser);
    }


}
