package com.fyp.lucapp.MainModels;

import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.BasicModels.Reviews;
import com.fyp.lucapp.BasicModels.WorkTime;

public class MainDoctorModel {
    private Doctors doctor;
    private WorkTime workingTime;
    private Reviews reviews;


    public MainDoctorModel() {
    }

    public MainDoctorModel(Doctors doctor, WorkTime workingTime, Reviews reviews) {
        this.doctor = doctor;
        this.workingTime = workingTime;
        this.reviews = reviews;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public WorkTime getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(WorkTime workingTime) {
        this.workingTime = workingTime;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "MainDoctorModel{" +
                "doctor=" + doctor +
                ", workingTime=" + workingTime +
                ", reviews=" + reviews +
                '}';
    }
}
