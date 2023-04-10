package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class Report implements Serializable {

    private String reportGenerationDate;
    private String doctorDetails;

    public Report(String reportGenerationDate,
                  String doctorDetails) {
        this.reportGenerationDate = reportGenerationDate;
        this.doctorDetails = doctorDetails;

    }

    public String getReportGenerationDate() {
        return reportGenerationDate;
    }

    public void setReportGenerationDate(String reportGenerationDate) {
        this.reportGenerationDate = reportGenerationDate;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportGenerationDate='" + reportGenerationDate + '\'' +
                ", doctorDetails='" + doctorDetails + '\'' +
                '}';
    }
}
