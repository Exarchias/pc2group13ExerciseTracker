package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {  //USER PAGE

    private int selectedExercise = 0;
    private String exerciseDisplay;
    private int selectedRecipe = 0;
    private Recipe recipeInFocus;

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
        superviseLabel.setText("You are supervising: " + DataHolder.supervisedUser.getUserName());
        //exerciseSelector.setText(String.valueOf(selectedExercise));

        listViewDisplay();
        updateDisplay();
        listViewDisplay2();
//        if(DataHolder.activeUser.exerciseList.get(0) != null){
//            updateDisplay();
//        }
        if (DataHolder.isAdmin()) {
            btnGoToTheAdminPage.setVisible(true);
        } else {
            btnGoToTheAdminPage.setVisible(false);
        }

//        if (DataHolder.isConnected){
//            saveBtn.setVisible(true);
//        } else {
//            saveBtn.setVisible(false);
//        }


    }

    @FXML
    public ListView<String> listView;
    @FXML
    Label lblActiveName;

    @FXML
    Label superviseLabel;

    //@FXML
    //TextField exerciseSelector;

    @FXML
    TextArea exerciseListTextArea;

    @FXML
    Button btnGoToTheAdminPage;

    @FXML
    Button useSelectedExerciseBtn;

    //@FXML
    //Button saveBtn;

    @FXML
    TextField recipeSelector;

    @FXML
    public ListView<String> listView2;

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
    public void buttonGoToTheSettingPage(ActionEvent event) throws Exception {
        //DataHolder.supervisedUser = DataHolder.activeUser;
        System.out.println("Supervised user is: " + DataHolder.supervisedUser.getUserName());
        Main.getInstance().setScene(Main.Scene4);
    }

    @FXML
    public void testViewExercises(ActionEvent event) {
        System.out.println("Presenting the exercises...");
        updateDisplay();
        System.out.println(exerciseDisplay);
    }

