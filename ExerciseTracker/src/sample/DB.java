package sample;

import java.sql.*;

public class DB {

    String url = "jdbc:mysql://den1.mysql5.gear.host:3306?user=xtracker&password=Kt4j_?V18w07";

    Statement statement;

    public DB() {
        try {
            Connection c = (Connection) DriverManager.getConnection(url);
            statement = c.createStatement();
        } catch (SQLException ex) {
            System.out.println("the connection fails ");
        }
    }


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
            System.out.println("error on executing the handshake");
            DataHolder.isConnected = false;
        }
    }

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
            System.out.println("error on loading the recipes");
            DataHolder.isConnected = false;
        }
    }

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
                        x.exerciseList.add(exer1);
                        System.out.println(exer1.getTitle() + " added to the user: " + x.getUserName());
                        for (Recipe j : x.recipeList){
                            if(recipeID == j.getRecipeID()){
                                j.exerciseList.add(exer1);
                                System.out.println(exer1.getTitle() + " added to the recipe: " + j.getTitle());
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
}