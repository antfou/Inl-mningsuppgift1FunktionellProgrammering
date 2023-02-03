package Shoeshop.Rapports;

import Shoeshop.Database.Repository;

import java.util.Scanner;

public class Rapports {

    protected final RapportMethods rapportMethods = new RapportMethods();
    protected final Repository repository = new Repository();

    public void rapport1(){
        final Scanner sc = new Scanner(System.in);
        System.out.println("Vilken kategori vill du söka upp?");
        System.out.println("(1) - Storlek");
        System.out.println("(2) - Märke");
        System.out.println("(3) - Färg");
        final int tempInt = Integer.parseInt(sc.nextLine());
        System.out.println("Sök: ");
        final String tempString = sc.nextLine();

        switch (tempInt){
            case 1 -> rapportMethods.rCustomerNames(rapportMethods.rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,rapportMethods.sizeSearch));
            case 2 -> rapportMethods.rCustomerNames(rapportMethods.rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,rapportMethods.brandSearch));
            case 3 -> rapportMethods.rCustomerNames(rapportMethods.rFilterShoesInOrder(repository.getOrderListFromDatabase(),tempString,rapportMethods.colorSearch));
        }
    }
}
