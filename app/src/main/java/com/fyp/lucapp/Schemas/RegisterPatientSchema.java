package com.fyp.lucapp.Schemas;

public class RegisterPatientSchema {
    private String patientEmail;
    private String patientPassword;
    private String patientName;
    private String patientContact;
    private String patientGender;
    private int patientAge;

    private String patientImage;

    public RegisterPatientSchema(String patientEmail, String patientPassword,
                                 String patientName, String patientContact,
                                 String patientGender, int patientAge
            , String patientImage) {
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientName = patientName;
        this.patientContact = patientContact;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.patientImage = patientImage;
        System.out.println("RegisterPatientSchema: " + this.toString());
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(String patientImage) {
        this.patientImage = patientImage;
    }

    @Override
    public String toString() {
        return "RegisterPatientSchema{" +
                "patientEmail='" + patientEmail + '\'' +
                ", patientPassword='" + patientPassword + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientContact='" + patientContact + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAge=" + patientAge +
                ", patientImage='" + patientImage + '\'' +
                '}';
    }
}
