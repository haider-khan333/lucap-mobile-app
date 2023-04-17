package com.fyp.lucapp.Schemas;

public class UpdatePatientSchema {
    private String userName;
    private String age;
    private String phoneNumber;
    private String gender;
    private String image;

    public UpdatePatientSchema() {
    }

    public UpdatePatientSchema(String userName, String age, String phoneNumber, String gender
    ,String image) {
        this.userName = userName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
