package Shoeshop.Interfaces;

import Shoeshop.Objects.Order;
import Shoeshop.Objects.Shoe;

@FunctionalInterface
public interface OrderSearchInterface {
    boolean search(Order order, String string);
}
