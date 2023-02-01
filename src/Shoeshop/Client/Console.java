package Shoeshop.Client;

import Shoeshop.Server.Repository;

public class Console {
    Repository repository;
    ClientMethods clientMethods;
    public Console(){
         repository = new Repository();
         clientMethods = new ClientMethods();
         clientMethods.displayInventoryToCustomer(repository.getListOfAllShoesAndAmountInStock());
    }



    public static void main(String[] args) {
        new Console();
    }
}
