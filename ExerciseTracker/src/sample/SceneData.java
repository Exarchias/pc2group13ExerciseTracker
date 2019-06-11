package sample;

public class SceneData {
    static private SceneData instance = new SceneData();


    private Object data = null;

    private SceneData() {
    }

    public static SceneData getInstance() {
        return instance;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
