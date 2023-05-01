package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class DAppointmentAdapter implements Serializable {

    private String doctorName;
    private String doctorImage;
    private String doctorSpeciality;
    private String doctorTiming;

    public DAppointmentAdapter() {
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDoctorTiming() {
        return doctorTiming;
    }

    public void setDoctorTiming(String doctorTiming) {
        this.doctorTiming = doctorTiming;
    }
}
