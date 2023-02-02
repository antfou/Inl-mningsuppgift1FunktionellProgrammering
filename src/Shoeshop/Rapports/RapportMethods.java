package Shoeshop.Rapports;

import Shoeshop.Interfaces.OrderSearchInterface;
import Shoeshop.Objects.Address;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Server.Repository;

import java.util.List;
import java.util.Scanner;

public class RapportMethods {
    final Repository repository = new Repository();

    final OrderSearchInterface sizeSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getSize() == Integer.parseInt(b));
    final OrderSearchInterface brandSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getBrand().getBrandName().equalsIgnoreCase(b));
    final OrderSearchInterface colorSearch = (a,b)->a.getOrderedShoes().stream().anyMatch(c->c.getColor().getColorName().equalsIgnoreCase(b));

    public void rapport1(){
        final Scanner sc = new Scanner(System.in);
        System.out.println("Vilken kategori vill du söka upp?");
        System.out.println("(1) - Storlek");
        System.out.println("(2) - Märke");
        System.out.println("(3) - Färg");
        final int tempInt = Integer.parseInt(sc.nextLine());
        System.out.println("Sök: ");
        final String tempString = sc.nextLine();

        switch (tempInt){
            case 1 -> rCustomerNames(rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,sizeSearch));
            case 2 -> rCustomerNames(rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,brandSearch));
            case 3 -> rCustomerNames(rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,colorSearch));
        }
    }

    public void rCustomerNames(List<Customer> list){
        list.stream().map(Customer::toString).distinct().forEach(System.out::println);
    }

    public List<Customer> rFilterShoesInOrder(List<Order> orderList, String wordToSearchFor, OrderSearchInterface osi){
        return orderList.stream().filter(s -> osi.search(s, wordToSearchFor)).map(Order::getCustomer).toList();
    }
}