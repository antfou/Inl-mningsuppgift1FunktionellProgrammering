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
    void getStringOfInventoryTest(){
        ClientMethods c = new ClientMethods();
        Repository r = new Repository();
        assert(c.getStringOfInventory(r.getListOfAllShoesAndAmountInStock()).equals(""));
    }
}
