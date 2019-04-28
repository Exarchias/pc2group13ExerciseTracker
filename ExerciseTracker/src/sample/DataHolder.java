package sample;

public class DataHolder {
    private static boolean admin = false;
    private static boolean login = false;

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
