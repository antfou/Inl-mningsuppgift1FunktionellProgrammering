package Shoeshop.Client;

import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Shoe;
import Shoeshop.Server.Repository;

import java.util.List;

public class ClientMethods {
    Repository repository;
    public ClientMethods(){
        repository = new Repository();
    }


    public String userLogin(String userFirstName, String userPassword){
        Customer tempCustomer = repository.getCustomerFromDatabaseUsingFirstName(userFirstName);

        if(userPassword.equals(tempCustomer.getPassword())){
            return "Välkommen: "+tempCustomer.getFirstName()+" "+tempCustomer.getLastName();
        }
        return "Fel användarnamn eller lösenord";
    }


    public String getStringOfInventory(List<Shoe> shoesList){
        //return shoesList.stream().filter(shoe -> shoe.getAmountInStock() > 0).forEach(shoe -> "hej" + shoe.getId());
        return null;
    }
}
