package com.fyp.lucapp.Schemas;

import java.io.Serializable;

public class ConfirmAppointmentSchema implements Serializable {
    private int doctorID;


    private String day;
    private int time;

    public ConfirmAppointmentSchema() {

    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}

