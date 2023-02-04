package Shoeshop.Objects;

public class Category {
    protected final String categoryName;
    protected final int categoryId;

    public Category(final String categoryName,
                    final int categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
