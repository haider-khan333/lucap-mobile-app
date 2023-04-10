package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class Doctors implements Serializable {

    private int id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String speciality;
    private String image;
    private int noOfPatients;
    private int yearsOfExperience;
    private String about;


    public Doctors() {

    }

    public Doctors(int id, String email, String username, String password, String phone,
                   String speciality, String image, int noOfPatients, int yearsOfExperience,
                   String about) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.speciality = speciality;
        this.image = image;
        this.noOfPatients = noOfPatients;
        this.yearsOfExperience = yearsOfExperience;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getImage() {
        return image;
    }

    public int getNoOfPatients() {
        return noOfPatients;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getAbout() {
        return about;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNoOfPatients(int noOfPatients) {
        this.noOfPatients = noOfPatients;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", speciality='" + speciality + '\'' +
                ", image='" + image + '\'' +
                ", noOfPatients=" + noOfPatients +
                ", yearsOfExperience=" + yearsOfExperience +
                ", about='" + about + '\'' +
                '}';
    }
}
