package com.fyp.lucapp.Fragments;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.ReportsAdapterView;
import com.fyp.lucapp.BasicModels.Medications;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Data;
import com.fyp.lucapp.Interface.ApiCallBack;
import com.fyp.lucapp.R;
import com.fyp.lucapp.databinding.ReportCardViewBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment implements AdapterInterface, ApiCallBack {


    public class ReportDetails {

        public String reportID;
        public String date;
        public String time;
        public String doctorName;
        public String patientName;
        public String doctorSpeciality;
        public String description;
        public String noduleType;
        public String noduleLobe;
        public List<Medications> medicationsList = new ArrayList<>();

    }

    private RecyclerView recyclerView;

    private ReportDetails reportDetails;

    private URL url;

    public ReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ask for read and write permission
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_report, container,
                false);
        url = new URL(getContext(), this);
        recyclerView = view.findViewById(R.id.reportRecyclerView);
        ReportsAdapterView reportsAdapterView = new ReportsAdapterView();
        recyclerView.setAdapter(reportsAdapterView);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);

        url.getReports();
        return view;
    }


    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {
        String reportID = reportDetails.reportID;
        String patientName = reportDetails.patientName;
        String doctorName = reportDetails.doctorName;
        String date = reportDetails.date;
        String time = reportDetails.time;
        String description = reportDetails.description;
        String noduleType = reportDetails.noduleType;
        String noduleLobe = reportDetails.noduleLobe;
        String doctorSpeciality = reportDetails.doctorSpeciality;
        List<Medications> medicationsList = reportDetails.medicationsList;


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
                ? "YES" : "NO", 600, 230, paint);

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

        //add the medications
//        TextPaint mTextPaint2 = new TextPaint();
//        StaticLayout mTextLayout2 = new StaticLayout("",mTextPaint2,
//                page2.getCanvas().getWidth() - 370, Layout.Alignment.ALIGN_NORMAL,
//                1.0f, 0.0f, false);

        //add picture to the pdf
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.lucapp);
//        //resize the bitmap to keep the aspect ratio
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        int newWidth = 500;
//        int newHeight = 350;
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
//                matrix, false);
//        page2.getCanvas().drawBitmap(resizedBitmap, 10, 120, paint);

        pdfDocument.finishPage(page2);


        //save pdf
        if (Helper.savePDFFile(getFilePath(reportID), pdfDocument)) {
            Snackbar.make(binding.getRoot(), "Report Downloaded",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(binding.getRoot(), "Report Download Failed",
                    Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view, int position) {

    }


    private String getFilePath(String ID) {
        ContextWrapper contextWrapper = new ContextWrapper(getActivity());
        File downloadDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        //create a unique file name
        File file = new File(downloadDirectory, "ID_" + ID + "_" + System.currentTimeMillis()
                + ".pdf");
        System.out.println("File path: " + file.getPath());
        return file.getPath();
    }

    @Override
    public void onSuccess(Object object) {


    }

    @Override
    public void onError(Object message) {

    }


}