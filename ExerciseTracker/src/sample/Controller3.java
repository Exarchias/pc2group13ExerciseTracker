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
        exerciseSelector.setText(String.valueOf(selectedExercise));

        listViewDisplay();
        updateDisplay();
        listViewDisplay2();
//        if(DataHolder.activeUser.exerciseList.get(0) != null){
//            updateDisplay();
//        }

        if (DataHolder.isAdmin())
            btnGoToTheAdminPage.setVisible(true);
        else btnGoToTheAdminPage.setVisible(false);


    }

    @FXML
   public ListView<String> listView;
    @FXML
    Label lblActiveName;
    @FXML
    TextField exerciseSelector;

    @FXML
    TextArea exerciseListTextArea;

    @FXML
    Button btnGoToTheAdminPage;

    @FXML
    Button useSelectedExerciseBtn;

    @FXML
    TextField recipeSelector;

    @FXML
    public ListView<String> listView2;

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
    public void testViewExercises(ActionEvent event){
        System.out.println("Presenting the exercises...");
        updateDisplay();
        System.out.println(exerciseDisplay);
    }

    @FXML
    public void addExercise(ActionEvent event) throws Exception{
        System.out.println("adding an exercise to the list");
//        Exercise exer = new Exercise("TestExercise " + DataHolder.testCount,
//                "Test Exercise description", DataHolder.userList.indexOf(DataHolder.activeUser));
//        DataHolder.testCount ++;
//        DataHolder.activeUser.exerciseList.add(exer);
//        updateDisplay();
        Main.getInstance().setScene(Main.Scene6);
    }

    @FXML
    public void deleteExercise(ActionEvent event){
        selectedExercise = Integer.parseInt(exerciseSelector.getText());
        System.out.println("Deleting the exercise " +
                DataHolder.activeUser.exerciseList.get(selectedExercise).getTitle() + " from the list");
        DataHolder.activeUser.exerciseList.remove(selectedExercise);
        updateDisplay();
        listViewDisplay();

    }

    @FXML
    public void editExercise(ActionEvent event) throws Exception{
        selectedExercise = Integer.parseInt(exerciseSelector.getText());
        System.out.println("editing the exercise" +
                DataHolder.activeUser.exerciseList.get(selectedExercise).getTitle() + " from the list");
        DataHolder.supervisedExercise = DataHolder.activeUser.exerciseList.get(selectedExercise);
        DataHolder.supervisedExercisePostion = selectedExercise;
        Main.getInstance().setScene(Main.Scene11);
    }

    @FXML
    public void useSelectedExercise(ActionEvent event){
        if(DataHolder.activeUser.exerciseList != null){
            selectedExercise = Integer.parseInt(exerciseSelector.getText());
            System.out.println("Deleting the exercise " +
                    DataHolder.activeUser.exerciseList.get(selectedExercise).getTitle() + " from the list");
            Exercise exer = DataHolder.activeUser.exerciseList.get(selectedExercise);
            DataHolder.activeUser.exerciseList.add(exer);
            updateDisplay();
            listViewDisplay();
        } else {
            System.out.println("the list is empty...");
        }

    }

    @FXML
    public void somethingIsSelectedOnListView(MouseEvent event){
        selectedExercise = listView.getSelectionModel().getSelectedIndex();
        exerciseSelector.setText(String.valueOf(selectedExercise));
    }

    @FXML
    public void somethingIsSelectedOnListViewRight(MouseEvent event){
        selectedRecipe = listView2.getSelectionModel().getSelectedIndex();
        //recipeSelector.setText(String.valueOf(selectedRecipe));
    }

    public void updateDisplay(){
        exerciseDisplay = "";
        if(!DataHolder.activeUser.exerciseList.isEmpty()){
            exerciseDisplay += DataHolder.activeUser.exerciseList.get(selectedExercise).getTitle() + "\n";
            exerciseDisplay += DataHolder.activeUser.exerciseList.get(selectedExercise).getDescription() + "\n";
        }
        exerciseDisplay += "You can add this exercise to your history by clicking use selected exercise.\n";
        exerciseListTextArea.setText(exerciseDisplay);

    }

    public void listViewDisplay(){
        listView.getItems().clear();
        for (Exercise x : DataHolder.activeUser.exerciseList){
            listView.getItems().add(x.getTitle());
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void listViewDisplay2(){
        listView2.getItems().clear();
        for (Recipe x : DataHolder.activeUser.recipeList){
            listView2.getItems().add(x.getTitle());
        }
        listView2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


}
