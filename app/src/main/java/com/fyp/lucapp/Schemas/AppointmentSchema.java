package com.fyp.lucapp.Schemas;

public class AppointmentSchema {

    private int doctorId;
    private int patientId;
    private String date;
    private String start_time;
    private String end_time;

    public AppointmentSchema(int doctorId, int patientId, String date, String start_time, String end_time) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
