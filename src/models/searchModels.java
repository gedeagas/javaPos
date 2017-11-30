package models;

/**
 * Created by gedeagas on 27/11/17.
 */
public class searchModels {
    private int searchId;

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public searchModels(int searchId, int searchEan, String searchName, long searchPrice) {

        this.searchId = searchId;
        this.searchEan = searchEan;
        this.searchName = searchName;
        this.searchPrice = searchPrice;
    }

    private int searchEan;
    private String searchName;
    private long searchPrice;

    public searchModels(int searchEan, String searchName, long searchPrice) {
        this.searchEan = searchEan;
        this.searchName = searchName;
        this.searchPrice = searchPrice;
    }

    public int getSearchEan() {

        return searchEan;
    }

    public void setSearchEan(int searchEan) {
        this.searchEan = searchEan;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public long getSearchPrice() {
        return searchPrice;
    }

    public void setSearchPrice(long searchPrice) {
        this.searchPrice = searchPrice;
    }
}
