package com.fyp.lucapp.BasicModels;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DSpecificDoctor {


    private int id;
    private String status;
    private String email;
    private String username;
    private String phone;
    private String speciality;
    private String image;
    private int numberOfPatients;
    private int yearsOfExperience;
    private String about;
    private String currentWorkingHours;
    private List<String> currentReviews;
    private String currentRatings;
    private LinkedList<Map<String, List<Integer>>> availableTimes;

    public DSpecificDoctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCurrentWorkingHours() {
        return currentWorkingHours;
    }

    public void setCurrentWorkingHours(String currentWorkingHours) {
        this.currentWorkingHours = currentWorkingHours;
    }

    public List<String> getCurrentReviews() {
        return currentReviews;
    }

    public void setCurrentReviews(List<String> currentReviews) {
        this.currentReviews = currentReviews;
    }

    public String getCurrentRatings() {
        return currentRatings;
    }

    public void setCurrentRatings(String currentRatings) {
        this.currentRatings = currentRatings;
    }

    public LinkedList<Map<String,List<Integer>>> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(LinkedList<Map<String,List<Integer>>> availableTimes) {
        this.availableTimes= availableTimes;
    }
}
