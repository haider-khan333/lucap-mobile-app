package com.fyp.lucapp.Helper;

import android.graphics.Bitmap;

import com.fyp.lucapp.BasicModels.Data;
import com.fyp.lucapp.BasicModels.DoctorsData;
import com.fyp.lucapp.BasicModels.Medications;
import com.fyp.lucapp.BasicModels.Patient;
import com.fyp.lucapp.BasicModels.ReportData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    /**
     * return a doctor from doctor list
     *
     * @return
     */
    public static DoctorsData getDoctor(int position) {
        return Data.doctors.get(position);
    }

    /**
     * Convert string base64 to bitmap
     */
    public static Bitmap convertBase64ToBitmap(String base64) {
        byte[] decodedString = android.util.Base64.decode(base64, android.util.Base64.DEFAULT);
        return android.graphics.BitmapFactory.decodeByteArray(decodedString,
                0, decodedString.length);
    }

    public static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
    }


    public static String getFormattedDate(String date) {
        StringBuilder formattedDate = new StringBuilder();
        for (int i = 0; i < date.length(); i++) {
            if (i == 1) {
                formattedDate.append(date.charAt(i)).append(" ");
            } else if (i == 3) {
                String month = toMonth(date.substring(i - 1, i + 1));
                System.out.println("month " + month);
                System.out.println("date " + date.substring(i - 1, i + 1));
                formattedDate.append(month).append(", ");
            } else {
                if (i == 2) {
                    formattedDate.append("");
                } else {
                    formattedDate.append(date.charAt(i));
                }
            }
        }
        return formattedDate.toString();
    }

    public static String toMonth(String month) {
        switch (month) {
            case "01":
                return "January";
            case "02":
                return "February";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "August";
            case "09":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "null";
        }
    }

    public static ArrayList<DoctorsData> getDoctors(JSONArray docList) {
        ArrayList<DoctorsData> doctorsDataArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < docList.length(); i++) {
                DoctorsData doctorsData = new DoctorsData();
                doctorsData.setId(docList.getJSONObject(i).getInt("id"));
                doctorsData.setUsername(docList.getJSONObject(i).getString("username"));
                doctorsData.setImage(docList.getJSONObject(i).getString("image"));
                doctorsData.setSpeciality(docList.getJSONObject(i).getString("speciality"));
                doctorsData.setYearsOfExperience(docList.getJSONObject(i).getInt("years_of_experience"));
                doctorsData.setEmail(docList.getJSONObject(i).getString("email"));
                doctorsData.setPhone(docList.getJSONObject(i).getString("phone"));
                doctorsDataArrayList.add(doctorsData);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return doctorsDataArrayList;
    }


    public static ArrayList<ReportData> getReportList(JSONArray reportList) {
        ArrayList<ReportData> reportDataArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < reportList.length(); i++) {
                ReportData reportData = new ReportData();
                reportData.setPatientName(reportList.getJSONObject(i).optString("patient_name"));
                reportData.setDoctorName(reportList.getJSONObject(i).optString("doctor_name"));
                reportData.setDoctorSpeciality(reportList.getJSONObject(i).optString("doctor_speciality"));
                reportData.setNoduleType(reportList.getJSONObject(i).optString("cancer_type"));
                reportData.setNoduleLobe(reportList.getJSONObject(i).optString("cancer_lobe"));
                reportData.setDate(reportList.getJSONObject(i).optString("date"));
                reportData.setTime(reportList.getJSONObject(i).optString("time"));
                reportData.setDescription(reportList.getJSONObject(i).optString("description"));
                reportData.setReportID(reportList.getJSONObject(i).optString("id"));

                List<Medications> medicationsList = new ArrayList<>();
                JSONArray medications = reportList.getJSONObject(i).optJSONArray("medications");
                if (medications != null) {
                    for (int j = 0; j < medications.length(); j++) {
                        JSONObject medicationObject = medications.optJSONObject(j);
                        Medications medication = new Medications();
                        medication.setMedicineName(medicationObject.optString("medicine_name"));
                        medication.setMedicineGrams(medicationObject.optString("medicine_grams"));
                        medication.setMedicineDosage(medicationObject.optString("medicine_dosage"));
                        medication.setMedicineFrequency(medicationObject.optString("medicine_frequency"));
                        medicationsList.add(medication);
                    }
                    reportData.setMedicationsList(medicationsList);

                } else {
                    reportData.setMedicationsList(null);
                }
                reportDataArrayList.add(reportData);
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return reportDataArrayList;

    }

    public static ArrayList<Medications> getMedicines(JSONArray medicineList) {
        ArrayList<Medications> medicationsArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < medicineList.length(); i++) {
                Medications medications = new Medications();
                medications.setMedicineName(medicineList.getJSONObject(i).optString("medicine_name"));
                medications.setMedicineGrams(medicineList.getJSONObject(i).optString("medicine_grams"));
                medications.setMedicineDosage(medicineList.getJSONObject(i).optString("medicine_dosage"));
                medications.setMedicineFrequency(medicineList.getJSONObject(i).optString("medicine_frequency"));
                medicationsArrayList.add(medications);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return medicationsArrayList;
    }

    public static Patient getPatient(JSONObject patientObject) {
        Patient patient = new Patient();
        try {
            patient.setPatientId(String.valueOf(patientObject.optInt("id")));
            patient.setPatientName(patientObject.optString("username"));
            patient.setPatientEmail(patientObject.optString("email"));
            patient.setPatientContact(patientObject.optString("phone"));
            patient.setPatientAge(patientObject.optInt("age"));
            patient.setPatientPassword(patientObject.optString("password"));
            patient.setPatientGender(patientObject.optString("gender"));
        } catch
        (Exception e) {
            System.out.println(e);
        }


        return patient;
    }

    public static String convertEmailToAsterisks(String email) {
        if (email == null || !email.contains("@")) {
            return null;
        }

        String[] parts = email.split("@");
        String localPart = parts[0];
        String domain = parts[1];

        StringBuilder maskedLocalPart = new StringBuilder();
        for (int i = 0; i < localPart.length(); i++) {
            if (i != localPart.charAt(0)) {
                maskedLocalPart.append("*");
            }

        }

        return maskedLocalPart + "@" + domain;
    }




}
