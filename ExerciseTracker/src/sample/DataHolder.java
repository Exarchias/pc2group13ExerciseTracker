package sample;

import java.util.ArrayList;

public class DataHolder {
    private static boolean admin = false;
    private static boolean login = false;
    public static User activeUser;
    public static User supervisedUser; //when an Admin inspects a User's profile.
    public static ArrayList<User> userList = new ArrayList<>();

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        DataHolder.admin = admin;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        DataHolder.login = login;
    }
}
