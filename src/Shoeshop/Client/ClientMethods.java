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


    public String userLogin(String userFirstName, String userPassword){
        Customer tempCustomer = repository.getCustomerFromDatabaseUsingFirstName(userFirstName);

        if(userPassword.equals(tempCustomer.getPassword())){
            return "Välkommen: "+tempCustomer.getFirstName()+" "+tempCustomer.getLastName();
        }
        return "Fel användarnamn eller lösenord";
    }


    public void displayInventoryToCustomer(List<Shoe> shoesList){
        shoesList.stream(). filter(shoe -> shoe.getAmountInStock()>0). forEach(shoe -> System.out.println("Sko nr:"+shoe.getId()+" Märke "+shoe.getBrand().getBrandName() +" -Färg: "+shoe.getColor().getColorName()+" -Storlek: "+shoe.getSize()+" -Antal:"+shoe.getAmountInStock()));
    }

    public String janisHeltFelKod(List<Shoe> shoesList){
        return shoesList.stream().map(Shoe::getBrand).map(Brand::getBrandName).distinct()
                .collect(Collectors.joining(", ", "" , ""));
    }
}
