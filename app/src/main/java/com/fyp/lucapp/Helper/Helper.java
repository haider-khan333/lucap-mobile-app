package com.fyp.lucapp.Helper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.BasicModels.DDoctor;
import com.fyp.lucapp.BasicModels.DMedications;
import com.fyp.lucapp.BasicModels.DPatient;
import com.fyp.lucapp.BasicModels.DReport;
import com.fyp.lucapp.BasicModels.DSpecificDoctor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Helper {


    /**
     * Convert string base64 to bitmap
     */
    public static Bitmap convertBase64ToBitmap(String base64) {
        String header = "data:image/png;base64,";
        if (base64.startsWith(header)) {
            base64 = base64.substring(header.length());
        }

        System.out.println("helper::base64 " + base64);
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

    public static ArrayList<DDoctor> getDoctors(JSONArray docList) {
        ArrayList<DDoctor> doctorsDataArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < docList.length(); i++) {
                DDoctor doctorsData = new DDoctor();
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


    public static DSpecificDoctor getSpecificDoctorDetails(JSONObject docList) {
        DSpecificDoctor dSpecificDoctor = new DSpecificDoctor();
        try {
            dSpecificDoctor.setId(docList.getInt("id"));
            dSpecificDoctor.setStatus(docList.getString("status"));
            dSpecificDoctor.setEmail(docList.getString("email"));
            dSpecificDoctor.setUsername(docList.getString("username"));
            dSpecificDoctor.setPhone(docList.getString("phone"));
            dSpecificDoctor.setSpeciality(docList.getString("speciality"));
            dSpecificDoctor.setImage(docList.getString("image"));
            dSpecificDoctor.setNumberOfPatients(docList.getInt("no_of_patients"));
            dSpecificDoctor.setYearsOfExperience(docList.getInt("years_of_experience"));
            dSpecificDoctor.setAbout(docList.getString("about"));

            JSONArray currentReviewsList = docList.getJSONArray("current_reviews");
            List<String> currentReviews = new ArrayList<>();
            for (int i = 0; i < currentReviewsList.length(); i++) {
                currentReviews.add(currentReviewsList.getString(i));
            }
            dSpecificDoctor.setCurrentReviews(currentReviews);
            dSpecificDoctor.setCurrentRatings(docList.getString("current_ratings"));

            dSpecificDoctor.setAvailableTimes(getWorkingTime(
                    docList.getJSONArray("available_times")));
        } catch (Exception e) {
            System.out.println(e);
        }

        return dSpecificDoctor;
    }

    private static LinkedList<Map<String, List<Integer>>> getWorkingTime(JSONArray jsonArray) {
        LinkedList<Map<String, List<Integer>>> returnList = new LinkedList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String day = jsonObject.getString("day");
                JSONArray timingsJsonArray = jsonObject.getJSONArray("timings");
                List<Integer> timingsList = new ArrayList<>();
                for (int j = 0; j < timingsJsonArray.length(); j++) {
                    timingsList.add(timingsJsonArray.getInt(j));
                }
                returnList.add(new HashMap<String, List<Integer>>() {{
                    put(day, timingsList);
                }});

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        Collections.sort(returnList, new Comparator<Map<String, List<Integer>>>() {
            @Override
            public int compare(Map<String, List<Integer>> o1, Map<String, List<Integer>> o2) {
                return o1.keySet().toArray()[0].toString().compareTo(o2.keySet().
                        toArray()[0].toString());
            }
        });

        return returnList;
    }


    public static ArrayList<DReport> getReportList(JSONArray reportList) {
        ArrayList<DReport> reportDataArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < reportList.length(); i++) {
                DReport reportData = new DReport();
                reportData.setPatientName(reportList.getJSONObject(i).optString("patient_name"));
                reportData.setDoctorName(reportList.getJSONObject(i).optString("doctor_name"));
                reportData.setDoctorSpeciality(reportList.getJSONObject(i).optString("doctor_speciality"));
                reportData.setNoduleType(reportList.getJSONObject(i).optString("cancer_type"));
                reportData.setNoduleLobe(reportList.getJSONObject(i).optString("cancer_lobe"));
                reportData.setDate(reportList.getJSONObject(i).optString("date"));
                reportData.setTime(reportList.getJSONObject(i).optString("time"));
                reportData.setDescription(reportList.getJSONObject(i).optString("description"));
                reportData.setReportID(reportList.getJSONObject(i).optString("id"));

                List<DMedications> medicationsList = new ArrayList<>();
                JSONArray medications = reportList.getJSONObject(i).optJSONArray("medications");
                if (medications != null) {
                    for (int j = 0; j < medications.length(); j++) {
                        JSONObject medicationObject = medications.optJSONObject(j);
                        DMedications medication = new DMedications();
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

    public static ArrayList<DMedications> getMedicines(JSONArray medicineList) {
        ArrayList<DMedications> medicationsArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < medicineList.length(); i++) {
                DMedications medications = new DMedications();
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

    public static DPatient getPatient(JSONObject patientObject) {
        DPatient patient = new DPatient();
        try {
            patient.setPatientId(String.valueOf(patientObject.optInt("id")));
            patient.setPatientName(patientObject.optString("username"));
            patient.setPatientEmail(patientObject.optString("email"));
            patient.setPatientContact(patientObject.optString("phone"));
            patient.setPatientAge(patientObject.optInt("age"));
            patient.setPatientPassword(patientObject.optString("password"));
            patient.setPatientGender(patientObject.optString("gender"));
            patient.setPatientImage(patientObject.optString("image"));
        } catch
        (Exception e) {
            System.out.println(e);
        }


        return patient;
    }

    public static List<DAppointment> getAppointmentData(JSONArray appList) {
        List<DAppointment> dAppointmentList = new ArrayList<>();

        for (int i = 0; i < appList.length(); i++) {
            JSONObject appObject = appList.optJSONObject(i);
            DAppointment dAppointment = new DAppointment();
            dAppointment.setDoctorName(appObject.optString("doctor_name"));
            dAppointment.setDoctorSpeciality(appObject.optString("doctor_speciality"));
            dAppointment.setDoctorImage(appObject.optString("doctor_image"));
            dAppointment.setStatus(appObject.optString("status"));
            String day = appObject.optString("day");
            String time = appObject.optString("time");

            dAppointment.setDoctorTiming(day + " | " + time);
            dAppointmentList.add(dAppointment);
        }


        return dAppointmentList;

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

    public static void removeUserID() {
        URL.LOGGED_IN_PATIENT_ID = "";
    }

    public static void setUserID(String userID) {
        URL.LOGGED_IN_PATIENT_ID = userID;
    }

    public static void removeSavedUser(Context context) {
        //remove user data from shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("userDetail", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("userID");
        editor.remove("userName");
        editor.remove("userEmail");
        editor.remove("userPhone");
        editor.remove("userGender");
        editor.remove("userImage");
        editor.remove("userAge");
        editor.apply();
        //remove user data from static variable
        removeUserID();
    }

    public static void saveUser(Context context,
                                String userID, String userName,
                                String userEmail, String userPhone,
                                String userGender, String userImage,
                                String userAge) {
        //save user data in shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("userDetail", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("userID", userID);
        editor.putString("userName", userName);
        editor.putString("userEmail", userEmail);
        editor.putString("userPhone", userPhone);
        editor.putString("userGender", userGender);
        editor.putString("userImage", userImage);
        editor.putString("userAge", userAge);
        editor.apply();
        //save user data in static variable
        setUserID(userID);
    }

    public static DPatient getSavedUser(Context context) {
        //get user data from shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("userDetail", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "");
        String userName = sharedPreferences.getString("userName", "");
        String userEmail = sharedPreferences.getString("userEmail", "");
        String userPhone = sharedPreferences.getString("userPhone", "");
        String userGender = sharedPreferences.getString("userGender", "");
        String userImage = sharedPreferences.getString("userImage", "");
        String userAge = sharedPreferences.getString("userAge", "");

        DPatient patient = new DPatient();
        patient.setPatientId(userID);
        patient.setPatientName(userName);
        patient.setPatientEmail(userEmail);
        patient.setPatientContact(userPhone);
        patient.setPatientGender(userGender);
        patient.setPatientImage(userImage);
        patient.setPatientAge(Integer.parseInt(userAge));

        return patient;
    }


}
