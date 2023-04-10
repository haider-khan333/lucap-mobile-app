package com.fyp.lucapp.Helper;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;

import com.fyp.lucapp.BasicModels.Data;
import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.MainModels.MainDoctorModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    /**
     * return a doctor from doctor list
     *
     * @return
     */
    public static Doctors getDoctor(int position) {
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

    /**
     * Save PDF file to local storage
     */
    public static boolean savePDFFile(String fileName, PdfDocument pdfDocument) {
        try {
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pdfDocument.writeTo(fileOutputStream);
            fileOutputStream.close();
            pdfDocument.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Doctors> getDoctors(JSONArray docList) {
        ArrayList<Doctors> doctorsArrayList = new ArrayList<>();
        try {
            for (int i = 0; i < docList.length(); i++) {
                Doctors doctors = new Doctors();
                doctors.setId(docList.getJSONObject(i).getInt("id"));
                doctors.setUsername(docList.getJSONObject(i).getString("username"));
                doctors.setImage(docList.getJSONObject(i).getString("image"));
                doctors.setSpeciality(docList.getJSONObject(i).getString("speciality"));
                doctorsArrayList.add(doctors);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return doctorsArrayList;
    }

//    public static List<MainDoctorModel> parseDoctors(JSONArray doctorsList,
//                                                     JSONArray workingList,
//                                                     JSONArray ratingList) throws JSONException {
//        List<MainDoctorModel> mainDoctorsList = new ArrayList<>();
//
//
//    }


}
