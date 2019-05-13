package sample;

public class Recipe {
    private String title;
    private String description;
    private int owner;

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
