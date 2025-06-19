package model;

public class Category {
    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String categoryName) {
        if (categoryName == null) {
            throw new IllegalArgumentException("Category name cannot be null.");
        }
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    @Override
    public String toString() {
        return "Category{" +
               "categoryId=" + categoryId +
               ", categoryName='" + categoryName + '\'' +
               '}';
    }
}