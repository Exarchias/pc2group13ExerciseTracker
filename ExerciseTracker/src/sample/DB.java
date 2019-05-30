package sample;

import java.sql.*;

public class DB {

    String url = "jdbc:mysql://den1.mysql5.gear.host:3306?user=xtracker&password=Kt4j_?V18w07";

    Statement statement;

    //this method establishes a connection between the application and MySQL db
    public DB() {
        try {
            Connection c = (Connection) DriverManager.getConnection(url);
            statement = c.createStatement();
        } catch (SQLException ex) {
            System.out.println("the connection fails");
        }
    }


    //this method makes the handshake possible between the application and database system to pull req info
    public void doAHandshake() {
        try {
            ResultSet rs = statement.executeQuery("SHOW databases");

            while(rs.next())
            {
                System.out.println(" This database ==> " + rs.getString(1));
            }

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("Error on executing the handshake");
            DataHolder.isConnected = false;
        }
    }

    //here we enable java to actually execute MySQL statements
    public void loadUsers() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT * FROM user");
            User firstUser = new User("superuser", "12345",
                    "admin@exercise.trackom", true);
            DataHolder.userList.add(0,firstUser);
            System.out.println("Verification: The very first User is " + DataHolder.userList.get(0).getUserName());

            while(rs.next())
            {
                String name = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(2);
                boolean isAdmin = rs.getBoolean(6);
                int weight = rs.getInt(5);
                int userID = rs.getInt(1);
                //String userName, String passWord, String email, Boolean isAdmin
                //String userName, String passWord, String email, boolean isAnAdmin, int weight, int userID
                User user1 = new User(name, password, email,isAdmin, weight,userID);
                DataHolder.userList.add(user1);
                System.out.println("The user: " + name + " is loaded to the application");
            }

