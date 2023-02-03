package Shoeshop.Client;

import Shoeshop.Objects.Brand;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Objects.Shoe;
import Shoeshop.Server.Repository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientMethods {
    Repository repository;
    public ClientMethods(){
        repository = new Repository();
    }


    public Customer userLogin(String userFirstName, String userPassword){
        Customer tempCustomer = repository.getCustomerFromDatabaseUsingFirstName(userFirstName);
        if (tempCustomer!=null) {
            if (userPassword.equals(tempCustomer.getPassword())) {
                return tempCustomer;
            }
        }
        return null;
    }

    public Shoe fetchShoeIfInStock(List<Shoe> shoeList, int id){
        if(id<=0){
            return null;
        } else if (id>shoeList.size()) {
            return null;
        }else{
            return shoeList.get(id-1);
        }
    }


    public void displayInventoryToCustomer(List<Shoe> shoesList){
        shoesList.stream().filter(shoe -> shoe.getAmountInStock()>0).forEach(shoe -> System.out.println("Sko nr:"+shoe.getId()+" Märke "+shoe.getBrand().getBrandName() +" -Färg: "+shoe.getColor().getColorName()+" -Storlek: "+shoe.getSize()+" -Antal:"+shoe.getAmountInStock()));
    }

    public String getStringWithAllBrandNames(List<Shoe> shoesList){
        return shoesList.stream().map(Shoe::getBrand).map(Brand::getBrandName).distinct()
                .collect(Collectors.joining(", ", "" , ""));
    }

    public Customer getCustomerUsingFirstName(List<Customer> customers, String name){
        return customers.stream().filter(c->c.getFirstName().equals(name)).toList().get(0);
    }

    public boolean checkIfInStock(List<Shoe> shoeList, int id){
        return shoeList.stream().filter(shoe -> shoe.getAmountInStock()>0).toList().stream().anyMatch(s->s.getId()==id);
    }


    public int chooseOrderToAddShoe(boolean newOrder,int customerId){
        if(newOrder){
            return 999;
        } else {
            return repository.getOrderListFromDatabaseUsingCustomerId(customerId).stream().mapToInt(Order::getId).distinct().max().getAsInt();
        }
    }

    public boolean newOrderPrompt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Är det här en ny beställning?");
        System.out.println("(1) - Ja, gör en ny beställning");
        System.out.println("(2) - Nej, lägg till produkten i min senaste beställning");
        final int tempInt = Integer.parseInt(sc.nextLine());
        if(tempInt==1){
            return true;
        }else
            return false;
        }
    }

