package sample;

import java.util.ArrayList;

public class Recipe {
    private String title;
    private String description;
    private int owner;
    ArrayList<Exercise> exerciseList = new ArrayList<>();

    public Recipe(String title, String description, int owner){
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
}
