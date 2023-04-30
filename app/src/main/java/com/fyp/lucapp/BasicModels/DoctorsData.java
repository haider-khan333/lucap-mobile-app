package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class DoctorsData implements Serializable {



    private int id;
    private String email;
    private String username;
    private String phone;
    private String speciality;
    private String image;
    private int yearsOfExperience;


    public DoctorsData() {

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

    public String getPhone() {
        return phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getImage() {
        return image;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
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


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", speciality='" + speciality + '\'' +
                ", image='" + image + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
