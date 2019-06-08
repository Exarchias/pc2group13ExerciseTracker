package sample;

import java.util.ArrayList;

public class DataHolder {
    private static boolean admin = false;
    private static boolean login = false;
    private static String activeName; //updates automatically on the login.
    public static User activeUser;
    public static User supervisedUser; //when an Admin inspects a User's profile.
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Recipe> publicRecipes = new ArrayList<>();
    public static Exercise supervisedExercise;
    public static Recipe supervisedRecipe;
    public static int supervisedExercisePostion;
    public static int supervisedRecipePostion;
    public static int testCount = 0;
    public static boolean isConnected = false;
    public static boolean dbActivated = false; //CONFIGORATIONS FOR THE DB.

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        DataHolder.admin = admin;
    }

    public static String getActiveName() {
        return activeName;
    }

    public static void setActiveName(String activeName) {
        DataHolder.activeName = activeName;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        DataHolder.login = login;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User activeUser) {
        DataHolder.activeUser = activeUser;
    }

    public static User getSupervisedUser() {
        return supervisedUser;
    }

    public static void setSupervisedUser(User supervisedUser) {
        DataHolder.supervisedUser = supervisedUser;
    }

    public static Exercise getSupervisedExercise() {
        return supervisedExercise;
    }

    public static void setSupervisedExercise(Exercise supervisedExercise) {
        DataHolder.supervisedExercise = supervisedExercise;
    }

    public static Recipe getSupervisedRecipe() {
        return supervisedRecipe;
    }

    public static void setSupervisedRecipe(Recipe supervisedRecipe) {
        DataHolder.supervisedRecipe = supervisedRecipe;
    }

    public static int getSupervisedExercisePostion() {
        return supervisedExercisePostion;
    }

    public static void setSupervisedExercisePostion(int supervisedExercisePostion) {
        DataHolder.supervisedExercisePostion = supervisedExercisePostion;
    }

    public static int getSupervisedRecipePostion() {
        return supervisedRecipePostion;
    }

    public static void setSupervisedRecipePostion(int supervisedRecipePostion) {
        DataHolder.supervisedRecipePostion = supervisedRecipePostion;
    }

    public static int getTestCount() {
        return testCount;
    }

    public static void setTestCount(int testCount) {
        DataHolder.testCount = testCount;
    }

    //removes the unpublished recipes from the publicRecipes list
    public static void unpublishRecipes(){
        if(!publicRecipes.isEmpty()) {
            for (Recipe x : publicRecipes) {
                if (!x.isPublic()) {
                    publicRecipes.remove(publicRecipes.indexOf(x));
                }
            }
        }
    }
}
