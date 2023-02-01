package Shoeshop.Server;

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
                        /*,new ArrayList<>()*/));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempShoeList;
    }



    public void  CallAddToCart(int customerId, int customerOrderId, int shoeId){
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
}
