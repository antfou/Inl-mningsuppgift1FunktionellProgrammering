package Shoeshop.Objects;

import java.util.Date;
import java.util.List;

public class Order {

    protected final int id;
    protected final Date orderDate;
    protected final Customer customer;
    protected final List<Shoe> orderedShoes;

    public Order(final int id,
                 final Date orderDate,
                 final Customer customer,
                 final List<Shoe> orderedShoes) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderedShoes = orderedShoes;
    }

    public int getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Shoe> getOrderedShoes() {
        return orderedShoes;
    }
}
