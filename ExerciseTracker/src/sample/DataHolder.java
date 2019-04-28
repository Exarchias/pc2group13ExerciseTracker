package sample;

import java.util.ArrayList;

public class DataHolder {
    private static boolean admin = false;
    private static boolean login = false;
    private static String activeName; //updates automatically on the login.
    public static User activeUser;
    public static User supervisedUser; //when an Admin inspects a User's profile.
    public static ArrayList<User> userList = new ArrayList<>();
    public static Exercise supervisedExercise;
    public static int supervisedExercisePostion;
    public static int testCount = 0;

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


}
