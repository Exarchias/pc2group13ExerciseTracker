package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller11 implements Initializable { //EXERCISE PAGE

    private int selectedRecipe = 0;
    private int selectedRecipeIN;
    private int selectedRecipeOUT;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!DataHolder.isLogin()) {
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selectedRecipe = DataHolder.supervisedRecipePostion;
        selectedRecipeIN = selectedRecipe;
        System.out.println("The value of the selectedRecipe is" + selectedRecipe);
        //recipeSelector.setText(String.valueOf(selectedRecipe + 1));
        //addToSelectedRecipeCheckBox.setSelected(false);
        titleTextField.setText(DataHolder.supervisedExercise.getTitle());
        descriptionTextArea.setText(DataHolder.supervisedExercise.getDescription());
        listViewDisplay();

        if (DataHolder.isAdmin())
            btnGoToAdminPage.setVisible(true);
        else btnGoToAdminPage.setVisible(false);
        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());
        updateDisplay();
    }

    @FXML
    Label lblActiveName;
    @FXML
    Button btnGoToAdminPage;

    @FXML
    TextField titleTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    TextArea recipesTextArea;

    //@FXML
    //TextField recipeSelector;

    @FXML
    CheckBox addToSelectedRecipeCheckBox;

    @FXML
    public ListView<String> listView;

    @FXML
    public void buttonLogOutPressed(ActionEvent event) throws Exception {
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    @FXML
    public void buttonGoToAdminPagePressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene2);

    }

    @FXML
    public void goToUserPage(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene3);

    }

    @FXML
    public void editExercise(ActionEvent event) throws Exception {
        System.out.println("The title of the exercise is: " + titleTextField.getText());
        System.out.println("The description of the exercise is: " + descriptionTextArea.getText());
//        if(addToSelectedRecipeCheckBox.isSelected()){
//            selectedRecipe = Integer.parseInt(recipeSelector.getText());
//            System.out.println("The exercise is added to the recipe: " + selectedRecipe);
//            //the code below is purposely not displaced by the addTheExercise() method.
//            //it will be necessary to be so, in order to be added to the recipe.
//            System.out.println("The exercise is considered added to a recipe");
//        } else {
//            editTheExercise(); //I made it that way for simplicity.
//            System.out.println("The exercise is considered added and checked as completed");
//            Main.getInstance().setScene(Main.Scene3);
//        }
        editTheExercise(); //I made it that way for simplicity.
        System.out.println("The exercise is considered added and checked as completed");
        Main.getInstance().setScene(Main.Scene3);

    }

