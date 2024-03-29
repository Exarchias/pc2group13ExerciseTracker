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
        if (!DataHolder.isLogin()) {
            try {
                Methods.logOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (DataHolder.isAdmin())
            btnGoToAdminPage.setVisible(true);
        else btnGoToAdminPage.setVisible(false);

        //recipeSelector.setText(String.valueOf(selectedRecipe));
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
    public void addExercise(ActionEvent event) throws Exception {
        System.out.println("The title of the exercise is: " + titleTextField.getText());
        System.out.println("The description of the exercise is: " + descriptionTextArea.getText());
        if (addToSelectedRecipeCheckBox.isSelected()) {
            //selectedRecipe = Integer.parseInt(recipeSelector.getText());
            System.out.println("The exercise is added to the recipe: " + selectedRecipe);
            Exercise exer = new Exercise(titleTextField.getText(),
                    descriptionTextArea.getText(), DataHolder.userList.indexOf(DataHolder.supervisedUser));
            DataHolder.supervisedUser.recipeList.get(selectedRecipe).exerciseList.add(exer);
            System.out.println("The exercise is considered added to a recipe");
            exer.setOwner(DataHolder.supervisedUser.getUserID());
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(START)
            DB db = new DB();
            db.addOneExercise(exer.getTitle(), exer.getDescription(), exer.getOwner());
            int exeID = db.loadLastExerciseId();
            System.out.println("Last Exercise ID is: " + exeID);
            System.out.println("Selected Recipe ID is: " + DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID());
            db.exerciseToRecipe(exeID, DataHolder.supervisedUser.recipeList.get(selectedRecipe).getRecipeID());
            db = null; //cuts the connection
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(END)
            recipeDisplay();
        } else {
            addTheExercise(); //I made it that way for simplicity.
            System.out.println("The exercise is considered added and checked as completed");
            Main.getInstance().setScene(Main.Scene3);
        }

    }

    @FXML
    public void browseRecipesButtonPressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene12);

    }

    @FXML
    public void cleanFields(ActionEvent event) {
        titleTextField.setText("");
        descriptionTextArea.setText("");
        addToSelectedRecipeCheckBox.setSelected(false);
        System.out.println("Cleaning the fields");

    }

    @FXML
    public void testViewSelectedRecipes(ActionEvent event) {
        System.out.println("Viewing the recipes...");
        recipeDisplay();

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
        Main.getInstance().setScene(Main.Scene10);

    }

    public void addTheExercise() {
        Exercise exer = new Exercise(titleTextField.getText(),
                descriptionTextArea.getText(), DataHolder.userList.indexOf(DataHolder.supervisedUser));
        exer.setOwner(DataHolder.supervisedUser.getUserID());
        DataHolder.supervisedUser.exerciseList.add(exer);
        //TESTING ADDING A USER ON THE DATA BASE(START)
        DB db = new DB();
        db.addOneExercise(exer.getTitle(), exer.getDescription(), exer.getOwner());
        System.out.println("The last users ID should be " + db.loadLastUserId());
        db = null; //cuts the connection
        //TESTING ADDING A USER ON THE DATA BASE(END)
    }

    @FXML
    public void somethingIsSelectedOnListView(MouseEvent event) {
        selectedRecipe = listView.getSelectionModel().getSelectedIndex();
        //recipeSelector.setText(String.valueOf(selectedRecipe));
        updateDisplay();
    }

    public void updateDisplay() {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            recipeDisplay = Methods.displayTherecipe(DataHolder.supervisedUser.recipeList.get(selectedRecipe));
            recipesTextArea.setText(recipeDisplay);
        } else {
            recipeDisplay = "";
            recipesTextArea.setText(recipeDisplay);
        }
    }

    public void recipeDisplay() {
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            int count = 0;
            String inRecipeDisplay = "";
            System.out.println(DataHolder.supervisedUser.recipeList.get(selectedRecipe).getTitle());
            for (Exercise x : DataHolder.supervisedUser.recipeList.get(selectedRecipe).exerciseList) {
                inRecipeDisplay += count + ") " + x.getTitle() + "\n";
                count++;
            }
            System.out.println(inRecipeDisplay);
        }
    }

    public void listViewDisplay() {
        listView.getItems().clear();
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            for (Recipe x : DataHolder.supervisedUser.recipeList) {
                listView.getItems().add(x.getTitle() + " " + Methods.displayIfRecipeIsPublic(x));
                //listView.getItems().add(Methods.oneRecipeOneLine(x));
            }
        } else {
            listView.getItems().add("");
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //adding the push ups template to the exercise.
    @FXML
    public void useTemplate1(ActionEvent event) throws Exception {
        titleTextField.setText(DataHolder.templateList.get(0).getTitle());
        descriptionTextArea.setText(DataHolder.templateList.get(0).getDescription());
    }

    //adding the sit ups template to the exercise.
    @FXML
    public void useTemplate2(ActionEvent event) throws Exception {
        titleTextField.setText(DataHolder.templateList.get(1).getTitle());
        descriptionTextArea.setText(DataHolder.templateList.get(1).getDescription());
    }


    //adding the squats template to the exercise.
    @FXML
    public void useTemplate3(ActionEvent event) throws Exception {
        titleTextField.setText(DataHolder.templateList.get(2).getTitle());
        descriptionTextArea.setText(DataHolder.templateList.get(2).getDescription());
    }


}
