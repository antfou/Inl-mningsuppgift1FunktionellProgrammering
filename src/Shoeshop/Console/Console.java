package Shoeshop.Console;

import Shoeshop.Enums.ActiveUser;
import Shoeshop.Enums.ChosenProduct;
import Shoeshop.Enums.RapportMode;
import Shoeshop.Enums.SupportMode;
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
    protected ActiveUser activeUser;
    protected ChosenProduct chosenProduct;
    protected SupportMode supportMode;
    RapportMode rapportMode;


    public Console() throws InterruptedException {
        repository = new Repository();
        methods = new Methods();
        rapports = new Rapports();
        activeUser = ActiveUser.LOGGED_OUT;
        chosenProduct = ChosenProduct.NOT_IN_STOCK;
        bootup();
        while (activeUser == ActiveUser.LOGGED_OUT) {
            user = login();
        }
        Shoe shoe = chooseProduct();
        repository.callAddToCart(user.getId(),
                methods.chooseOrderToAddShoe(methods.newOrderPrompt(),
                        user.getId()), shoe.getId());
        System.out.println("Added to cart: Sko nr:"
                + shoe.getId() + " Märke "
                + shoe.getBrand().getBrandName()
                + " -Färg: " + shoe.getColor().getColorName()
                + " -Storlek: " + shoe.getSize());
    }


    public void bootup() {
        final Scanner sc = new Scanner(System.in);
        supportMode = SupportMode.SUPPORT_MODE_ON;
        while (supportMode == supportMode.SUPPORT_MODE_ON) {
            System.out.println("Välkommen: ");
            System.out.println("Vad vill du göra?");
            System.out.println("(1) - kolla på rapporter");
            System.out.println("(2) - göra en beställning");
            int tempInt = Integer.parseInt(sc.nextLine());
            switch (tempInt) {
                case 1 -> Rapport();
                case 2 -> supportMode = SupportMode.SUPPORT_MODE_OFF;
            }
        }
    }


    public void Rapport() {
        final Scanner sc = new Scanner(System.in);
        rapportMode = RapportMode.RAPPORT_MODE_ON;
        while (rapportMode == RapportMode.RAPPORT_MODE_ON) {
            System.out.println("Vilken rapport vill du se?");
            System.out.println("(1) - Kunder som handlat en viss vara.");
            System.out.println("(2) - Alla kunder med antal ordrar");
            System.out.println("(3) - Alla kunder med summa spenderat");
            System.out.println("(4) - Main meny");
            int tempInt = Integer.parseInt(sc.nextLine());
            switch (tempInt) {
                case 1 -> rapports.rapport1();
                case 2 -> rapports.rapport2();
                case 3 -> rapports.rapport3();
                case 4 -> rapportMode = RapportMode.RAPPORT_MODE_OFF;
            }
        }
    }


    public Customer login() {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Vänligen logga in:");
        System.out.println("Förnamn: ");
        String userName = sc.nextLine();
        System.out.println("Lösenord: ");
        String password = sc.nextLine();
        if (methods.userLogin(userName, password) != null) {
            activeUser = ActiveUser.LOGGED_IN;
            System.out.println("Välkommen " + userName);
            return (methods.userLogin(userName, password));
        }
        System.out.println("Felaktigt användarnamn eller lösenord");
        return null;
    }


    public Shoe chooseProduct() {
        final Scanner sc = new Scanner(System.in);

        methods.displayInventoryToCustomer(repository.getListOfAllShoesAndAmountInStock());

        while (chosenProduct == ChosenProduct.NOT_IN_STOCK) {
            System.out.println("Skriv in skonummret av skon du vill lägga till i din Cart: ");
            int tempInt = sc.nextInt();
            if (methods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(), tempInt) != null && methods.checkIfInStock(repository.getListOfAllShoesAndAmountInStock(), tempInt)) {
                chosenProduct = ChosenProduct.IN_STOCK;
                return methods.fetchShoeIfInStock(repository.getListOfAllShoesAndAmountInStock(), tempInt);
            }
            System.out.println("Den skon har vi tyvär inte.");
        }
        return null;
    }


    public static void main(final String[] args) throws InterruptedException {
        new Console();
    }
}
