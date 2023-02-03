package Shoeshop.Database;

import Shoeshop.Objects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    Properties properties = new Properties();

    public Repository(){
        try{
            properties.load(new FileInputStream("src/Shoeshop/Server/Setttings.Properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public Customer getCustomerFromDatabaseUsingFirstName(String firstName){
        final String query = "select * from customer where customer.firstName = ?";
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));

            PreparedStatement stmt = c.prepareStatement(query);
        ){
            stmt.setString(1,firstName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address tempaddress = new Address(rs.getString("address"),rs.getString("city"));
                Customer tempCustomer = new Customer(rs.getInt("id"), rs.getString("firstName"),rs.getString("lastname"), rs.getString("password"),tempaddress);
                return tempCustomer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }


    public Customer getCustomerFromDatabaseUsingId(int id){
        final String query = "select * from customer where customer.id = ?";
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));

            PreparedStatement stmt = c.prepareStatement(query);
        ){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address tempaddress = new Address(rs.getString("address"),rs.getString("city"));
                Customer tempCustomer = new Customer(rs.getInt("id"), rs.getString("firstName"),rs.getString("lastname"), rs.getString("password"),tempaddress);
                return tempCustomer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Customer> getCustomerListFromDatabase(){
        final String query = "select * from customer";
        List<Customer> tempList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address tempaddress = new Address(rs.getString("address"),rs.getString("city"));
                Customer tempCustomer = new Customer(rs.getInt("id"), rs.getString("firstName"),rs.getString("lastname"), rs.getString("password"),tempaddress);
                tempList.add(tempCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempList;
    }





    public List<Shoe> getListOfAllShoesAndAmountInStock(){
        final String query = "select * from shoe inner join stock on stock.shoeId= shoe.id";
        List<Shoe> tempShoeList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tempShoeList.add(new Shoe(rs.getInt("id"),new Brand(rs.getString("brand")),
                        rs.getInt("Size"),new Color(rs.getString("color")),
                        rs.getInt("price"),rs.getInt("amount_in_stock")
                        ,addCategoriesToList(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempShoeList;
    }



    public void callAddToCart(int customerId, int customerOrderId, int shoeId){
        final String query = "call addToCart(?,?,?)";
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));

            CallableStatement stmt = c.prepareCall(query);
        ){
            stmt.setInt(1,customerId);
            stmt.setInt(2,customerOrderId);
            stmt.setInt(3,shoeId);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public List<Category> addCategoriesToList(int shoeId){
        String query = "select * from category inner join shoe_category on shoe_category.categoryId = category.id inner join shoe on shoe_category.shoeid = shoe.id where shoe.id = ?";
        List<Category> tempCategoryList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            stmt.setInt(1,shoeId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Category tempCategory = new Category(rs.getString("name"),rs.getInt("id"));
                tempCategoryList.add(tempCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempCategoryList;
    }


    public List<Shoe> addShoesToListUsingOrderId(int orderId){
        String query = "select * from shoe inner join product_customer_order on shoe.id = product_customer_order.shoeId inner join stock on stock.shoeId= shoe.id where product_customer_order.customer_ordersID = ?";
        List<Shoe> tempShoeList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            stmt.setInt(1,orderId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tempShoeList.add(new Shoe(rs.getInt("id"),new Brand(rs.getString("brand")),
                        rs.getInt("Size"),new Color(rs.getString("color")),
                        rs.getInt("price"),rs.getInt("amount_in_stock")
                        ,addCategoriesToList(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempShoeList;
    }


    public List<Order> getOrderListFromDatabaseUsingCustomerId(int customerId){
        final String query = "select * from customer_order inner join product_customer_order on customer_order.id = product_customer_order.customer_ordersID where customer_order.customerId = ?";
        List<Order> tempOrderList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            stmt.setInt(1,customerId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tempOrderList.add(new Order(rs.getInt("id"),rs.getDate("order_date"),
                        getCustomerFromDatabaseUsingId(customerId),addShoesToListUsingOrderId(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempOrderList;
    }



    public List<Order> getOrderListFromDatabase(){
        final String query = "select * from customer_order inner join product_customer_order on customer_order.id = product_customer_order.customer_ordersID";
        List<Order> tempOrderList = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
            PreparedStatement stmt = c.prepareStatement(query);
        ){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tempOrderList.add(new Order(rs.getInt("id"),rs.getDate("order_date"),
                        getCustomerFromDatabaseUsingId(rs.getInt("customerID")),addShoesToListUsingOrderId(rs.getInt("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempOrderList;
    }
}
