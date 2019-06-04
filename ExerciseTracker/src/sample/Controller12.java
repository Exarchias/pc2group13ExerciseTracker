package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller12 implements Initializable {
    private int selectedRecipe;
    private int selectedExercise;

    @FXML
    private Label lblActiveName;
    @FXML
    private Button btnGoToTheAdminPage;

    @FXML
    private TextArea exerciseTextArea;

    @FXML
    private TextArea recipeTextArea;

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
       listViewDisplay();
        updateDisplay();
    }

    public void logOutButtonPressed(ActionEvent event) throws Exception{
        DataHolder.setLogin(false);
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are Logged out");

    }

    public void someThingIsSelecteOnListView2(MouseEvent event) throws Exception{
      selectedRecipe = listView2.getSelectionModel().getSelectedIndex();
      listViewDisplay();
        updateDisplay();
    }

    public void someThingIsSelecteOnListView(MouseEvent event) throws Exception{
        selectedExercise = listView.getSelectionModel().getSelectedIndex();
        updateDisplay();
    }

    public void goToTheAdminPageButtonPressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene2);
    }

    public void goToTheUserPageButtonPressed(ActionEvent event)throws Exception{
        Main.getInstance().setScene(Main.Scene3);
    }

    public void useExerciseButtonPressed(ActionEvent event) throws Exception{
        Exercise exer = DataHolder.publicRecipes.get(selectedRecipe).exerciseList.get(selectedExercise);
        //String title, String description, int owner
        Exercise exer2 = new Exercise(exer.getTitle(), exer.getDescription(), DataHolder.supervisedUser.getUserID());
        //TESTING ADDING AN EXERCISE ON THE DATA BASE(START)
        DB db = new DB();
        db.addOneExercise(exer2.getTitle(), exer2.getDescription(), DataHolder.supervisedUser.getUserID());
        int exeID = db.loadLastExerciseId();
        System.out.println("Last Exercise ID is: " + exeID);
        exer2.setExerciseID(exeID);
        //TESTING ADDING AN EXERCISE ON THE DATA BASE(END)
        DataHolder.supervisedUser.exerciseList.add(exer2);
    }

    public void useRecipeButtonPressed(ActionEvent event)throws Exception{
        Recipe recip = DataHolder.publicRecipes.get(selectedRecipe);
        //String title, String description, int owner
        Recipe recip2 = new Recipe(recip.getTitle(), recip.getDescription(), DataHolder.supervisedUser.getUserID());
        for (Exercise x : DataHolder.publicRecipes.get(selectedRecipe).exerciseList){
            recip2.exerciseList.add(x);
        }
        //TESTING ADDING A RECIPE ON THE DATA BASE(START)
        DB db = new DB();
        db.addOneRecipe(recip2.getTitle(), recip2.getDescription(), DataHolder.supervisedUser.getUserID(), false);
        int recID = db.loadLastRecipeId();
        System.out.println("The ID of the last used public recipe is: " + recID);
        //TESTING ADDING A RECIPE ON THE DATA BASE(END)
        recip2.setRecipeID(recID);

        for (Exercise x : recip2.exerciseList){
            x.setRecipeID(recID);
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(START)
            db.addOneExercise(x.getTitle(), x.getDescription(), DataHolder.supervisedUser.getUserID());
            int exeID = db.loadLastExerciseId();
            x.setExerciseID(exeID);
            db.exerciseToRecipe(exeID, recID);
            System.out.println("Last Exercise ID is: " + x.getExerciseID());
            System.out.println("Last Recipe ID is: " + x.getRecipeID());

            //TESTING ADDING AN EXERCISE ON THE DATA BASE(END)
        }
        DataHolder.supervisedUser.recipeList.add(recip2);
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
            //listView2.getItems().add(String.valueOf(x.getTitle()) + " / " + Methods.returnRecipeOwner(x));
            listView2.getItems().add(Methods.oneRecipeOneLine(x));

        }
        listView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void listViewDisplay(){
        listView.getItems().clear();
        for (Exercise x : DataHolder.publicRecipes.get(selectedRecipe).exerciseList){
            //listView.getItems().add(x.getTitle() + " / " + Methods.returnExerciseOwner(x));
            listView.getItems().add(Methods.oneExerciseOneLine(x));
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void updateDisplay(){
        exerciseTextArea.setText(Methods.displayTheExercise(DataHolder.publicRecipes.get(selectedRecipe).exerciseList.
                get(selectedExercise)));
        recipeTextArea.setText(Methods.displayTherecipe(DataHolder.publicRecipes.get(selectedRecipe)));
    }





}
