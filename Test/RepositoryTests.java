import Shoeshop.Server.Repository;
import org.junit.jupiter.api.Test;

public class RepositoryTests {
    @Test
    void getCustomerFromDatabaseUsingFirstNameTest(){
        Repository r = new Repository();
        assert(r.getCustomerFromDatabaseUsingFirstName("Tobbe").getAddress().getAddressName().equals("Gatgatan 2"));
    }
    @Test
    void getCustomerFromDatabaseUsingIdTest(){
        Repository r = new Repository();
        assert(r.getCustomerFromDatabaseUsingId(1).getAddress().getAddressName().equals("Gatgatan 2"));
    }


    @Test
    void getListOfAllShoesAndAmountInStockTest(){
        Repository r = new Repository();
        assert(r.getListOfAllShoesAndAmountInStock().get(0).getBrand().getBrandName().equals("Ecco"));
    }

    @Test
    void addCategoriesToListTest(){
        Repository r = new Repository();
        assert(r.addCategoriesToList(1).get(0).getCategoryName().equals("Herrsko"));
    }

    @Test
    void addShoesToListUsingOrderIdTest(){
        Repository r = new Repository();
        assert(r.addShoesToListUsingOrderId(1).get(0).getBrand().getBrandName().equals("Adidas"));
    }


    @Test
    void getOrderListFromDatabaseUsingCustomerIdTest(){
        Repository r = new Repository();
        assert(r.getOrderListFromDatabaseUsingCustomerId(1).get(0).getOrderedShoes().get(0).getId() == 3);
    }
}