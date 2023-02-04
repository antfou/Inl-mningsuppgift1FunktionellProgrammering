package Shoeshop.Rapports;

import Shoeshop.Interfaces.OrderSearchInterface;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Database.Repository;
import Shoeshop.Objects.Shoe;

import java.util.List;

public class RapportMethods {
    final Repository repository = new Repository();

    final OrderSearchInterface sizeSearch = (a, b) -> a
            .getOrderedShoes()
            .stream()
            .anyMatch(c ->
                    c.getSize() == Integer
                            .parseInt(b));
    final OrderSearchInterface brandSearch = (a, b) -> a
            .getOrderedShoes().stream()
            .anyMatch(c ->
                    c.getBrand()
                            .getBrandName()
                            .equalsIgnoreCase(b));
    final OrderSearchInterface colorSearch = (a, b) -> a
            .getOrderedShoes()
            .stream()
            .anyMatch(c ->
                    c.getColor()
                            .getColorName()
                            .equalsIgnoreCase(b));


    public void rPrintAllCustomersWhoOrderedShoe(final List<Customer> list) {
        list.stream()
                .map(Customer::toString)
                .distinct()
                .forEach(System.out::println);
    }

    public List<Customer> rFilterShoesInOrder(final List<Order> orderList,
                                              final String wordToSearchFor,
                                              final OrderSearchInterface osi) {
        return orderList
                .stream()
                .filter(s -> osi
                        .search(s, wordToSearchFor))
                .map(Order::getCustomer)
                .toList();
    }

    public void rPrintCustomerAndAmountOfOrders(final List<Customer> list) {
        list.stream()
                .map(Customer::getFirstName)
                .distinct()
                .toList()
                .stream()
                .map(s -> s + " " +
                        getCustomerLasNameUsingFirstName(s) +
                        " || Antal Ordrar: " +
                        rGetAmoutOfDistinctOrders(
                                repository.getOrderListFromDatabaseUsingCustomerId(
                                        repository.getCustomerFromDatabaseUsingFirstName(s)
                                                .getId()))).forEach(System.out::println);
    }

    public void rPrintCustomersAndAmoutSpent(final List<Customer> list) {
        list.stream()
                .map(Customer::getFirstName)
                .distinct()
                .toList()
                .stream()
                .map(s -> s + " " +
                        getCustomerLasNameUsingFirstName(s) +
                        " || Totalt Spenderat: " +
                        amountOfMoneyInMultipleOrders
                                (repository.getOrderListFromDatabaseUsingCustomerId
                                        (repository.getCustomerFromDatabaseUsingFirstName(s)
                                                .getId()))).forEach(System.out::println);

    }

    public int rGetAmoutOfDistinctOrders(final List<Order> list) {
        return (int) list.stream()
                .map(s ->
                        s.getId())
                .toList()
                .stream()
                .distinct()
                .count();
    }

    public String getCustomerLasNameUsingFirstName(final String name) {
        return repository
                .getCustomerFromDatabaseUsingFirstName(name)
                .getLastName();
    }

    public int rAmountOfMoneyInAShoeList(final List<Shoe> list) {
        return list.stream()
                .mapToInt(Shoe::getPrice)
                .sum();
    }

    public List<List<Shoe>> rPutMultipleShoeListsInSameList(final List<Order> list) {
        return list.stream()
                .map(Order::getOrderedShoes)
                .distinct()
                .toList();
    }

    public int rAmountOfMoneyInMultipleOrders(final List<List<Shoe>> listList) {
        return listList.stream()
                .mapToInt(this::rAmountOfMoneyInAShoeList)
                .distinct()
                .sum();
    }

    public int amountOfMoneyInMultipleOrders(final List<Order> list) {
        return rAmountOfMoneyInMultipleOrders
                (rPutMultipleShoeListsInSameList(list));
    }
}