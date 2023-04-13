package com.fyp.lucapp.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.fyp.lucapp.R;
import com.google.android.material.card.MaterialCardView;

public class ComponentReport extends LinearLayout {

    private TextView date;
    private TextView doctorDetails;
    private TextView downloadReport;

    public ComponentReport(@NonNull Context context) {
        super(context);
        init();
    }

    public ComponentReport(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentReport(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_report,
                this, true);
        date = view.findViewById(R.id.reportDate);
        doctorDetails = view.findViewById(R.id.doctorDetails);
        downloadReport = view.findViewById(R.id.downloadReport);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails.setText(doctorDetails);
    }

    public void setDownloadReport(String downloadReport) {
        this.downloadReport.setText(downloadReport);
    }


}
