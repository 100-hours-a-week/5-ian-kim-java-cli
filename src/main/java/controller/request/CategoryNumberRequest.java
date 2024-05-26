package controller.request;

public class CategoryNumberRequest {
    private int categoryNumber;

    public CategoryNumberRequest(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }
}
