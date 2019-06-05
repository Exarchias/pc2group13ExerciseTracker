package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    public static final String sample = "sample";
    public static final String Scene2 = "Scene2";
    public static final String Scene3 = "Scene3";
    public static final String Scene4 = "Scene4";
    public static final String Scene5 = "Scene5";
    public static final String Scene6 = "Scene6";
    public static final String Scene7 = "Scene7";
    public static final String Scene8 = "Scene8";
    public static final String Scene9 = "Scene9";
    public static final String Scene10 = "scene10";
    public static final String Scene11 = "Scene11";
    public static final String Scene12 = "Scene12";


    private Stage primaryStage;
    private static Main instance = null;

    public static Main getInstance(){return instance;}


    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Exercise Tracker Application");
        setScene(sample);
        primaryStage.show();


    }

    public void setScene(String nameOfScene) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(nameOfScene+".fxml"));
        root.getStylesheets().add(getClass().getResource(nameOfScene+".fxml").toExternalForm());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("ApplicationView.css").toExternalForm();
        scene.getStylesheets().add(css);

    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //PDF.PdfFun(); //TESTING REASONS
        if(DataHolder.dbActivated){ //CHECKS IF THE DATA BASE IS ACTIVATED
            //JDBC starts
            DB db = new DB();
            db.loadUsers();
            db.loadRecipes();
            db.loadExercises();
            System.out.println("The last users ID should be " + db.loadLastUserId());
//            for (Recipe x : DataHolder.publicRecipes){
//                System.out.println(x.getTitle() + " " + Methods.returnRecipeOwner(x));
//            }
            //JDBC ends
        }

        if(!DataHolder.isConnected){
            Methods.testOfflineScript();
        }

        launch(args);

    }
}
