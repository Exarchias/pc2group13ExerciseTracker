package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller9 implements Initializable { //EXERCISE PAGE

    private int selectedRecipe = 0;

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
        //addToSelectedRecipeCheckBox.setSelected(false);
        lblActiveName.setText("You are logged in as: " + DataHolder.activeUser.getUserName());
        titleTextField.setText(DataHolder.supervisedRecipe.getTitle());
        descriptionTextArea.setText(DataHolder.supervisedRecipe.getDescription());
        recipeIsPublic.setSelected(DataHolder.supervisedRecipe.isPublic());

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
    CheckBox recipeIsPublic;

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
    public void editRecipe(ActionEvent event) throws Exception {
        System.out.println("The title of the exercise is: " + titleTextField.getText());
        System.out.println("The description of the exercise is: " + descriptionTextArea.getText());
        editTheRecipe(); //I made it that way for simplicity.
        System.out.println("The exercise is considered added and checked as completed");
        Main.getInstance().setScene(Main.Scene8);


    }

    @FXML
    public void cleanFields(ActionEvent event) {
        titleTextField.setText("");
        descriptionTextArea.setText("");
        recipeIsPublic.setSelected(false);
        System.out.println("Cleaning the fields");

    }

    @FXML
    public void testViewSelectedRecipes(ActionEvent event) {
        System.out.println("Viewing the recipes...");

    }


    public void editTheRecipe() {
        DataHolder.supervisedRecipe.setTitle(titleTextField.getText());
        DataHolder.supervisedRecipe.setDescription(descriptionTextArea.getText());
        DataHolder.supervisedRecipe.setPublic(recipeIsPublic.isSelected());
        //TESTING EDITING A RECIPE ON THE DATA BASE(START)
        DB db = new DB();
        db.editOneRecipe(titleTextField.getText(),
                descriptionTextArea.getText(), DataHolder.supervisedUser.getUserID(), DataHolder.supervisedRecipe.isPublic(),
                DataHolder.supervisedRecipe.getRecipeID());
        db = null; //cuts the connection
        //TESTING EDITING A RECIPE ON THE DATA BASE(END)
        if (recipeIsPublic.isSelected()) {
            DataHolder.publicRecipes.add(DataHolder.supervisedRecipe);
        } else {
            DataHolder.publicRecipes.remove(DataHolder.supervisedRecipe);
        }
    }


}
