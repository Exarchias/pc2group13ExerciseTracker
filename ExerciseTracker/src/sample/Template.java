package sample;

public class Template {
    private  int templateID;
    private String title;
    private String description;

    public Template(int templateID, String title, String description) {
        this.templateID = templateID;
        this.title = title;
        this.description = description;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
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
