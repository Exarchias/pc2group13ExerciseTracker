
package sample;


public class Exercise {
    private String title;
    private String description;
    private int exerciseID;
    private int owner;
    private int recipeID;
    private int repetitions;
    private int caloriesPerRepetition;

    public Exercise(String title, String description, int owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public Exercise(String title, String description, int exerciseID,
                    int owner, int recipeID, int repetitions, int caloriesPerRepetition) {
        this.title = title;
        this.description = description;
        this.exerciseID = exerciseID;
        this.owner = owner;
        this.recipeID = recipeID;
        this.repetitions = repetitions;
        this.caloriesPerRepetition = caloriesPerRepetition;
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

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getCaloriesPerRepetition() {
        return caloriesPerRepetition;
    }

    public void setCaloriesPerRepetition(int caloriesPerRepetition) {
        this.caloriesPerRepetition = caloriesPerRepetition;
    }

    //helping method that gives fair calories per repetition according the the weight
    public int calculateCaloriesPerWeight(Double weight) {
        weight = weight / 200;
        //ratio according the the weight
        int calories = (int) (caloriesPerRepetition * (1 + weight));
        return calories;
    }
}
