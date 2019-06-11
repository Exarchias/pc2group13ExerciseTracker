package sample;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private String description;
    private int owner;
    private int recipeID;
    private boolean isPublic = false;
    ArrayList<Exercise> exerciseList = new ArrayList<>();

    //This one includes the public recipes functionality.
    public Recipe(String title, String description, int owner, int recipeID, boolean isPublic) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.recipeID = recipeID;
        this.isPublic = isPublic;
    }

    //this constructor is according to the EER.
    public Recipe(String title, String description, int owner, int recipeID) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.recipeID = recipeID;
    }

    public Recipe(String title, String description, int owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
