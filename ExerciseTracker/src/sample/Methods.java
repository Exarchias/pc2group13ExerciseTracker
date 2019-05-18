package sample;

public class Methods {


    public static void logOut()throws Exception{
        Main.getInstance().setScene(Main.sample);
        System.out.println("You are logged out");
    }

    public static void testOfflineScript(){
        User firstUser = new User("admin", "12345",
                "admin@exercise.trackom", true);

        DataHolder.userList.add(0,firstUser);
        User secondUser = new User("average", "12345",
                "LowBob@exercise.trackom", false);
        DataHolder.userList.add(1,secondUser);
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
        System.out.println("exercises for the User's history are just initiated");
        Recipe trilili = new Recipe("Trilili", "The trilili recipe", 0);
        DataHolder.userList.get(1).recipeList.add(trilili);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri1);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri2);
        DataHolder.userList.get(1).recipeList.get(1).exerciseList.add(tri3);
        System.out.println("Offline procedure just got complete");
    }
}
