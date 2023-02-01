import Shoeshop.Client.ClientMethods;
import Shoeshop.Client.Console;
import Shoeshop.Server.Repository;
import org.junit.jupiter.api.Test;

public class ClientTests {

    @Test
    void userLoginTest(){
        ClientMethods c = new ClientMethods();
        assert(c.userLogin("Leroy","Pokemon97").equals("Välkommen: Leroy Collazo"));
    }

    @Test
    void getStringWithAllBrandNamesTest(){
        Repository r = new Repository();
        ClientMethods c = new ClientMethods();
        assert(c.getStringWithAllBrandNames(r.getListOfAllShoesAndAmountInStock()).equals("Ecco, Adidas, Nike, Timberlands, Puma"));
    }
}
