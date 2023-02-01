import Shoeshop.Server.Repository;
import org.junit.jupiter.api.Test;

public class RepositoryTests {
    @Test
    void getCustomerFromDatabaseUsingFirstNameTest(){
        Repository r = new Repository();
        assert(r.getCustomerFromDatabaseUsingFirstName("Tobbe").getAddress().getAddressName().equals("Gatgatan 2"));
    }


    @Test
    void getListOfAllShoesAndAmountInStockTest(){
        Repository r = new Repository();
        assert(r.getListOfAllShoesAndAmountInStock().get(0).getBrand().getBrandName().equals("Ecco"));
    }

}