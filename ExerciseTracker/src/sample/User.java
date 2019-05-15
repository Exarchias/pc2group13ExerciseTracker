package sample;

import java.util.ArrayList;

public class User {
    private String userName;
    private String passWord;
    private String email;
    private boolean isAnAdmin;
    public ArrayList<Exercise> exerciseList = new ArrayList<>();
    public  ArrayList<Recipe> recipeList = new ArrayList<>();

    public User(String userName, String passWord, String email, Boolean isAdmin){
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.isAnAdmin = isAdmin;
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

    public void setAnAdmin(boolean anAdmin) {
        isAnAdmin = anAdmin;
    }
}
