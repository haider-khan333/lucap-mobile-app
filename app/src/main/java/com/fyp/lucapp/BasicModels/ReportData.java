package com.fyp.lucapp.BasicModels;

import java.util.ArrayList;
import java.util.List;

public class ReportData {
    private String reportID;
    private  String date;
    private String time;
    private String doctorName;
    private String patientName;
    private String doctorSpeciality;
    private String description;
    private String noduleType;
    private String noduleLobe;
    private List<Medications> medicationsList = new ArrayList<>();

    public ReportData() {
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoduleType() {
        return noduleType;
    }

    public void setNoduleType(String noduleType) {
        this.noduleType = noduleType;
    }

    public String getNoduleLobe() {
        return noduleLobe;
    }

    public void setNoduleLobe(String noduleLobe) {
        this.noduleLobe = noduleLobe;
    }

    public List<Medications> getMedicationsList() {
        return medicationsList;
    }

    public void setMedicationsList(List<Medications> medicationsList) {
        this.medicationsList = medicationsList;
    }
}
