package models;

/**
 * Created by gedeagas on 23/11/17.
 */
public class categoryModels {
    private int categoryId;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public categoryModels(int categoryId, String categoryName) {

        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