            DataHolder.isConnected = true;

        }
        catch (SQLException ex)
        {
            System.out.println("error on loading the users");
            DataHolder.isConnected = false;
        }
    }

    public void loadRecipes() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT * FROM xtracker.recipe");

            while(rs.next())
            {
                //String title, String description, int owner, int recipeID, boolean isPublic
                String title = rs.getString(3);
                String description = rs.getString(4);
                int userID = rs.getInt(2);
                int recipeID = rs.getInt(1);
                boolean isPublic = rs.getBoolean(5);

                Recipe recipe1 = new Recipe(title, description, userID, recipeID, isPublic);

                for (User x : DataHolder.userList){
                    if(x.getUserID() == userID){
                        x.recipeList.add(recipe1);
                        System.out.println(recipe1.getTitle() + " added to the user: " + x.getUserName());
                    }
                }
                if(isPublic){
                    DataHolder.publicRecipes.add(recipe1);
                    System.out.println(recipe1.getTitle() + " is public.");
                }

            }

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on");

        }
    }

    //this method pulls exercises form the db, also exercises can be added to user's recipes
    public void loadExercises() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT * FROM xtracker.exercise");

            while(rs.next())
            {
                //String title, String description, int exerciseID,
                //                    int owner, int recipeID, int repetitions, int caloriesPerRepetition
                String title = rs.getString(5);
                String description = rs.getString(6);
                int exerciseID = rs.getInt(1);
                int userID = rs.getInt(2);
                int recipeID = rs.getInt(7);
                int repetitions = rs.getInt(3);
                int caloriesPerRepetition = rs.getInt(4);
                Exercise exer1 = new Exercise(title, description, exerciseID, userID, recipeID, repetitions,caloriesPerRepetition);

                for (User x : DataHolder.userList){
                    if(x.getUserID() == userID){
                        for (Recipe j : x.recipeList){
                            if(recipeID == j.getRecipeID()){
                                j.exerciseList.add(exer1);
                                System.out.println(exer1.getTitle() + " added to the recipe: "
                                        + j.getTitle() + "of the user: " + x.getUserName());
                            } else {
                                x.exerciseList.add(exer1);
                                System.out.println(exer1.getTitle() + " added to the user: " + x.getUserName());
                            }
                        }
                        break;
                    }
                }

            }

            DataHolder.isConnected = true;


        }
        catch (SQLException ex)
        {
            System.out.println("error on loading the recipes");
            DataHolder.isConnected = false;
        }
    }
 
    //adding a new user
    public void addOneUser(String username, String password, String email, boolean admin) {
        try {
            int isAdmin;
            if(admin){
                isAdmin = 1;
            } else {
                isAdmin = 0;
            }
            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("INSERT INTO user(username, password, email, isAdmin) VALUES ('" + username +"', '" +
                    password +"', '" + email +"', '" + isAdmin + "')");
            System.out.println("Not a failure. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on adding a user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //adding new exercise
    public void addOneExercise(String title, String description, int owner) {
        try {
            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("INSERT INTO exercise(user_userID, title, description) VALUES ('" + owner +"', '" +
                    title +"', '" + description + "')");
            System.out.println("Not a failure. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on adding an exercise:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //adding new recipe
    public void addOneRecipe(String title, String description, int owner, boolean publish) {
        try {
            int isPublic;
            if(publish){
                isPublic = 1;
            } else {
                isPublic = 0;
            }
            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("INSERT INTO recipe(user_userID, title, description, isPublic) VALUES ('" + owner +"', '" +
                    title +"', '" + description + "', '" + isPublic + "')");
            System.out.println("Not a failure. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on adding an exercise:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //editing user data
    public void editOneUser(String username, String password, String email, boolean admin, int userID) {
        try {
            int isAdmin;
            if(admin){
                isAdmin = 1;
            } else {
                isAdmin = 0;
            }
            statement.executeUpdate("USE xtracker");
            //username, password, email, isAdmin
            statement.executeUpdate("UPDATE user SET username='" + username +"', password='" +
                    password +"', email='" + email +"', isAdmin='" + isAdmin + "' WHERE userid='"+ userID +"'");
            System.out.println("Not a failure on editing the user. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on editing the user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //editing exercise details
    public void editOneExercise(String title, String description,int owner, int exerciseID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE exercise SET user_userID='" + owner +"' ,title='" +
                    title +"', description='" + description +"' WHERE exerciseID='"+ exerciseID +"'");
            System.out.println("Not a failure on editing the Exercise. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on editing the user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //updating exercise to a receipe
    public void exerciseToRecipe(int exerciseID, int recipeID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE exercise SET recipe_recipeId='" + recipeID +"'  WHERE exerciseID='"+ exerciseID +"'");
            System.out.println("Not a failure on editing the Exercise. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on editing the user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //editing recipe
    public void editOneRecipe(String title, String description, int owner, boolean publish, int recipeID) {
        try {
            int isPublic;
            if(publish){
                isPublic = 1;
            } else {
                isPublic = 0;
            }
            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE recipe SET user_userID='" + owner +"' ,title='" +
                    title +"', description='" + description + "', isPublic='" + isPublic + "' WHERE recipeId='"+ recipeID +"'");
            System.out.println("Not a failure on editing the recipe. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on editing the recipe:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //removing user
    public void deleteOneUser(int userID) {
        try {
            int isAdmin;

            statement.executeUpdate("USE xtracker");
            //username, password, email, isAdmin
            statement.executeUpdate("DELETE FROM user WHERE userid='"+ userID +"'");
            System.out.println("Not a failure on deleting the user. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on deleting the user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //removing exercise
    public void deleteOneExercise(int exerciseID) {
        try {
            int isAdmin;

            statement.executeUpdate("USE xtracker");
            //username, password, email, isAdmin
            statement.executeUpdate("DELETE FROM exercise WHERE exerciseID='"+ exerciseID +"'");
            System.out.println("Not a failure on deleting the exercise. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on deleting the exercise:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //deleting recipe
    public void deleteOneRecipe(int recipeID) {
        try {
            statement.executeUpdate("USE xtracker");
            //username, password, email, isAdmin
            statement.executeUpdate("DELETE FROM recipe WHERE recipeId='"+ recipeID +"'");
            System.out.println("Not a failure on deleting the recipe. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on deleting the recipe:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //sorting/loading last user
    public int loadLastUserId() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT userID FROM user ORDER BY userID DESC LIMIT 1");
            int lastID = 5000;
            while(rs.next()){
                lastID = rs.getInt(1);
            }

            DataHolder.isConnected = true;
            return lastID;

        }
        catch (SQLException ex)
        {
            System.out.println("error on loading the last userID: " + ex);
            DataHolder.isConnected = false;
            return 5000;
        }
    }

    //sorting exercises
    public int loadLastExerciseId() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT exerciseID FROM exercise ORDER BY exerciseID DESC LIMIT 1");
            int lastID = 5000;
            while(rs.next()){
                lastID = rs.getInt(1);
            }

            DataHolder.isConnected = true;
            return lastID;

        }
        catch (SQLException ex)
        {
            System.out.println("error on loading the last Exercise ID: " + ex);
            DataHolder.isConnected = false;
            return 5000;
        }
    }

    //sorting recipes
    public int loadLastRecipeId() {
        try {
            statement.executeQuery("USE xtracker");
            ResultSet rs = statement.executeQuery("SELECT recipeId FROM recipe ORDER BY recipeId DESC LIMIT 1");
            int lastID = 5000;
            while(rs.next()){
                lastID = rs.getInt(1);
            }

            DataHolder.isConnected = true;
            return lastID;

        }
        catch (SQLException ex)
        {
            System.out.println("error on loading the last Exercise ID: " + ex);
            DataHolder.isConnected = false;
            return 5000;
        }
    }
    //sorting/assign an exercise to a user
    public void exerciseToUser(int exerciseID, int userID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE exercise SET user_userID='" + userID +"'  WHERE exerciseID='"+ exerciseID +"'");
            System.out.println("Not a failure on assigning an exercise to a user. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on editing the user:" + ex);
            DataHolder.isConnected = false;
        }
    }
    //sorting: change the exerciseID of an exercise
    public void exerciseChangeID(int oldExerciseID, int newExerciseID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE exercise SET exerciseID='" + newExerciseID +"'  WHERE exerciseID='"+ oldExerciseID +"'");
            System.out.println("Not a failure on changing the Exercise ID. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on changing the Exercise ID:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //sorting: assign a recipe to a user
    public void recipeToUser(int recipeID, int userID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE recipe SET user_userID='" + userID +"'  WHERE recipeId='"+ recipeID +"'");
            System.out.println("Not a failure on assigning the recipe to a user. check the database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on assigning the recipe to a user:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //sorting: changes the id of a recipe.
    public void recipeChangeId(int oldRecipeID, int newRecipeID) {
        try {

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE recipe SET recipeId='" + newRecipeID +"'  WHERE recipe_recipeId='"+ oldRecipeID +"'");
            System.out.println("Not a failure on changing the id of a recipe. check the Database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on changing the id of a recipe:" + ex);
            DataHolder.isConnected = false;
        }
    }

    //set the isPublic state of a recipe.
    public void recipeIsPublic(int recipeID, boolean publish) {
        try {
            int isPublic = 0;
            if(publish){
                isPublic = 1;
            } else {
                isPublic = 0;
            }

            statement.executeUpdate("USE xtracker");
            statement.executeUpdate("UPDATE recipe SET isPublic='" + isPublic +"'  WHERE recipeId='"+ recipeID +"'");
            System.out.println("Not a failure on changing the public state of a recipe. check the Database");

            DataHolder.isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println("error on changing the public state of a recipe:" + ex);
            DataHolder.isConnected = false;
        }
    }





}
