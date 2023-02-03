package Shoeshop.Client;

import Shoeshop.Logic.ActiveUser;
import Shoeshop.Logic.ChosenProduct;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Shoe;
import Shoeshop.Rapports.RapportMethods;
import Shoeshop.Server.Repository;

import java.util.Scanner;

public class Console {
    Repository repository;
    ClientMethods clientMethods;
    Scanner sc;
    Customer user;
    ActiveUser activeUser;
    ChosenProduct chosenProduct;
    RapportMethods rapportMethods = new RapportMethods();
    //TODO: lägg alla classes som final
    public Console() throws InterruptedException {
        repository = new Repository();
        clientMethods = new ClientMethods();
        rapportMethods = new RapportMethods();

        //rapportMethods.rapport1();




        activeUser = ActiveUser.LOGGED_OUT;
        chosenProduct = ChosenProduct.NOT_IN_STOCK;

        while(activeUser == ActiveUser.LOGGED_OUT) {
            user = login();
        }
        Shoe shoe = chooseProduct();

        repository.callAddToCart(user.getId(), clientMethods.chooseOrderToAddShoe(clientMethods.newOrderPrompt(), user.getId()), shoe.getId());
        System.out.println("Added to cart: Sko nr:"+shoe.getId()+" Märke "+shoe.getBrand().getBrandName() +" -Färg: "+shoe.getColor().getColorName()+" -Storlek: "+shoe.getSize());
    }



    public Customer login(){
        sc = new Scanner(System.in);

        System.out.println("Vänligen logga in:");
        System.out.println("Förnamn: ");
        String userName = sc.nextLine();

        System.out.println("Lösenord: ");
        String password = sc.nextLine();

        if(clientMethods.userLogin(userName,password) != null){
            activeUser = ActiveUser.LOGGED_IN;
            System.out.println("Välkommen " + userName);
            return (clientMethods.userLogin(userName,password));
        }
        System.out.println("Felaktigt användarnamn eller lösenord");
        return null;
    }



    public Shoe chooseProduct(){
        sc = new Scanner(System.in);

        clientMethods.displayInventoryToCustomer(repository.getListOfAllShoesAndAmountInStock());

        while(chosenProduct==ChosenProduct.NOT_IN_STOCK) {
            System.out.println("Skriv in skonummret av skon du vill lägga till i din Cart: ");
            int tempInt = sc.nextInt();
            if(clientMethods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt)!=null && clientMethods.checkIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt)){
                chosenProduct=ChosenProduct.IN_STOCK;
                return clientMethods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt);
            }
            System.out.println("Den skon har vi tyvär inte.");
        }
        return null;
    }



    public static void main(String[] args) throws InterruptedException {
        new Console();
    }
}
