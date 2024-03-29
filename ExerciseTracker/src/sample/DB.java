package sample;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DB {

    String url = "jdbc:mysql://den1.mysql5.gear.host:3306?user=xtracker&password=Kt4j_?V18w07";

    Statement statement;

    //this method establishes a connection between the application and MySQL db
    public DB() {
        if (DataHolder.dbActivated) {
            try {
                Connection c = (Connection) DriverManager.getConnection(url);
                statement = c.createStatement();
            } catch (SQLException ex) {
                System.out.println("the connection fails");
            }
        }
    }


    //this method makes the handshake possible between the application and database system to pull req info
    public void doAHandshake() {
        if (DataHolder.dbActivated) {
            try {
                ResultSet rs = statement.executeQuery("SHOW databases");

                while (rs.next()) {
                    System.out.println(" This database ==> " + rs.getString(1));
                }

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("Error on executing the handshake");
                DataHolder.isConnected = false;
            }
        }
    }

    //here we enable java to actually execute MySQL statements
    public void loadUsers() throws NoSuchAlgorithmException {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT * FROM user");
                User firstUser = new User("superuser", Methods.encrypted("12345"),
                        "admin@exercise.trackom", true);
                DataHolder.userList.add(0, firstUser);
                System.out.println("Verification: The very first User is " + DataHolder.userList.get(0).getUserName());

                while (rs.next()) {
                    String name = rs.getString(3);
                    String password = rs.getString(4);
                    String email = rs.getString(2);
                    boolean isAdmin = rs.getBoolean(6);
                    int weight = rs.getInt(5);
                    int userID = rs.getInt(1);
                    //String userName, String passWord, String email, Boolean isAdmin
                    //String userName, String passWord, String email, boolean isAnAdmin, int weight, int userID
                    User user1 = new User(name, password, email, isAdmin, weight, userID);
                    DataHolder.userList.add(user1);
                    System.out.println("The user: " + name + " is loaded to the application");
                }

                DataHolder.isConnected = true;

            } catch (SQLException ex) {
                System.out.println("error on loading the users");
                DataHolder.isConnected = false;
            }
        }
    }

    public void loadRecipes() {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT * FROM xtracker.recipe");

                while (rs.next()) {
                    //String title, String description, int owner, int recipeID, boolean isPublic
                    String title = rs.getString(3);
                    String description = rs.getString(4);
                    int userID = rs.getInt(2);
                    int recipeID = rs.getInt(1);
                    boolean isPublic = rs.getBoolean(5);

                    Recipe recipe1 = new Recipe(title, description, userID, recipeID, isPublic);

                    for (User x : DataHolder.userList) {
                        if (x.getUserID() == userID) {
                            x.recipeList.add(recipe1);
                            System.out.println(recipe1.getTitle() + " added to the user: " + x.getUserName());
                        }
                    }
                    if (recipe1.isPublic()) {
                        DataHolder.publicRecipes.add(recipe1);
                        System.out.println(recipe1.getTitle() + " is public.");
                    }

                }

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on");

            }
        }
    }

    //this method pulls exercises form the db, also exercises can be added to user's recipes
    public void loadExercises() {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT * FROM xtracker.exercise");

                while (rs.next()) {
                    //String title, String description, int exerciseID,
                    //                    int owner, int recipeID, int repetitions, int caloriesPerRepetition
                    String title = rs.getString(5);
                    String description = rs.getString(6);
                    int exerciseID = rs.getInt(1);
                    int userID = rs.getInt(2);
                    int recipeID = rs.getInt(7);
                    int repetitions = rs.getInt(3);
                    int caloriesPerRepetition = rs.getInt(4);
                    System.out.println("From DB for exer1: exerciseID=" + exerciseID + ", recipeID=" + recipeID + "owner" + userID);
                    Exercise exer1 = new Exercise(title, description, exerciseID, userID, recipeID, repetitions, caloriesPerRepetition);

                    for (User x : DataHolder.userList) {
                        if (x.getUserID() == userID) {
                            if (recipeID == 0) {
                                System.out.println("adding an exercise to the user " + x.getUserName());
                                x.exerciseList.add(exer1);
                            }
                            for (Recipe j : x.recipeList) {
                                System.out.println("From recipe J: title=" + j.getTitle() + ", recipeID=" + j.getRecipeID() +
                                        "from the user: " + j.getOwner());
                                System.out.println(exer1.getRecipeID() + " as exer compares with " + j.getRecipeID() + " as J " +
                                        "from the user: " + j.getOwner());
                                if (exer1.getRecipeID() == j.getRecipeID()) {
                                    j.exerciseList.add(exer1);
                                    System.out.println(exer1.getTitle() + ":" + exer1.getExerciseID() + " added to the recipe: "
                                            + j.getTitle() + ":" + exer1.getRecipeID() + " of the user: " + x.getUserName()
                                            + ":" + exer1.getOwner());
                                    break;
                                } else {
                                    System.out.println("Doing nothing");
                                }


                            }

                        } else {
                            System.out.println("We dont have a much between " + x.getUserID() + " and " + userID);
                        }
                    }

                }

                DataHolder.isConnected = true;


            } catch (SQLException ex) {
                System.out.println("error on loading the recipes");
                DataHolder.isConnected = false;
            }
        }
    }

    //adding a new user
    public void addOneUser(String username, String password, String email, boolean admin) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;
                if (admin) {
                    isAdmin = 1;
                } else {
                    isAdmin = 0;
                }
                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("INSERT INTO user(username, password, email, isAdmin) VALUES ('" + username + "', '" +
                        password + "', '" + email + "', '" + isAdmin + "')");
                System.out.println("Not a failure. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on adding a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //adding new exercise
    public void addOneExercise(String title, String description, int owner) {
        if (DataHolder.dbActivated) {
            try {
                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("INSERT INTO exercise(user_userID, title, description) VALUES ('" + owner + "', '" +
                        title + "', '" + description + "')");
                System.out.println("Not a failure. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on adding an exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //adding new recipe
    public void addOneRecipe(String title, String description, int owner, boolean publish) {
        if (DataHolder.dbActivated) {
            try {
                int isPublic;
                if (publish) {
                    isPublic = 1;
                } else {
                    isPublic = 0;
                }
                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("INSERT INTO recipe(user_userID, title, description, isPublic) VALUES ('" + owner + "', '" +
                        title + "', '" + description + "', '" + isPublic + "')");
                System.out.println("Not a failure. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on adding an exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //editing user data
    public void editOneUser(String username, String password, String email, boolean admin, int userID) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;
                if (admin) {
                    isAdmin = 1;
                } else {
                    isAdmin = 0;
                }
                statement.executeUpdate("USE xtracker");
                //username, password, email, isAdmin
                statement.executeUpdate("UPDATE user SET username='" + username + "', password='" +
                        password + "', email='" + email + "', isAdmin='" + isAdmin + "' WHERE userid='" + userID + "'");
                System.out.println("Not a failure on editing the user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on editing the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //editing exercise details
    public void editOneExercise(String title, String description, int owner, int exerciseID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET user_userID='" + owner + "' ,title='" +
                        title + "', description='" + description + "' WHERE exerciseID='" + exerciseID + "'");
                System.out.println("Not a failure on editing the Exercise. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on editing the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //updating exercise to a receipe
    public void exerciseToRecipe(int exerciseID, int recipeID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET recipe_recipeId='" + recipeID + "'  WHERE exerciseID='" + exerciseID + "'");
                System.out.println("The exercise " + exerciseID + "is assigned to the recipe with the id " + recipeID);
                System.out.println("Not a failure on changing the recipe of the exercise. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the recipe of the exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //editing recipe
    public void editOneRecipe(String title, String description, int owner, boolean publish, int recipeID) {
        if (DataHolder.dbActivated) {
            try {
                int isPublic;
                if (publish) {
                    isPublic = 1;
                } else {
                    isPublic = 0;
                }
                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET user_userID='" + owner + "' ,title='" +
                        title + "', description='" + description + "', isPublic='" + isPublic + "' WHERE recipeId='" + recipeID + "'");
                System.out.println("Not a failure on editing the recipe. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on editing the recipe:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //removing user
    public void deleteOneUser(int userID) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;

                statement.executeUpdate("USE xtracker");
                //username, password, email, isAdmin
                statement.executeUpdate("DELETE FROM user WHERE userid='" + userID + "'");
                System.out.println("Not a failure on deleting the user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //removing exercise
    public void deleteOneExercise(int exerciseID) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;

                statement.executeUpdate("USE xtracker");
                //username, password, email, isAdmin
                statement.executeUpdate("DELETE FROM exercise WHERE exerciseID='" + exerciseID + "'");
                System.out.println("Not a failure on deleting the exercise. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //deleting recipe
    public void deleteOneRecipe(int recipeID) {
        if (DataHolder.dbActivated) {
            try {
                statement.executeUpdate("USE xtracker");
                //username, password, email, isAdmin
                statement.executeUpdate("DELETE FROM recipe WHERE recipeId='" + recipeID + "'");
                System.out.println("Not a failure on deleting the recipe. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the recipe:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting/loading last user
    public int loadLastUserId() {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT userID FROM user ORDER BY userID DESC LIMIT 1");
                int lastID = 5000;
                while (rs.next()) {
                    lastID = rs.getInt(1);
                }

                DataHolder.isConnected = true;
                return lastID;

            } catch (SQLException ex) {
                System.out.println("error on loading the last userID: " + ex);
                DataHolder.isConnected = false;
                return 5000;
            }
        } else {
            return 5000;
        }
    }

    //sorting exercises
    public int loadLastExerciseId() {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT exerciseID FROM exercise ORDER BY exerciseID DESC LIMIT 1");
                int lastID = 5000;
                while (rs.next()) {
                    lastID = rs.getInt(1);
                }

                DataHolder.isConnected = true;
                return lastID;

            } catch (SQLException ex) {
                System.out.println("error on loading the last Exercise ID: " + ex);
                DataHolder.isConnected = false;
                return 5000;
            }
        } else {
            return 5000;
        }
    }

    //sorting recipes
    public int loadLastRecipeId() {
        if (DataHolder.dbActivated) {
            try {
                statement.executeQuery("USE xtracker");
                ResultSet rs = statement.executeQuery("SELECT recipeId FROM recipe ORDER BY recipeId DESC LIMIT 1");
                int lastID = 5000;
                while (rs.next()) {
                    lastID = rs.getInt(1);
                }

                DataHolder.isConnected = true;
                return lastID;

            } catch (SQLException ex) {
                System.out.println("error on loading the last Exercise ID: " + ex);
                DataHolder.isConnected = false;
                return 5000;
            }
        } else {
            return 5000;
        }
    }

    //sorting/assign an exercise to a user
    public void exerciseToUser(int exerciseID, int userID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET user_userID='" + userID + "'  WHERE exerciseID='" + exerciseID + "'");
                System.out.println("Not a failure on assigning an exercise to a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on editing the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: change the exerciseID of an exercise
    public void exerciseChangeID(int oldExerciseID, int newExerciseID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET exerciseID='" + newExerciseID + "'  WHERE exerciseID='" + oldExerciseID + "'");
                System.out.println("Not a failure on changing the Exercise ID. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the Exercise ID:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: assign a recipe to a user
    public void recipeToUser(int recipeID, int userID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET user_userID='" + userID + "'  WHERE recipeId='" + recipeID + "'");
                System.out.println("Not a failure on assigning the recipe to a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on assigning the recipe to a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: changes the id of a recipe.
    public void recipeChangeId(int oldRecipeID, int newRecipeID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET recipeId='" + newRecipeID + "'  WHERE recipe_recipeId='" + oldRecipeID + "'");
                System.out.println("Not a failure on changing the id of a recipe. check the Database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the id of a recipe:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //set the isPublic state of a recipe.
    public void recipeIsPublic(int recipeID, boolean publish) {
        if (DataHolder.dbActivated) {
            try {
                int isPublic = 0;
                if (publish) {
                    isPublic = 1;
                } else {
                    isPublic = 0;
                }

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET isPublic='" + isPublic + "'  WHERE recipeId='" + recipeID + "'");
                System.out.println("Not a failure on changing the public state of a recipe. check the Database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the public state of a recipe:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: change the userID of a user
    public void userChangeID(int oldUserID, int newUserID) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET user_userID='" + newUserID + "'  WHERE user_userID='" + oldUserID + "'");
                System.out.println("Not a failure on changing the userID of a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the userID of a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: edit the username of a user.
    public void userChangeUsername(int userID, String userName) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET username='" + userName + "'  WHERE user_userID='" + userID + "'");
                System.out.println("Not a failure on changing the username of a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the username of a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: edit the password of a user.
    public void userChangePassword(int userID, String password) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET password='" + password + "'  WHERE user_userID='" + userID + "'");
                System.out.println("Not a failure on changing the password of a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the password of a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sorting: edit the weight of a user.
    public void userChangeUsername(int userID, int weight) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE recipe SET weight='" + weight + "'  WHERE user_userID='" + userID + "'");
                System.out.println("Not a failure on changing the weight of a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on changing the weight of a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }


    //removing exercise
    public void deleteAllTheExercisesOfTheUser(int userID) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("DELETE FROM exercise WHERE user_userID='" + userID + "'");
                System.out.println("Not a failure on deleting the exercises of the user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the exercises of the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //removing exercise
    public void deleteAllTheExercisesOfTheRecipe(int recipeID) {
        if (DataHolder.dbActivated) {
            try {
                int isAdmin;

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("DELETE FROM exercise WHERE recipe_recipeId='" + recipeID + "'");
                System.out.println("Not a failure on deleting the exercises of the user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the exercises of the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //deleting the recipes of the user.
    public void deleteAllTheRecipesOfTheUser(int userID) {
        if (DataHolder.dbActivated) {
            try {
                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("DELETE FROM recipe WHERE user_userID='" + userID + "'");
                System.out.println("Not a failure on deleting the recipes of the user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on deleting the recipes of the user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //changes the weight of a user
    public void weightOfTheUser(int userID, int weight) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE user SET weight='" + weight + "'  WHERE userID='" + userID + "'");
                System.out.println("Not a failure on assigning the recipe to a user. check the database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on assigning the recipe to a user:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sets the repetitions of an exercise.
    public void setExerciseRepetitions(int exerciseID, int repetitions) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET repetitionsUnit'" + repetitions + "'  WHERE exerciseId='"
                        + exerciseID + "'");
                System.out.println("Not a failure on setting the repetitions of the exercise. check the Database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on setting the repetitions of the exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }

    //sets the calories of each repetition of the exercise.
    public void setExerciseCaloriesPerRepetition(int exerciseID, int calories) {
        if (DataHolder.dbActivated) {
            try {

                statement.executeUpdate("USE xtracker");
                statement.executeUpdate("UPDATE exercise SET caloriesPerRepetition'" + calories + "'  WHERE exerciseId='"
                        + exerciseID + "'");
                System.out.println("Not a failure on setting the calories per repetition of the exercise. check the Database");

                DataHolder.isConnected = true;
            } catch (SQLException ex) {
                System.out.println("error on setting the calories per repetition of the exercise:" + ex);
                DataHolder.isConnected = false;
            }
        }
    }


}