//    @FXML
//    public void cleanFields(ActionEvent event){
//        titleTextField.setText("");
//        descriptionTextArea.setText("");
//        addToSelectedRecipeCheckBox.setSelected(false);
//        System.out.println("Cleaning the fields");
//
//    }

    @FXML
    public void testViewSelectedRecipes(ActionEvent event) {
        System.out.println("Viewing the recipes...");

    }

    @FXML
    public void manageRecipes(ActionEvent event) throws Exception {
        System.out.println("going to manage the Recipes on the recipe page");
        Main.getInstance().setScene(Main.Scene8);

    }

    @FXML
    public void editTheRecipe(ActionEvent event) throws Exception {
        //selectedRecipe = Integer.parseInt(recipeSelector.getText());
        System.out.println("going to edit the recipe:" + selectedRecipe);
        //Main.getInstance().setScene(Main.Scene9);

    }

    @FXML
    public void addaNewRecipe(ActionEvent event) throws Exception {
        System.out.println("Adding a new recipe");
        //Main.getInstance().setScene(Main.Scene10);

    }

    public void editTheExercise() {
        selectedRecipeOUT = selectedRecipe;
        if (selectedRecipeOUT == 0) {
            if (selectedRecipeIN == 0) {
                Exercise exex1 = new Exercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner());
                DataHolder.supervisedUser.exerciseList.remove(DataHolder.supervisedExercisePostion);
                DataHolder.supervisedUser.exerciseList.add(DataHolder.supervisedExercisePostion, exex1);

                //TESTING ADDING A USER ON THE DATA BASE(START)
                DB db = new DB();
                db.editOneExercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.
                        getOwner(), DataHolder.supervisedExercise.getExerciseID());
                db = null; //cuts the connection
                //TESTING ADDING A USER ON THE DATA BASE(END)
            } else {
                Exercise exex2 = new Exercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner());
                DataHolder.supervisedUser.exerciseList.add(exex2);


                //TESTING ADDING A USER ON THE DATA BASE(START)
                DB db = new DB();
                db.editOneExercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner(), DataHolder.supervisedExercise.getExerciseID());
                db.exerciseToRecipe(DataHolder.supervisedExercise.getExerciseID(), 0);
                db = null; //cuts the connection
                //TESTING ADDING A USER ON THE DATA BASE(END)
            }
        } else {
            if (selectedRecipeIN == 0) {
                Exercise exex = new Exercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner());
                DataHolder.supervisedUser.recipeList.get(selectedRecipeOUT - 1).exerciseList.add(exex);

                //TESTING ADDING A USER ON THE DATA BASE(START)
                DB db = new DB();
                db.editOneExercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner(), DataHolder.supervisedExercise.getExerciseID());
                db.exerciseToRecipe(DataHolder.supervisedExercise.getExerciseID(), DataHolder.supervisedUser.recipeList.
                        get(selectedRecipeOUT - 1).getRecipeID());
                db = null; //cuts the connection
                //TESTING ADDING A USER ON THE DATA BASE(END)
            } else {
                Exercise exOld = new Exercise(DataHolder.supervisedUser.recipeList.get(selectedRecipeIN).exerciseList.get(DataHolder.
                        supervisedExercisePostion).getTitle(), DataHolder.supervisedUser.recipeList.get(selectedRecipeIN).exerciseList.get(DataHolder.
                        supervisedExercisePostion).getDescription(), DataHolder.supervisedUser.recipeList.get(selectedRecipeIN).exerciseList.get(DataHolder.
                        supervisedExercisePostion).getOwner());
                DataHolder.supervisedUser.recipeList.get(selectedRecipeIN).exerciseList.remove(DataHolder.supervisedExercisePostion);
                Exercise exnew = new Exercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner());
                DataHolder.supervisedUser.recipeList.get(selectedRecipeOUT - 1).exerciseList.add(exnew);

                //TESTING ADDING A USER ON THE DATA BASE(START)
                DB db = new DB();
                db.editOneExercise(titleTextField.getText(), descriptionTextArea.getText(), DataHolder.supervisedExercise.getOwner(), DataHolder.supervisedExercise.getExerciseID());
                db.exerciseToRecipe(DataHolder.supervisedExercise.getExerciseID(), DataHolder.supervisedUser.
                        recipeList.get(selectedRecipeOUT - 1).getRecipeID());
                db = null; //cuts the connection
                //TESTING ADDING A USER ON THE DATA BASE(END)
            }
        }

        DataHolder.supervisedRecipePostion = 0;
        //DataHolder.activeUser.exerciseList.add(exer);
    }

    public void listViewDisplay() {
        listView.getItems().clear();
        listView.getItems().add(DataHolder.supervisedUser.getUserName() + " history.");
        for (Recipe x : DataHolder.supervisedUser.recipeList) {
            //listView.getItems().add(x.getTitle());
            listView.getItems().add(Methods.oneRecipeOneLine(x));
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void somethingIsSelectedOnListViewRight(MouseEvent event) {
        selectedRecipe = listView.getSelectionModel().getSelectedIndex();
        //recipeSelector.setText(String.valueOf(selectedRecipe));
        updateDisplay();
    }

    public void updateDisplay() {
        String recipeDisplay;
        if ((selectedRecipe) <= 0) {
            recipeDisplay = Methods.displayExercisesofTheUser(DataHolder.supervisedUser);
        } else {
            recipeDisplay = Methods.displayTherecipe(DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1));
        }
        recipesTextArea.setText(recipeDisplay);
    }


}
