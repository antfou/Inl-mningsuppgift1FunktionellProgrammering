package Shoeshop.Rapports;

import Shoeshop.Interfaces.OrderSearchInterface;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Database.Repository;

import java.util.List;

public class RapportMethods {
    final Repository repository = new Repository();

    final OrderSearchInterface sizeSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getSize() == Integer.parseInt(b));
    final OrderSearchInterface brandSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getBrand().getBrandName().equalsIgnoreCase(b));
    final OrderSearchInterface colorSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getColor().getColorName().equalsIgnoreCase(b));



    public void rCustomerNames(List<Customer> list){
        list.stream().map(Customer::toString).distinct().forEach(System.out::println);
    }

    public List<Customer> rFilterShoesInOrder(List<Order> orderList, String wordToSearchFor, OrderSearchInterface osi){
        return orderList.stream().filter(s -> osi.search(s, wordToSearchFor)).map(Order::getCustomer).toList();
    }
}