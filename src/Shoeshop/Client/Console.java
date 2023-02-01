package Shoeshop.Client;

import Shoeshop.Logic.ActiveUser;
import Shoeshop.Logic.ChosenProduct;
import Shoeshop.Objects.Customer;
import Shoeshop.Server.Repository;

import java.util.Scanner;

public class Console {
    Repository repository;
    ClientMethods clientMethods;
    Scanner sc;
    Customer user;
    ActiveUser activeUser;
    ChosenProduct chosenProduct;
    public Console() throws InterruptedException {
        repository = new Repository();
        clientMethods = new ClientMethods();

        activeUser = ActiveUser.LOGGED_OUT;
        chosenProduct = ChosenProduct.NOT_IN_STOCK;

        while(activeUser == ActiveUser.LOGGED_OUT) {
            user = login();
            //Thread.sleep(1000);
        }

        while(chosenProduct == ChosenProduct.NOT_IN_STOCK) {
            clientMethods.displayInventoryToCustomer(repository.getListOfAllShoesAndAmountInStock());
            System.out.println(chooseProduct());
        }
    }



    public Customer login(){
        sc = new Scanner(System.in);

        System.out.println("Vänligen logga in:");
        System.out.println("Förnamn: ");
        String userName = sc.nextLine();

        System.out.println("Lösenord: ");
        String password = sc.nextLine();

        System.out.println(clientMethods.userLogin(userName,password));

        if(clientMethods.userLogin(userName,password).startsWith("Välkommen:")){
            activeUser = ActiveUser.LOGGED_IN;
            return repository.getCustomerFromDatabaseUsingFirstName(userName);
        }
        return null;
    }



    public int chooseProduct(){
        sc = new Scanner(System.in);
        System.out.println("Skriv in skonummret av skon du vill lägga till i din Cart: ");

        int shoe = sc.nextInt();

        if(shoe>repository.getListOfAllShoesAndAmountInStock().size()){
            System.out.println("vi har inte sko nr: "+shoe);
            return 0;
        }
        if(shoe<0){
            System.out.println("Vi har inga skor under: ");
            return 0;
        }
        chosenProduct = ChosenProduct.IN_STOCK;
        return sc.nextInt();
    }



    public static void main(String[] args) throws InterruptedException {
        new Console();
    }
}
