package Shoeshop.Objects;

import java.util.List;

public class Customer {

    protected final int id;
    protected final String firstName;
    protected final String lastName;
    protected final String password;
    protected final Address address;

    public Customer(int id, String firstName, String lastName, String password, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer||" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address.addressName +
                "||";
    }
}
