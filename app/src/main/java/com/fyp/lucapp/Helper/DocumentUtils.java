package com.fyp.lucapp.Helper;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.fyp.lucapp.BasicModels.DMedications;
import com.fyp.lucapp.BasicModels.DReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocumentUtils {


    public static boolean createPDF(DReport reportDetails, Context context) {
        String reportID = reportDetails.getReportID();
        String patientName = reportDetails.getPatientName();
        String doctorName = reportDetails.getDoctorName();

        String date = reportDetails.getDate();
        String time = reportDetails.getTime();
        String description = reportDetails.getDescription();
        String noduleType = reportDetails.getNoduleType();
        String noduleLobe = reportDetails.getNoduleLobe();
        String doctorSpeciality = reportDetails.getDoctorSpeciality();
        List<DMedications> medicationsList = reportDetails.getMedicationsList();


        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(700,
                500, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Paint paint = new Paint();


        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
        paint.setTextSize(25);
        paint.setTextAlign(Paint.Align.CENTER);
        page.getCanvas().drawText("(Medical Report)", 350, 50, paint);

        //make a line
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        page.getCanvas().drawLine(10, 60, 690, 60, paint);

        //reset the stroke width
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);


        //make the "Report ID" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Report ID: ", 10, 100, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        page.getCanvas().drawText(reportID, 120, 100, paint);


        //TODO:code for report generation details
        //make the "Report Generated At" text bold and the value of it normal and place it on the right side
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("(Report Generation Details)",
                400, 100, paint);

        //create a new line
        paint.setStrokeWidth(0.5f);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        page.getCanvas().drawLine(400, 110, 690, 110, paint);

        //reset the stroke width
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        //make the "Date" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Date: ", 400, 130, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        page.getCanvas().drawText(date, 600, 130, paint);

        //make the "Day" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Time: ", 400, 150, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        page.getCanvas().drawText(time, 600, 150, paint);


        //TODO: code snippet for the results of the cancer detections
        //make the "Results" text bold and the value of it normal and place it on the right side
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("(Cancer Details)",
                400, 200, paint);

        //create a new line
        paint.setStrokeWidth(0.5f);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        page.getCanvas().drawLine(400, 210, 690, 210, paint);

        //reset the stroke width
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        //make the "Detections" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Cancer (if any): ", 400, 230, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        page.getCanvas().drawText(noduleType.equals("normal")
                ? "NO" : "YES", 600, 230, paint);

        //make the "Cancer" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Cancer type (if any): ", 400, 250, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        page.getCanvas().drawText(noduleType, 600, 250, paint);

        //make the "Cancer Location" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Cancer Location: ", 400, 270, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        page.getCanvas().drawText(noduleLobe, 600, 270, paint);


        //TODO: code snippet for the patient details
        //make the "Patient Name" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Patient Name: ", 10, 120, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        page.getCanvas().drawText(patientName, 120, 120, paint);

        //TODO: code snippet for the Doctor details
        //make the "Doctor Name" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Doctor Name: ", 10, 140, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        page.getCanvas().drawText(doctorName, 120, 140, paint);


        //TODO: code snippet for the Description of the report
        //make the "Description" text bold and the value of it normal
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page.getCanvas().drawText("Description: ", 10, 160, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        TextPaint mTextPaint = new TextPaint();
        StaticLayout mTextLayout = new StaticLayout(description, mTextPaint,
                page.getCanvas().getWidth() - 370, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, false);
        page.getCanvas().save();
        int textX = 10;
        int textY = 170;
        page.getCanvas().translate(textX, textY);
        mTextLayout.draw(page.getCanvas());
        page.getCanvas().restore();

        //make a vertical grey line
        paint.setStrokeWidth(0.5f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        page.getCanvas().drawLine(380, 60, 380, 500, paint);

        //reset the stroke width
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        pdfDocument.finishPage(page);

        //TODO: second page
        PdfDocument.PageInfo pageInfo2 = new PdfDocument.PageInfo.Builder(700,
                500, 2).create();
        PdfDocument.Page page2 = pdfDocument.startPage(pageInfo2);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
        paint.setTextSize(25);
        paint.setTextAlign(Paint.Align.CENTER);
        page2.getCanvas().drawText("(Medical Report)", 350, 50, paint);

        //make a line
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        page2.getCanvas().drawLine(10, 60, 690, 60, paint);

        //reset the stroke width
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);


        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setTextSize(15);
        paint.setTextAlign(Paint.Align.LEFT);
        page2.getCanvas().drawText("Medications: ", 10, 100, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        int medicationStartY = 120;
        paint.setTextSize(14);

        for (DMedications medication : medicationsList) {
            String medicineName = medication.getMedicineName();
            String medicineGrams = medication.getMedicineGrams();
            String medicineDosage = medication.getMedicineDosage();
            String medicineFrequency = medication.getMedicineFrequency();

            // Draw medication name
            paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
            page2.getCanvas().drawText("Medicine Name: ", 10, medicationStartY, paint);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            page2.getCanvas().drawText(medicineName, 130, medicationStartY, paint);

            // Draw medication grams
            paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
            page2.getCanvas().drawText("Medicine Grams: ", 10, medicationStartY + 20, paint);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            page2.getCanvas().drawText(medicineGrams, 130, medicationStartY + 20, paint);

            // Draw medication dosage
            paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
            page2.getCanvas().drawText("Medicine Dosage: ", 10, medicationStartY + 40, paint);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            page2.getCanvas().drawText(medicineDosage, 130, medicationStartY + 40, paint);

            // Draw medication frequency
            paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL));
            page2.getCanvas().drawText("Medicine Frequency: ", 10, medicationStartY + 60, paint);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            page2.getCanvas().drawText(medicineFrequency, 140, medicationStartY + 60, paint);

            // Increment the starting position for the next medication
            medicationStartY += 80;

            // Draw a line
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.STROKE);
            page2.getCanvas().drawLine(10, medicationStartY, 690, medicationStartY, paint);
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.FILL);

            // Increment the starting position for the next medication
            medicationStartY += 20;


        }

        pdfDocument.finishPage(page2);


        //save pdf
        return savePDFFile(getFilePath(reportID, context), pdfDocument);

    }

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

    private static String getFilePath(String ID, Context context) {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File downloadDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        //create a unique file name
        File file = new File(downloadDirectory, "ID_" + ID + "_" + System.currentTimeMillis()
                + ".pdf");
        System.out.println("File path: " + file.getPath());
        return file.getPath();
    }


}
