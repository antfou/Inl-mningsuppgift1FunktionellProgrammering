package Shoeshop.Console;

import Shoeshop.Objects.Brand;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Objects.Shoe;
import Shoeshop.Database.Repository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Methods {
    protected final Repository repository;

    public Methods() {
        repository = new Repository();
    }


    public Customer userLogin(final String userFirstName, final String userPassword) {
        final Customer tempCustomer = repository.
                getCustomerFromDatabaseUsingFirstName(userFirstName);
        if (tempCustomer != null) {
            if (userPassword.equals
                    (tempCustomer.getPassword())) {
                return tempCustomer;
            }
        }
        return null;
    }


    public Shoe fetchShoeIfInStock(final List<Shoe> shoeList, final int id) {
        if (id <= 0) {
            return null;
        } else if (id > shoeList.size()) {
            return null;
        } else {
            return shoeList.get(id - 1);
        }
    }


    public void displayInventoryToCustomer(final List<Shoe> shoesList) {
        shoesList
                .stream()
                .filter(shoe -> shoe.getAmountInStock() > 0).
                forEach(shoe -> System.out.println
                        ("Sko nr:" + shoe.getId() +
                                " Märke " +
                                shoe.getBrand().getBrandName() +
                                " -Färg: " +
                                shoe
                                        .getColor()
                                        .getColorName() +
                                " -Storlek: " +
                                shoe.getSize() +
                                " -Antal:" + shoe
                                .getAmountInStock()));
    }


    public String getStringWithAllBrandNames(final List<Shoe> shoesList) {
        return shoesList
                .stream()
                .map(Shoe::getBrand)
                .map(Brand::getBrandName)
                .distinct()
                .collect
                        (Collectors.
                                joining(", ", "", ""));
    }


    public Customer getCustomerUsingFirstName(final List<Customer> customers, final String name) {
        return customers
                .stream()
                .filter(c -> c.getFirstName()
                        .equals(name))
                .toList()
                .get(0);
    }


    public boolean checkIfInStock(final List<Shoe> shoeList, final int id) {
        return shoeList
                .stream()
                .filter(shoe -> shoe
                        .getAmountInStock() > 0)
                .toList()
                .stream()
                .anyMatch(s -> s
                        .getId() == id);
    }


    public int chooseOrderToAddShoe(final boolean newOrder, final int customerId) {
        if (newOrder) {
            return 999;
        } else {
            return repository
                    .getOrderListFromDatabaseUsingCustomerId(customerId)
                    .stream()
                    .mapToInt(Order::getId)
                    .distinct()
                    .max()
                    .getAsInt();
        }
    }


    public boolean newOrderPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Är det här en ny beställning?");
        System.out.println("(1) - Ja, gör en ny beställning");
        System.out.println("(2) - Nej, lägg till produkten i min senaste beställning");
        final int tempInt = Integer.parseInt(sc.nextLine());
        if (tempInt == 1) {
            return true;
        } else
            return false;
    }
}

