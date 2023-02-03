package Shoeshop.Console;

import Shoeshop.Enums.ActiveUser;
import Shoeshop.Enums.ChosenProduct;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Shoe;
import Shoeshop.Rapports.Rapports;
import Shoeshop.Database.Repository;

import java.util.Scanner;

public class Console {
    protected final Repository repository;
    protected final Methods methods;
    protected final Rapports rapports;

    protected Customer user;
    ActiveUser activeUser;
    ChosenProduct chosenProduct;

    public Console() throws InterruptedException {
        repository = new Repository();
        methods = new Methods();
        rapports = new Rapports();


        //rapports.rapport1();




        activeUser = ActiveUser.LOGGED_OUT;
        chosenProduct = ChosenProduct.NOT_IN_STOCK;

        while(activeUser == ActiveUser.LOGGED_OUT) {
            user = login();
        }
        Shoe shoe = chooseProduct();

        repository.callAddToCart(user.getId(), methods.chooseOrderToAddShoe(methods.newOrderPrompt(), user.getId()), shoe.getId());
        System.out.println("Added to cart: Sko nr:"+shoe.getId()+" Märke "+shoe.getBrand().getBrandName() +" -Färg: "+shoe.getColor().getColorName()+" -Storlek: "+shoe.getSize());
    }



    public Customer login(){
        final Scanner sc = new Scanner(System.in);

        System.out.println("Vänligen logga in:");
        System.out.println("Förnamn: ");
        String userName = sc.nextLine();

        System.out.println("Lösenord: ");
        String password = sc.nextLine();

        if(methods.userLogin(userName,password) != null){
            activeUser = ActiveUser.LOGGED_IN;
            System.out.println("Välkommen " + userName);
            return (methods.userLogin(userName,password));
        }
        System.out.println("Felaktigt användarnamn eller lösenord");
        return null;
    }



    public Shoe chooseProduct(){
        final Scanner sc = new Scanner(System.in);

        methods.displayInventoryToCustomer(repository.getListOfAllShoesAndAmountInStock());

        while(chosenProduct==ChosenProduct.NOT_IN_STOCK) {
            System.out.println("Skriv in skonummret av skon du vill lägga till i din Cart: ");
            int tempInt = sc.nextInt();
            if(methods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt)!=null && methods.checkIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt)){
                chosenProduct=ChosenProduct.IN_STOCK;
                return methods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(),tempInt);
            }
            System.out.println("Den skon har vi tyvär inte.");
        }
        return null;
    }



    public static void main(String[] args) throws InterruptedException {
        new Console();
    }
}
