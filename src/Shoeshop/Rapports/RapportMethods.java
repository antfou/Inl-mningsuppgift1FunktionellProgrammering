package Shoeshop.Rapports;

import Shoeshop.Objects.Address;
import Shoeshop.Objects.Customer;
import Shoeshop.Objects.Order;
import Shoeshop.Objects.Shoe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RapportMethods {

    public List<String> rCustomerNames(List<Customer> list){
        return list.stream().map(Customer::getFullName).toList();
    }
    public List<String> rCustomerAddress(List<Customer> list){
        return list.stream().map(Customer::getAddress).toList().stream().map(Address::getAddressName).toList();
    }


    public List<Order> rFilterOrder(List<Order> orderList, String wordToSearchFor, String typeToSearchFor){
        if(typeToSearchFor.equalsIgnoreCase("size")){
            //return orderList.stream().filter(order -> order.getOrderedShoes().stream().filter(Shoe::getSize).equals(Integer.parseInt(wordToSearchFor)));
        }else if(typeToSearchFor.equalsIgnoreCase("color")){

        }else if(typeToSearchFor.equalsIgnoreCase("brand")){

        }
        return null;
    }
}
