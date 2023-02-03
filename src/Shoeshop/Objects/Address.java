package Shoeshop.Objects;

public class Address {
    protected final String addressName;
    protected final String cityName;

    public Address(String addressName, String cityName) {
        this.addressName = addressName;
        this.cityName = cityName;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getCity() {
        return cityName;
    }
}