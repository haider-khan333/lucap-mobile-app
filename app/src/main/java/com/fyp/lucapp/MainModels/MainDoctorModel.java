package com.fyp.lucapp.MainModels;

import com.fyp.lucapp.BasicModels.DoctorsData;
import com.fyp.lucapp.BasicModels.Reviews;
import com.fyp.lucapp.BasicModels.WorkTime;

public class MainDoctorModel {
    private DoctorsData doctor;
    private WorkTime workingTime;
    private Reviews reviews;


    public MainDoctorModel() {
    }

    public MainDoctorModel(DoctorsData doctor, WorkTime workingTime, Reviews reviews) {
        this.doctor = doctor;
        this.workingTime = workingTime;
        this.reviews = reviews;
    }

    public DoctorsData getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorsData doctor) {
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