//    @FXML
//    public void saveTheData(ActionEvent event){
//        System.out.println("Save the data");
//    }

    @FXML
    public void addExercise(ActionEvent event) throws Exception {
        System.out.println("adding an exercise to the list");
//        Exercise exer = new Exercise("TestExercise " + DataHolder.testCount,
//                "Test Exercise description", DataHolder.userList.indexOf(DataHolder.activeUser));
//        DataHolder.testCount ++;
//        DataHolder.activeUser.exerciseList.add(exer);
//        updateDisplay();
        Main.getInstance().setScene(Main.Scene6);
    }

    @FXML
    public void deleteExercise(ActionEvent event) {
        if (selectedRecipe <= 0) {
            //selectedExercise = Integer.parseInt(exerciseSelector.getText());
            System.out.println("Deleting the exercise " +
                    DataHolder.supervisedUser.exerciseList.get(selectedExercise).getTitle() + " from the list");
            //TESTING DELETING AN EXERCISE ON THE DATA BASE(START)
            DB db = new DB();
            db.deleteOneExercise(DataHolder.supervisedUser.exerciseList.get(selectedExercise).getExerciseID());
            db = null; //cuts the connection
            //TESTING DELETING AN EXERCISE ON THE DATA BASE(END)
            DataHolder.supervisedUser.exerciseList.remove(selectedExercise);
        } else {
            //selectedExercise = Integer.parseInt(exerciseSelector.getText());
            System.out.println("Deleting the exercise " +
                    DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.get(selectedExercise).getTitle()
                    + " from the list");
            //TESTING DELETING AN EXERCISE ON THE DATA BASE(START)
            DB db = new DB();
            db.deleteOneExercise(DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).
                    exerciseList.get(selectedExercise).getExerciseID());
            db = null; //cuts the connection
            //TESTING DELETING AN EXERCISE ON THE DATA BASE(END)
            DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.remove(selectedExercise);
        }
        updateDisplay();
        listViewDisplay();

    }

    @FXML
    public void editExercise(ActionEvent event) throws Exception {
        //selectedExercise = Integer.parseInt(exerciseSelector.getText());
        if (selectedRecipe <= 0) {
            System.out.println("editing the exercise" +
                    DataHolder.supervisedUser.exerciseList.get(selectedExercise).getTitle() + " from the list");
            DataHolder.supervisedExercise = DataHolder.supervisedUser.exerciseList.get(selectedExercise);
            DataHolder.supervisedExercisePostion = selectedExercise;
            DataHolder.supervisedRecipePostion = 0;
        } else {
            System.out.println("editing the exercise" +
                    DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.
                            get(selectedExercise).getTitle() + " from the list");
            DataHolder.supervisedExercise = DataHolder.supervisedUser.recipeList.
                    get(selectedRecipe - 1).exerciseList.get(selectedExercise);
            DataHolder.supervisedExercisePostion = selectedExercise;
            DataHolder.supervisedRecipePostion = selectedRecipe - 1;
        }
        Main.getInstance().setScene(Main.Scene11);
    }

    @FXML
    public void useSelectedExercise(ActionEvent event) {
        if (selectedRecipe <= 0) {
            //selectedExercise = Integer.parseInt(exerciseSelector.getText());
            Exercise exer = DataHolder.supervisedUser.exerciseList.get(selectedExercise);
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(START)
            DB db = new DB();
            db.addOneExercise(exer.getTitle(), exer.getDescription(), exer.getOwner());
            int exeID = db.loadLastExerciseId();
            System.out.println("Last Exercise ID is: " + exeID);
            System.out.println("Selected Recipe ID is: " + exer.getRecipeID());
            //db.exerciseToRecipe(exeID, exer.getRecipeID());
            db = null; //cuts the connection
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(END)
            exer.setExerciseID(exeID);
            db.exerciseToRecipe(exeID, 0);
            DataHolder.supervisedUser.exerciseList.add(exer);
        } else {
            //selectedExercise = Integer.parseInt(exerciseSelector.getText());
            Exercise exer = DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.get(selectedExercise);
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(START)
            DB db = new DB();
            db.addOneExercise(exer.getTitle(), exer.getDescription(), exer.getOwner());
            int exeID = db.loadLastExerciseId();
            db.exerciseToRecipe(exeID, 0);
            System.out.println("Last Exercise ID is: " + exeID);
            System.out.println("Selected Recipe ID is: " + exer.getRecipeID());
            //db.exerciseToRecipe(exeID, exer.getRecipeID());
            db = null; //cuts the connection
            //TESTING ADDING AN EXERCISE ON THE DATA BASE(END)
            exer.setExerciseID(exeID);
            DataHolder.supervisedUser.exerciseList.add(exer);
        }
        updateDisplay();
        listViewDisplay();

    }


    @FXML
    public void somethingIsSelectedOnListView(MouseEvent event) {
        selectedExercise = listView.getSelectionModel().getSelectedIndex();
        //exerciseSelector.setText(String.valueOf(selectedExercise));
        updateDisplay();
    }

    @FXML
    public void somethingIsSelectedOnListViewRight(MouseEvent event) {
        selectedRecipe = listView2.getSelectionModel().getSelectedIndex();
        listViewDisplay();
        updateDisplay();
        //recipeSelector.setText(String.valueOf(selectedRecipe));
    }

    public void updateDisplay() {
        exerciseDisplay = "";
        //if(!DataHolder.supervisedUser.recipeList.isEmpty()) {
        if (selectedRecipe <= 0) {
            if (DataHolder.supervisedUser.exerciseList.isEmpty()) {
                exerciseDisplay += "No exercises detected. \n";
            } else {
                exerciseDisplay += DataHolder.supervisedUser.exerciseList.get(selectedExercise).getTitle() + "\n";
                exerciseDisplay += DataHolder.supervisedUser.exerciseList.get(selectedExercise).getDescription() + "\n";
            }
        } else {
            if (DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.isEmpty()) {
                exerciseDisplay += "No exercises detected. \n";
            } else {
                exerciseDisplay += DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.
                        get(selectedExercise).getTitle() + "\n";
                exerciseDisplay += DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList.
                        get(selectedExercise).getDescription() + "\n";
            }
        }

        exerciseDisplay += "You can add this exercise to your history by clicking use selected exercise.\n";
        //}
        exerciseListTextArea.setText(exerciseDisplay);

    }

    public void listViewDisplay() {
        listView.getItems().clear();
        if (selectedRecipe <= 0) {
            for (Exercise x : DataHolder.supervisedUser.exerciseList) {
                //listView.getItems().add(x.getTitle());
                if (!DataHolder.supervisedUser.exerciseList.isEmpty()) {
                    listView.getItems().add(x.getTitle());
                } else {
                    listView.getItems().add("");
                }
            }
        } else {
            if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
                for (Exercise x : DataHolder.supervisedUser.recipeList.get(selectedRecipe - 1).exerciseList) {
                    if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
                        listView.getItems().add(x.getTitle());
                        //listView.getItems().add(Methods.oneExerciseOneLine(x));
                    } else {
                        listView.getItems().add("");
                    }
                }
            }
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void listViewDisplay2() {
        listView2.getItems().clear();
        listView2.getItems().add(DataHolder.supervisedUser.getUserName() + " history.");
        if (!DataHolder.supervisedUser.recipeList.isEmpty()) {
            for (Recipe x : DataHolder.supervisedUser.recipeList) {
                listView2.getItems().add(x.getTitle() + " " + Methods.displayIfRecipeIsPublic(x));
                //listView2.getItems().add(Methods.oneRecipeOneLine(x));
            }
        }
        listView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void browseRecipesButtonPressed(ActionEvent event) throws Exception {
        Main.getInstance().setScene(Main.Scene12);

    }

    @FXML
    public void manageRecipes(ActionEvent event) throws Exception {
        System.out.println("going to manage the Recipes on the recipe page");
        Main.getInstance().setScene(Main.Scene8);

    }

    @FXML
    public void getPdf(ActionEvent event) throws Exception {
        PDF.pdfExerciselist(DataHolder.supervisedUser);
    }
}
