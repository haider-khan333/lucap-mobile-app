package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class Person extends Address implements Serializable {

    private String personId;
    private String personName;
    private String personPassword;
    private String personEmail;
    private String personContact;

    public Person(String personId, String personName, String personEmail,String password,String personContact,
                  String houseNumber, String streetNumber, String city, String country,
                  String postalCode) {
        super(houseNumber, streetNumber, city, country, postalCode);
        this.personId = personId;
        this.personName = personName;
        this.personContact = personContact;
        this.personPassword = password;
        this.personEmail = personEmail;

    }

    public Person(String personId, String personName, String personEmail,String password,String contact,
                  Address address) {
        super(address.getHouseNumber(), address.getStreetNumber(), address.getCity(),
                address.getCountry(), address.getPostalCode());
        this.personId = personId;
        this.personContact = contact;
        this.personName = personName;
        this.personPassword = password;
        this.personEmail = personEmail;

    }


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public String getPersonContact() {
        return personContact;
    }

    public void setPersonContact(String personContact) {
        this.personContact = personContact;
    }





    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", personEmail='" + personEmail + '\'' +
                '}';
    }
}
