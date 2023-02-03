import Shoeshop.Console.Methods;
import Shoeshop.Database.Repository;
import org.junit.jupiter.api.Test;

public class ClientTests {

    @Test
    void userLoginTest(){
        Methods c = new Methods();
        assert(c.userLogin("Leroy","Pokemon97").equals("Välkommen: Leroy Collazo"));
    }

    @Test
    void getStringWithAllBrandNamesTest(){
        Repository r = new Repository();
        Methods c = new Methods();
        assert(c.getStringWithAllBrandNames(r.getListOfAllShoesAndAmountInStock()).equals("Ecco, Adidas, Nike, Timberlands, Puma"));
    }

    @Test
    void getCustomerUsingFirstNameTest(){
        Repository r = new Repository();
        Methods c = new Methods();
        assert(c. getCustomerUsingFirstName(r.getCustomerListFromDatabase(),"Tobbe").getId()==1);
    }
}
