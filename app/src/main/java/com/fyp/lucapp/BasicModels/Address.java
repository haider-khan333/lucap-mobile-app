package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class Address implements Serializable {

    private String houseNumber;
    private String streetNumber;
    private String city;
    private String country;
    private String postalCode;

    public Address(String houseNumber, String streetNumber, String city, String country,
                   String postalCode) {
        super();
        this.houseNumber = houseNumber;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
