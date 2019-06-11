package sample;

import java.util.ArrayList;

public class User {
    private String userName;
    private String passWord;
    private String email;
    private boolean isAnAdmin;
    private int weight;
    private int userID;
    public ArrayList<Exercise> exerciseList = new ArrayList<>();
    public ArrayList<Recipe> recipeList = new ArrayList<>();

    public User(String userName, String passWord, String email, Boolean isAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.isAnAdmin = isAdmin;
    }

    public User(String userName, String passWord, String email, boolean isAnAdmin, int weight, int userID) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.isAnAdmin = isAnAdmin;
        this.weight = weight;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAnAdmin() {
        return isAnAdmin;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setAnAdmin(boolean anAdmin) {
        isAnAdmin = anAdmin;
    }


}
