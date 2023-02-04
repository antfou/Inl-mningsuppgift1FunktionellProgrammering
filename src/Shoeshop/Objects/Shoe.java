package Shoeshop.Objects;

import java.util.List;

public class Shoe {
    protected final int id;
    protected final Brand brand;
    protected final int size;
    protected final Color color;
    protected final int price;
    protected final int amountInStock;
    protected final List<Category> categories;

    public Shoe(final int id,
                final Brand brand,
                final int size,
                final Color color,
                final int price,
                final int amountInStock,
                final List<Category> categories) {
        this.id = id;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
        this.amountInStock = amountInStock;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
