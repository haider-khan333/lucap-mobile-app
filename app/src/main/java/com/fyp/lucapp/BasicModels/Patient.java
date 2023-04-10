package com.fyp.lucapp.BasicModels;

public class Patient {

    private String patientId;
    private String patientName;
    private String patientPassword;
    private String patientEmail;
    private String patientContact;
    private String patientImage;
    private int patientAge;
    private String patientGender;

    public Patient(String patientId, String patientName, String patientEmail, String password,
                   String patientContact,
                   String patientImage, int patientAge, String patientGender) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientContact = patientContact;
        this.patientPassword = password;
        this.patientEmail = patientEmail;
        this.patientImage = patientImage;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(String patientImage) {
        this.patientImage = patientImage;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientPassword='" + patientPassword + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientContact='" + patientContact + '\'' +
                ", patientImage='" + patientImage + '\'' +
                ", patientAge=" + patientAge +
                ", patientGender='" + patientGender + '\'' +
                '}';
    }
}
