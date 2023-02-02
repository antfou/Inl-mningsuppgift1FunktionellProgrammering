package Shoeshop.Client;

import Shoeshop.Objects.Brand;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Shoe;
import Shoeshop.Server.Repository;

import java.util.List;
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
        shoesList.stream(). filter(shoe -> shoe.getAmountInStock()>0). forEach(shoe -> System.out.println("Sko nr:"+shoe.getId()+" Märke "+shoe.getBrand().getBrandName() +" -Färg: "+shoe.getColor().getColorName()+" -Storlek: "+shoe.getSize()+" -Antal:"+shoe.getAmountInStock()));
    }

    public String getStringWithAllBrandNames(List<Shoe> shoesList){
        return shoesList.stream().map(Shoe::getBrand).map(Brand::getBrandName).distinct()
                .collect(Collectors.joining(", ", "" , ""));
    }

    public Customer getCustomerUsingFirstName(List<Customer> customers, String name){
        return customers.stream().filter(c->c.getFirstName().equals(name)).toList().get(0);
    }
}
