package com.fyp.lucapp.BasicModels;


import java.io.Serializable;

public class DMedications implements Serializable {
    private String medicineName;
    private String medicineGrams;
    private String medicineDosage;
    private String medicineFrequency;

    public DMedications() {
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

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public String getMedicineFrequency() {
        return medicineFrequency;
    }

    public void setMedicineFrequency(String medicineFrequency) {
        this.medicineFrequency = medicineFrequency;
    }
}
