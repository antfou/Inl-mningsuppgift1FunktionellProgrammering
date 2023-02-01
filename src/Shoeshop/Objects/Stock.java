package Shoeshop.Objects;
//TODO kanske inte behöver denna klass
public class Stock {
    protected int id;
    protected Shoe shoe;
    protected int amountInStock;

    public Stock(int id,Shoe shoe, int amountInStock) {
        this.id = id;
        this.shoe = shoe;
        this.amountInStock = amountInStock;
    }

    public int getId() {
        return id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public int getAmountInStock() {
        return amountInStock;
    }
}
