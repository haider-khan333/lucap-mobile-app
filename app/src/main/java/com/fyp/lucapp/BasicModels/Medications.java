package com.fyp.lucapp.BasicModels;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Medications implements Serializable {
    private String medicineName;
    private String medicineGrams;
    private String startDate, endDate;
    private ArrayList<String> medicineTime;
    private String medicineGeneratedTime;

    public Medications(String medicineName, String medicineGrams,
                       String startDate, String endDate,
                       ArrayList<String> medicineTime
            , String medicineGeneratedTime) {
        this.medicineName = medicineName;
        this.medicineGrams = medicineGrams;
        this.medicineTime = medicineTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicineGeneratedTime = medicineGeneratedTime;

    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineGrams() {
        return medicineGrams;
    }

    public void setMedicineGrams(String medicineGrams) {
        this.medicineGrams = medicineGrams;
    }

    public ArrayList<String> getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(ArrayList<String> medicineTime) {
        this.medicineTime = medicineTime;
    }

    public String getMedicineGeneratedTime() {
        return medicineGeneratedTime;
    }

    public void setMedicineGeneratedTime(String medicineGeneratedTime) {
        this.medicineGeneratedTime = medicineGeneratedTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Medications{" +
                "medicineName='" + medicineName + '\'' +
                ", medicineGrams='" + medicineGrams + '\'' +
                ", medicineTime=" + medicineTime.toString() +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", medicineGeneratedTime=" + medicineGeneratedTime.toString() +
                '}';
    }
}
