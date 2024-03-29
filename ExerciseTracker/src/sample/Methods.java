package sample;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Methods {


    public static void logOut() throws Exception {
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    public static void testOfflineScript() throws NoSuchAlgorithmException {
        User firstUser = new User("admin", encrypted("12345"),
                "admin@exercise.trackom", true);

        DataHolder.userList.add(0, firstUser);
        User secondUser = new User("average", encrypted("12345"),
                "LowBob@exercise.trackom", false);
        DataHolder.userList.add(1, secondUser);
        System.out.println("Verification: The very first User is " + DataHolder.userList.get(0).getUserName());
        System.out.println("Verification: The very second User is " + DataHolder.userList.get(1).getUserName());
        //content for the admin starts
        Exercise exe1 = new Exercise("ExeAdmin1", "Exercise of the admin number 1", 0);
        Exercise exe2 = new Exercise("ExeAdmin2", "Exercise of the admin number 2", 0);
        Exercise exe3 = new Exercise("ExeAdmin3", "Exercise of the admin number 3", 0);
        System.out.println("exercises for the Admin's history are just initiated");
        DataHolder.userList.get(0).exerciseList.add(exe1);
        DataHolder.userList.get(0).exerciseList.add(exe2);
        DataHolder.userList.get(0).exerciseList.add(exe3);
        System.out.println("exercises for the Admin are just added to the admin's history");
        Exercise tra1 = new Exercise("traAdmin1", "Exercise for the tralala of the admin number 1", 0);
        Exercise tra2 = new Exercise("traAdmin2", "Exercise for the tralala of the admin number 2", 0);
        Exercise tra3 = new Exercise("traAdmin3", "Exercise for the tralala of the admin number 3", 0);
        System.out.println("exercises for the Admin's history are just initiated");
        Recipe tralala = new Recipe("Tralala", "The tralala recipe", 0);
        DataHolder.userList.get(0).recipeList.add(tralala);
        DataHolder.userList.get(0).recipeList.get(0).exerciseList.add(tra1);
        DataHolder.userList.get(0).recipeList.get(0).exerciseList.add(tra2);
        DataHolder.userList.get(0).recipeList.get(0).exerciseList.add(tra3);
        System.out.println("exercises for the Admin are just added to the tralala recipe of the admin");
        Exercise tro1 = new Exercise("troAdmin1", "Exercise for the trololo of the admin number 1", 0);
        Exercise tro2 = new Exercise("troAdmin2", "Exercise for the trololo of the admin number 2", 0);
        Exercise tro3 = new Exercise("troAdmin3", "Exercise for the trololo of the admin number 3", 0);
        System.out.println("exercises for the Admin's history are just initiated");
        Recipe trololo = new Recipe("Trololo", "The trololo recipe", 0);
        DataHolder.userList.get(0).recipeList.add(trololo);
        DataHolder.userList.get(0).recipeList.get(1).exerciseList.add(tro1);
        DataHolder.userList.get(0).recipeList.get(1).exerciseList.add(tro2);
        DataHolder.userList.get(0).recipeList.get(1).exerciseList.add(tro3);
        System.out.println("exercises for the Admin are just added to the trololo recipe of the admin");
        //content for the user starts
        Exercise exe4 = new Exercise("ExeUser1", "Exercise of the admin number 4", 1);
        Exercise exe5 = new Exercise("ExeUser2", "Exercise of the admin number 5", 1);
        Exercise exe6 = new Exercise("ExeUser3", "Exercise of the admin number 6", 1);
        System.out.println("exercises for the Admin's history are just initiated");
        DataHolder.userList.get(1).exerciseList.add(exe4);
        DataHolder.userList.get(1).exerciseList.add(exe5);
        DataHolder.userList.get(1).exerciseList.add(exe6);
        System.out.println("exercises for the Admin are just added to the user's history");
        Exercise tre1 = new Exercise("treUser1", "Exercise for the trelele of the user number 1", 1);
        Exercise tre2 = new Exercise("treUser2", "Exercise for the trelele of the user number 2", 1);
        Exercise tre3 = new Exercise("treUser3", "Exercise for the trelele of the user number 3", 1);
        System.out.println("exercises for the User's history are just initiated");
        Recipe trelele = new Recipe("Trelele", "The trelele recipe", 1);
        DataHolder.userList.get(1).recipeList.add(trelele);
        DataHolder.userList.get(1).recipeList.get(0).exerciseList.add(tre1);
        DataHolder.userList.get(1).recipeList.get(0).exerciseList.add(tre2);
        DataHolder.userList.get(1).recipeList.get(0).exerciseList.add(tre3);
        System.out.println("exercises for the Admin are just added to the tralala recipe of the user");
        Exercise tri1 = new Exercise("triUser1", "Exercise for the trilili of the user number 1", 1);
        Exercise tri2 = new Exercise("triUser2", "Exercise for the trilili of the user number 2", 1);
        Exercise tri3 = new Exercise("triUser3", "Exercise for the trilili of the user number 3", 1);
        Exercise tri4 = new Exercise("triUser4", "Exercise for the trilili of the user number 4", 1);
        Exercise tri5 = new Exercise("triUser5", "Exercise for the trilili of the user number 5", 1);
        Exercise tri6 = new Exercise("triUser6", "Exercise for the trilili of the user number 6", 1);
        System.out.println("exercises for the User's history are just initiated");
        Recipe trilili = new Recipe("Trilili", "The trilili recipe", 1);
        Recipe triliPublic = new Recipe("Trilili Public", "The public trilili recipe", 1);
        triliPublic.setPublic(true);
        DataHolder.userList.get(1).recipeList.add(trilili);
        DataHolder.userList.get(1).recipeList.add(triliPublic);
        DataHolder.publicRecipes.add(triliPublic);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri1);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri2);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri3);
        DataHolder.userList.get(1).recipeList.get(2).exerciseList.add(tri4);
        DataHolder.userList.get(1).recipeList.get(2).exerciseList.add(tri5);
        DataHolder.userList.get(1).recipeList.get(2).exerciseList.add(tri5);
        System.out.println("Offline procedure just got complete");
    }

    public static void initiatingTemplatesScript() {
        Template temp1 = new Template("Push Ups", "a set of push ups", 0, 1);
        DataHolder.templateList.add(0, temp1);
        System.out.println("Template temp1 just added to the templateList");
        Template temp2 = new Template("Sit Ups", "a set of sit ups", 0, 2);
        DataHolder.templateList.add(1, temp2);
        System.out.println("Template temp2 just added to the templateList");
        Template temp3 = new Template("Squats", "a set of Squats", 0, 3);
        DataHolder.templateList.add(2, temp3);
        System.out.println("Template temp3 just added to the templateList");
    }

    //this method displays the user that are admins
    public String displayAdmins() {
        int count = 0;
        String adminListDisplay = "";
        for (User x : DataHolder.userList) {
            if (x.isAnAdmin()) {
                adminListDisplay += count + ") " + x.getUserName() + "\n";
                count++;
            }
        }
        return adminListDisplay;
    }

    //this method displays the contents of an exercise:
    public static String displayTheExercise(Exercise exercise) {
        String exContent = "";
        exContent += "Title: " + exercise.getTitle() + "\n";
        exContent += "Description: " + exercise.getDescription() + "\n";
        exContent += "Exercise ID: " + exercise.getExerciseID() + "\n";
        exContent += "Exercise Owner: " + returnExerciseOwner(exercise) + "\n";
        return exContent;
    }

    //this method displays the recipes of the user.
    public static String displayUsersRecipes(User user) {
        String userRecipes = "";
        int count = 0;
        userRecipes += " Recipes \n";
        userRecipes += " ======= \n";
        for (Recipe x : user.recipeList) {
            count++;
            userRecipes += count + ") Title: " + x.getTitle() + "\n";
        }
        return userRecipes;
    }

    //this method displays a recipe's exercises
    public static String displayContentsofTherecipe(Recipe recipe) {
        String RecipeContent = "";
        int count = 0;
        RecipeContent += " Exercises \n";
        RecipeContent += " ======= \n";
        for (Exercise x : recipe.exerciseList) {
            count++;
            RecipeContent += count + ") Title: " + x.getTitle() + "\n";
        }
        return RecipeContent;
    }

    //this method displays a user's exercises
    public static String displayExercisesofTheUser(User user) {
        String userContent = "";
        int count = 0;
        userContent += " Exercises of the User \n";
        userContent += " ===================== \n";
        for (Exercise x : user.exerciseList) {
            count++;
            userContent += count + ") Title: " + x.getTitle() + "\n";
        }
        return userContent;
    }

    //this method displays the contents of a User Profile:
    public static String displayTheUser(User user) {
        String userContent = "";
        userContent += "Nickname: " + user.getUserName() + " " + displayIfAdmin(user) + "\n";
        userContent += "user Id: " + user.getUserID() + "\n";
        userContent += "user Id: " + user.getEmail() + "\n";
        userContent += "weight: " + user.getWeight() + "\n";
        userContent += displayUsersRecipes(user) + "\n";
        userContent += displayExercisesofTheUser(user);
        return userContent;
    }

    //this method displays if the User is admin:
    public static String displayIfAdmin(User user) {
        String adminContent = "";
        if (user.isAnAdmin()) {
            adminContent = " (Admin)";
        }
        return adminContent;
    }

    //this method displays if the User is admin:
    public static String displayIfRecipeIsPublic(Recipe recipe) {
        String adminContent = "";
        if (recipe.isPublic()) {
            adminContent = " (Public)";
        }
        return adminContent;
    }

    //WORK_IN_PROGRESS - This method displays the ID's of the users
    public static String displayIDsfTheUser(User user) {
        String userContent = "";
        int count = 0;
        userContent += " IDs of each User \n";
        userContent += " ===================== \n";
        for (User x : DataHolder.userList) {
            count++;
            userContent += count + ")" + x.getUserName() + " ID: " + x.getUserID() + "\n";
        }
        return userContent;
    }

    //this method displays a recipe's exercises
    public static String displayTherecipe(Recipe recipe) {
        String recipedisplay = "";
        recipedisplay += "Title: " + recipe.getTitle() + " " + displayIfRecipeIsPublic(recipe) + "\n";
        recipedisplay += "Description: " + recipe.getDescription() + "\n";
        //recipedisplay += "Owner: " + returnRecipeOwner(recipe) + "\n";
        recipedisplay += " ========= \n";
        recipedisplay += displayContentsofTherecipe(recipe) + "\n";
        return recipedisplay;
    }

    //Returns the username of the owner of the exercise
    public static String returnExerciseOwner(Exercise exercise) {
        String owner = DataHolder.userList.get(exercise.getOwner()).getUserName();
        return owner;
    }

    //Returns the username of the owner of the recipe
    public static String returnRecipeOwner(Recipe recipe) {
        String owner = DataHolder.userList.get(recipe.getOwner()).getUserName();
        return owner;
    }

    //this method transforms  a byte stream to a Hex format. We use this method for our hash encryption.
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    //transforms a string to a hash encryption. This method uses the help of bytesToHex method.
    public static String encrypted(String digested) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                digested.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    //This method give details for one User in one line.
    public static String oneUserOneLine(User user) {
        String msg = user.getUserName() + " " + displayIfAdmin(user) + " / " + user.getEmail() + " / ID: " + user.getUserID()
                + " / weight: " + user.getWeight();
        return msg;
    }

    //This method gives the details of one exercise in one line.
    public static String oneExerciseOneLine(Exercise exercise) {
        String msg = exercise.getTitle() + " / ID: " + exercise.getExerciseID() + " / Owner: " + returnExerciseOwner(exercise)
                + " / " + exercise.getDescription();
        return msg;
    }

    //this method gives the details of one Recipe in one line.
    public static String oneRecipeOneLine(Recipe recipe) {
        String msg = recipe.getTitle() + " " + displayIfRecipeIsPublic(recipe) + " / ID: " + recipe.getRecipeID()
                + " / Owner: " + returnRecipeOwner(recipe)
                + " / " + recipe.getDescription();
        return msg;
    }

    //this method returns the list of the exercises of a User, nicely organized in a list.
    public static String displayListOfExercises(User user) {
        String msg = "";
        for (Exercise x : user.exerciseList) {
            msg += oneExerciseOneLine(x) + " \n";
        }
        return msg;
    }

    //this method returns the list of the recipes of a User, nicely organized in a list.
    public static String displayListOfRecipes(User user) {
        String msg = "";
        for (Recipe x : user.recipeList) {
            msg += oneRecipeOneLine(x) + " \n";
        }
        return msg;
    }
}



