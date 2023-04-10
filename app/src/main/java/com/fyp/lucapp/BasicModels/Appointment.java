package com.fyp.lucapp.BasicModels;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Appointment implements Serializable {

    private String appointmentId;
    private String doctorID;
    private String patientID;
    private Date appointmentDate;
    private Time appointmentTime;
    private String isActive;

    public Appointment(String appointmentId, String doctorID, String patientID,
                       Date appointmentDate, Time appointmentTime, String isActive) {
        this.appointmentId = appointmentId;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.isActive = isActive;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String isActive() {
        return isActive;
    }

    public void setActive(String active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", patientID='" + patientID + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", isActive=" + isActive +
                '}';
    }
}
